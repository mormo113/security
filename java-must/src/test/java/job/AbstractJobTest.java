package job;

import jobs.AbstractJob;
import jobs.IJob;
import org.junit.BeforeClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by b010qds on 05/12/2016.
 */
public class AbstractJobTest {

    protected static File fileOrigine;
    protected static File fileCorrige;
    protected IJob job;

    @BeforeClass
    public static void setUpOnce() throws IOException {
        AbstractJob.WRITE_IN_FILE = true;
    }

    protected static File getFile(String fileName) {
        ClassLoader classLoader = AbstractJobTest.class.getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }

    protected static void initializeModifiedFile() throws IOException {
        Files.copy(fileCorrige.toPath(), fileOrigine.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * this method return a diff between two Files
     * @param file1
     * @param file2
     * @return
     * @throws Exception
     */
    protected List<String> compare(File file1, File file2) throws Exception {

        BufferedReader br1 = null;
        BufferedReader br2 = null;
        String sCurrentLine;
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> tmpList = new ArrayList<>();
        try {
            br1 = new BufferedReader(new FileReader(file1));
            br2 = new BufferedReader(new FileReader(file2));
            while ((sCurrentLine = br1.readLine()) != null) {
                list1.add(sCurrentLine);
            }
            while ((sCurrentLine = br2.readLine()) != null) {
                list2.add(sCurrentLine);
            }
            tmpList = new ArrayList<>(list1);
            tmpList.removeAll(list2);
        } finally {
            try {
                if (br1 != null)
                    br1.close();

                if (br2 != null)
                    br2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return tmpList;
    }
}
