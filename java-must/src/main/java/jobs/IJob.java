package jobs;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.text.BadLocationException;

import java.io.File;
import java.io.IOException;

/**
 * Created by b010cli on 01/04/2016.
 */
public interface IJob {

    public CompilationUnit doJob(File file) throws BadLocationException, IOException;

}
