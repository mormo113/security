package job.sonar;

import job.AbstractJobTest;
import jobs.sonar.JobAccolade;
import junitx.framework.FileAssert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by b010qds on 05/12/2016.
 */
public class JobAccoladeTest extends AbstractJobTest{

    private static final String FILE_NAME_ORIGINE = "JobAccolade_Origine.java";
    private static final String FILE_NAME_CORRIGE = "JobAccolade_Corrige.java";

    @Before
    public void setUp() {
        job = new JobAccolade();
        fileOrigine = getFile(FILE_NAME_ORIGINE);
        fileCorrige = getFile(FILE_NAME_CORRIGE);
    }

    @Test
    public void testDoJob() throws Exception {

        job.doJob(fileOrigine);

        FileAssert.assertEquals(fileCorrige, fileOrigine);

    }
}
