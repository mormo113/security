package job.sonar;

import job.AbstractJobTest;
import jobs.sonar.JobEquals;
import junitx.framework.FileAssert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by b010qds on 07/12/2016.
 */
public class JobEqualsTest extends AbstractJobTest {

    private static final String FILE_NAME_ORIGINE = "JobEquals_Origine.java";
    private static final String FILE_NAME_CORRIGE = "JobEquals_Corrige.java";

    @Before
    public void setUp() {
        job = new JobEquals();
        fileOrigine = getFile(FILE_NAME_ORIGINE);
        fileCorrige = getFile(FILE_NAME_CORRIGE);
    }

    @Test
    public void testDoJob() throws Exception {

        job.doJob(fileOrigine);

        FileAssert.assertEquals(fileCorrige, fileOrigine);

    }
}
