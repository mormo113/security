package jobs.fortify;

import jobs.AbstractJob;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.*;
import updater.fortify.A4.UpdaterFindPathType;
import updater.fortify.A4.UpdaterPathManipulation;
import updater.utils.AddImport;

/**
 * Created by b010cli on 01/04/2016.
 */
public class JobA4PathManipulation extends AbstractJob {

    private static Logger LOGGER = Logger.getLogger(JobA4PathManipulation.class.getName());

	/*
	 * Sécuriser les chemins d'accès lors de l'instanciation des classes:
	 * File | FileInputStream | FileOutputStream | FileReader | FileWriter
	 * Cas particuliers :
	 * Pour les classes FileInputStream | FileOutputStream | FileReader | FileWriter
	 * il faut faut appliquer la règle seulement quand on appel le constructeur
	  * qui prend une String en paramètre
	  * 	EX : new FileInputStream("/data/myRepo")
	 */

    @Override
    public void executeUpdater(CompilationUnit cu){
        UpdaterPathManipulation astPathManipulation = new UpdaterPathManipulation();
		UpdaterFindPathType astFindPathType = new UpdaterFindPathType();
		cu.accept(astFindPathType);
        cu.accept(astPathManipulation);
        if (astPathManipulation.getParameter().isClassEdited()) {
            AddImport.add(cu,"fr","generali","ccj","security","helpers","SecuredFile");
        }
		astPathManipulation.getParameter().getVariablesMap().clear();
    }

}
