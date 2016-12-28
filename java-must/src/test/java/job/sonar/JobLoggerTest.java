package job.sonar;

import job.AbstractJobTest;
import jobs.sonar.JobIfSequence;
import jobs.sonar.JobLogger;
import junitx.framework.FileAssert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by b010qds on 07/12/2016.
 */
public class JobLoggerTest extends AbstractJobTest {

    private static final String FILE_NAME_ORIGINE = "JobLogger_Origine.java";
    private static final String FILE_NAME_CORRIGE = "JobLogger_Corrige.java";

    @Before
    public void setUp() {
        job = new JobLogger();
        fileOrigine = getFile(FILE_NAME_ORIGINE);
        fileCorrige = getFile(FILE_NAME_CORRIGE);
    }

    @Test
    public void testDoJob() throws Exception {

        job.doJob(fileOrigine);

        FileAssert.assertEquals(fileCorrige, fileOrigine);

    }
}
