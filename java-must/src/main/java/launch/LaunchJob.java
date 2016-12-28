package launch;

import jobs.AbstractJob;
import jobs.sonar.JobIfSequence;
import tools.BrowseDirectory;
import tools.Counter;

/**
 * Created by b010cli on 18/03/2016.
 */
public class LaunchJob {

    public static void main(String[] args) throws Exception {

        String repertoireATraite = "C:\\JTB\\workspaces\\\\FichierDeTest\\1314";
        //permet d'ecrire ou pas le resultat apres traitement
        //par defaut cette valeur est à false
        AbstractJob.WRITE_IN_FILE = false;
        BrowseDirectory.doJob(repertoireATraite, new JobIfSequence());
        System.out.print("count : " + Counter.getCounter());
    }

}
