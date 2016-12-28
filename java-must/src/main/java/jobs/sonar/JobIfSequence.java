package jobs.sonar;

import jobs.AbstractJob;
import org.eclipse.jdt.core.dom.CompilationUnit;
import updater.sonar.IfSequence.UpdaterIfSequence;

/**
 * Created by b010cli on 29/11/2016.
 */

/**
 *
 */
public class JobIfSequence extends AbstractJob {

    @Override
    public void executeUpdater(CompilationUnit cu){
        cu.accept(new UpdaterIfSequence());
    }
}
