package job.fortify;

import job.AbstractJobTest;
import jobs.fortify.JobA4PathManipulation;
import junitx.framework.FileAssert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by b010qds on 30/11/2016.
 */
public class JobA4PathManipulationTest extends AbstractJobTest{

    private static final String FILE_NAME = "JobA4PathManipulation_Origine.java";
    private static final String FILE_NAME2 = "JobA4PathManipulation_Corrige.java";

    @Before
    public void setUp() throws IOException {
        job = new JobA4PathManipulation();
        fileOrigine = getFile(FILE_NAME);
        fileCorrige = getFile(FILE_NAME2);
    }

    @Test
    public void testDoJob()throws Exception {

        job.doJob(fileOrigine);

        FileAssert.assertEquals(fileCorrige, fileOrigine);

    }

}
