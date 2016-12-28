package tools;

import jobs.IJob;
import jobs.sonar.JobEquals;
import org.eclipse.jface.text.BadLocationException;
import updater.execption.UpdaterException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by b010cli on 01/04/2016.
 */
public class BrowseDirectory {

    private static final LoggerScren LOGGER = new LoggerScren(JobEquals.class.getName());

    private static List<String> listRepoNotTo = new ArrayList<String>(
        Arrays.asList("test", "generated-sources", "target", "tools"));

    public static final String SEPARATOR = ",";

    public static List<String> getListRepoNotTo() {
        return listRepoNotTo;
    }

    public static void setListRepoNotTo(List<String> listRepoNotTo) {
        BrowseDirectory.listRepoNotTo = listRepoNotTo;
    }

    public static void doJob(String LienRepo, IJob job) {
        File dossier = new File(LienRepo);
        File[] contenu = dossier.listFiles();
        for (int i = 0, n = contenu.length; i < n; i++) {
            if (contenu[i].isDirectory()) {
                if (isNotDirectoryOfTest(contenu[i].getAbsolutePath())) {
                    doJob(contenu[i].getAbsolutePath(), job);
                }
            } else if (contenu[i].isFile() && contenu[i].getName().endsWith(".java")) {
                try {
                    job.doJob(contenu[i]);
                }catch (UpdaterException e){
                    LOGGER.error("Probleme sur le Job : " + job.getClass().getName() + " sur la classe : " + contenu[i].getName()
                        + " Ligne : " + e.getMessage() ,e);
                }catch (IOException e){
                    LOGGER.error("Probleme sur le Job : " + job.getClass().getName() + " sur la classe : " + contenu[i].getName(),e);
                }catch (BadLocationException e){
                    LOGGER.error("Probleme sur le Job : " + job.getClass().getName() + " sur la classe : " + contenu[i].getName(),e);
                }catch (Exception e){
                    LOGGER.error("Probleme sur le Job : " + job.getClass().getName() + " sur la classe : " + contenu[i].getName(),e);
                }
            }
        }
    }

    private static boolean isNotDirectoryOfTest(String chemin ){
        for (String repoNotTo:listRepoNotTo){
            if (chemin.contains(repoNotTo)){
                return false;
            }
        }
        return true;
    }

    public static String getListRepoNotToString() {
        String repoNotTo = "";
        for (String repo:listRepoNotTo){
            repoNotTo = repoNotTo + repo + SEPARATOR + " ";
        }
        return repoNotTo;
    }
}
