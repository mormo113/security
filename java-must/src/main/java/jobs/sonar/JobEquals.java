package jobs.sonar;

import jobs.AbstractJob;
import org.eclipse.jdt.core.dom.CompilationUnit;
import tools.LoggerScren;
import updater.sonar.Equals.UpdaterEquals;

/**
 * Created by b010cli on 20/06/2016.
 */
public class JobEquals extends AbstractJob {

    private static LoggerScren LOGGER = new LoggerScren(JobEquals.class.getName());

    @Override
    public void executeUpdater(CompilationUnit cu){
        UpdaterEquals astEquals = new UpdaterEquals();
        cu.accept(astEquals);
    }

}
