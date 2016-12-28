package org.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SecuredFileInputStream {

	private FileInputStream fileInputStream;

	public SecuredFileInputStream(String name) throws FileNotFoundException {
		// This will check if file is allowed
		fileInputStream = new FileInputStream(new SecuredFile(name).getFile());
	}

	public SecuredFileInputStream(SecuredFile file) throws FileNotFoundException {
		fileInputStream = new FileInputStream(file.getFile());
	}

	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}
}
