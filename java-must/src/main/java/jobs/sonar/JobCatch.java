package jobs.sonar;

import jobs.AbstractJob;
import org.eclipse.jdt.core.dom.CompilationUnit;
import tools.LoggerScren;
import updater.sonar.Catch.UpdaterCatch;
import updater.sonar.Logger.UpdaterAddLogger;
import updater.sonar.Logger.UpdaterFindLogger;

/**
 * Created by b010cli on 01/04/2016.
 */
public class JobCatch extends AbstractJob{

    private static LoggerScren LOGGER = new LoggerScren(JobCatch.class.getName());

    /*
     * Ajoute un loggeur lorsque :
     *  - pas de logger
     *  - pas de throw new
     *  - pas de throw de l'exception catchée
     */
    @Override
    public void executeUpdater(CompilationUnit cu){
        UpdaterFindLogger astFindLogger = new UpdaterFindLogger();
        UpdaterCatch astCatch = new UpdaterCatch();
        cu.accept(astFindLogger);
        cu.accept(astCatch);
        if (astFindLogger.getParameter().getNameLogger() == null && astCatch.getParameter().isClassEdited()) {
            UpdaterAddLogger.job(cu);
        }
    }

}
