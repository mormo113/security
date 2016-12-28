package job.fortify;

import job.AbstractJobTest;
import jobs.fortify.JobA1XmlExternalEntityInjection;
import junitx.framework.FileAssert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by b010qds on 21/12/2016.
 */
public class JobA1XmlExternalEntityInjectionTest extends AbstractJobTest {

	private static final String FILE_NAME = "JobA1XmlExternalEntityInjection_Origine.java";
	private static final String FILE_NAME2 = "JobA1XmlExternalEntityInjection_Corrige.java";

	@Before
	public void setUp() throws IOException {
		job = new JobA1XmlExternalEntityInjection();
		fileOrigine = getFile(FILE_NAME);
		fileCorrige = getFile(FILE_NAME2);
	}

	@Test
	public void testDoJob()throws Exception {

		job.doJob(fileOrigine);

		FileAssert.assertEquals(fileCorrige, fileOrigine);

	}
}
