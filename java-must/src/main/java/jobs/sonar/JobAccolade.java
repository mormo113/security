package jobs.sonar;

import jobs.AbstractJob;
import org.eclipse.jdt.core.dom.CompilationUnit;
import tools.LoggerScren;
import updater.sonar.Accolade.UpdaterAccolade;

/**
 * Created by b010cli on 01/04/2016.
 */
public class JobAccolade extends AbstractJob{

    private static LoggerScren LOGGER = new LoggerScren(JobAccolade.class.getName());

    /*
     * Correction des accolade pour les if et les for
     */
    @Override
    public void executeUpdater(CompilationUnit cu){
        cu.accept(new UpdaterAccolade());
    }

}
