import java.io.File;
import fr.generali.ccj.security.helpers.SecuredFile;

public class SomeClass {

	private void secureFileCase(String repertoireSource){
		File repertoire = new File(SecuredFile.assertValidFilename(repertoireSource));

		if (myVar == 4){
			String MyVariable;
			doSomething();
		} else
			doSomething();
	}

	private void secureFileReaderCase(String repertoireSrc){
		FileReader repertoire = new FileReader(SecuredFile.assertValidFilename(repertoireSrc));

		if (myVar == 4){
			String MyVariable;
			doSomething();
		} else
			doSomething();
	}

	private void secureFileInputStreamCase(){
		String repSource;
		FileInputStream repertoire = new FileInputStream(SecuredFile.assertValidFilename(repSource));

		if (myVar == 4){
			String MyVariable;
			doSomething();
		} else
			doSomething();
	}


	private void secureFileOutputStream(File filePath){
		File FileOutputStream = new FileOutputStream(filePath);

		if (myVar == 4)
			doSomething();
		else
			doSomething();
	}

	private void secureFileWriter(){
		File secondFilePath;
		FileWriter repertoire = new FileWriter(secondFilePath);

		if (myVar == 4)
			doSomething();
		else
			doSomething();
	}

	private void secureFileWriterWithTryBloc(){
		try {
			File secondFilePath;
			FileWriter repertoire = new FileWriter(secondFilePath);

			if (myVar == 4)
				doSomething();
			else
				doSomething();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void secureFileWriterWithForBloc(){
		for(SomeObject item : SomeObjectList){
			try {
				File secondFilePath;
				FileWriter repertoire = new FileWriter(secondFilePath);

				if (myVar == 4)
					doSomething();
				else
					doSomething();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void secureFileWriterWithIfBloc(File jarFile, File[] entryFiles){
		if(true){
			try {
				File[] secondFilePath;
				FileWriter repertoire = new FileWriter(secondFilePath);

				for (int i = 0; i < entryFiles.length; i++) {
					DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(entryFiles[i])));
					if (myVar == 4)
						doSomething();
					else
						doSomething();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		} else if(false){
			try {
				File elseFilePath;
				FileWriter repertoire = new FileWriter(elseFilePath);
			}catch (Exception e){
				e.printStackTrace();
			}
		} else{
			File secondFilePath;
			FileWriter repertoire = new FileWriter(secondFilePath);
		}
	}
}