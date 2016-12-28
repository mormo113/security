import java.io.File;

public class SomeClass {

	private void secureFileCase(String repertoireSource){
		BufferedWriter sortie = new BufferedWriter(new FileWriter(new File(SecuredFile.assertValidFilename(nomFichier)), true));
	}

}