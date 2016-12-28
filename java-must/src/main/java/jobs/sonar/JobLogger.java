package jobs.sonar;

import jobs.AbstractJob;
import org.apache.log4j.Logger;
import org.eclipse.jdt.core.dom.CompilationUnit;
import updater.sonar.Logger.UpdaterChangeLogger;
import updater.sonar.Logger.UpdaterCorrectionFieldAccess;
import updater.sonar.Logger.UpdaterFindLogger;

/**
 * Created by b010cli on 01/04/2016.
 */
public class JobLogger extends AbstractJob {

    private static Logger LOGGER = Logger.getLogger(JobLogger.class.getName());

    /*
     * Correction sur les loggeurs
     * Armonise les nom des loggeur en LOGGER
     * Ajouter les attribue private/final/static si il manque
     * Si le logger est appeler avec this/nom de la classe on supprime
     */
    @Override
    public void executeUpdater(CompilationUnit cu){
        UpdaterFindLogger astFindLogger = new UpdaterFindLogger();
        cu.accept(astFindLogger);
        if (astFindLogger.getParameter().getNameLogger() != null) {
            LOGGER.info("logger trouver traitement start");
            UpdaterChangeLogger astChangeLogger = new UpdaterChangeLogger();
            astChangeLogger.setParameter(astFindLogger.getParameter());
            cu.accept(astChangeLogger);
            UpdaterCorrectionFieldAccess astCorrectionFieldAccess = new UpdaterCorrectionFieldAccess();
            astCorrectionFieldAccess.setParameter(astChangeLogger.getParameter());
            cu.accept(astCorrectionFieldAccess);
        }
    }

}
