package org.security;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class SecuredFileReader {

	private FileReader fileReader;

	public SecuredFileReader(String pathname) throws FileNotFoundException {
		// This will check if file is allowed
		fileReader = new FileReader(new SecuredFile(pathname).getFile());
	}

	public SecuredFileReader(SecuredFile file) throws FileNotFoundException {
		fileReader = new FileReader(file.getFile());
	}

	public FileReader getFileReader() {
		return fileReader;
	}
}
