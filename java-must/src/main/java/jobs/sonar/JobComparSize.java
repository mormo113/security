package jobs.sonar;

import jobs.AbstractJob;
import org.eclipse.jdt.core.dom.CompilationUnit;
import tools.LoggerScren;
import updater.sonar.ComparSize.UpdaterComparCorrectSize;
import updater.sonar.ComparSize.UpdaterComparDoubleNot;
import updater.sonar.ComparSize.UpdaterComparSwitch;

/**
 * Created by b010cli on 01/04/2016.
 */
public class JobComparSize extends AbstractJob {

    private static LoggerScren LOGGER = new LoggerScren(JobComparSize.class.getName());

    /*
     * Correction des accolade pour les if et les for
     */
    @Override
    public void executeUpdater(CompilationUnit cu){
        cu.accept(new UpdaterComparSwitch());
        cu.accept(new UpdaterComparCorrectSize());
        cu.accept(new UpdaterComparDoubleNot());
    }

}
