package jobs.fortify;

import jobs.AbstractJob;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.CompilationUnit;
import updater.fortify.A1.UpdaterXmlInjection;

/**
 * Created by b010qds on 21/12/2016.
 */
public class JobA1XmlExternalEntityInjection extends AbstractJob {
	private static Logger LOGGER = Logger.getLogger(JobA1XmlExternalEntityInjection.class.getName());

	/*
	 * Sécuriser l'injection d'entités externes via un DocumentBuilderFactory
	 */

	@Override
	public void executeUpdater(CompilationUnit cu){
		UpdaterXmlInjection astXmlInjection = new UpdaterXmlInjection();
		cu.accept(astXmlInjection);
	}
}
