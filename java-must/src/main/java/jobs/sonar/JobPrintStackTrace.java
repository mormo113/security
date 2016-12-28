package jobs.sonar;

import jobs.AbstractJob;
import org.eclipse.jdt.core.dom.CompilationUnit;
import tools.LoggerScren;
import updater.sonar.Logger.UpdaterAddLogger;
import updater.sonar.Logger.UpdaterFindLogger;
import updater.sonar.PrintStackTrace.ACPrintStackTrace;

/**
 * Created by b010cli on 01/04/2016.
 */
public class JobPrintStackTrace extends AbstractJob {

    private static LoggerScren LOGGER = new LoggerScren(JobPrintStackTrace.class.getName());

    /*
     * Remplace les printStackTrace par des loggeur.debug
     */
    @Override
    public void executeUpdater(CompilationUnit cu){
        UpdaterFindLogger astFindLogger = new UpdaterFindLogger();
        ACPrintStackTrace astPrintStackTrace = new ACPrintStackTrace();
        cu.accept(astFindLogger);
		astPrintStackTrace.setParameter(astFindLogger.getParameter());
        cu.accept(astPrintStackTrace);
        if (astFindLogger.getParameter().getNameLogger() == null && astPrintStackTrace.getParameter().isClassEdited()) {
            LOGGER.info("logger non trouver / ajout logger");
            UpdaterAddLogger.job(cu);
        }
    }

}
