package job.sonar;

import job.AbstractJobTest;
import jobs.sonar.JobComparSize;
import junitx.framework.FileAssert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by b010qds on 06/12/2016.
 */
public class JobComparSizeTest extends AbstractJobTest {

    private static final String FILE_NAME_ORIGINE = "JobComparSize_Origine.java";
    private static final String FILE_NAME_CORRIGE = "JobComparSize_Corrige.java";

    @Before
    public void setUp() {
        job = new JobComparSize();
        fileOrigine = getFile(FILE_NAME_ORIGINE);
        fileCorrige = getFile(FILE_NAME_CORRIGE);
    }

    @Test
    public void testDoJob() throws Exception {

        job.doJob(fileOrigine);

        FileAssert.assertEquals(fileCorrige, fileOrigine);

    }
}
