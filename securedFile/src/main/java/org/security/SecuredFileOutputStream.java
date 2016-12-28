package org.security;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SecuredFileOutputStream {

	private FileOutputStream fileOutputStream;

	public SecuredFileOutputStream(String name) throws FileNotFoundException {
		// This will check if file is allowed
		fileOutputStream = new FileOutputStream(new SecuredFile(name).getFile());
	}

	public SecuredFileOutputStream(String name, boolean append) throws FileNotFoundException  {
		// This will check if file is allowed
		fileOutputStream = new FileOutputStream(new SecuredFile(name).getFile(),append);
	}

	public SecuredFileOutputStream(SecuredFile file) throws FileNotFoundException  {
		fileOutputStream = new FileOutputStream(file.getFile());
	}

	public SecuredFileOutputStream(SecuredFile file, boolean append) throws FileNotFoundException  {
		fileOutputStream = new FileOutputStream(file.getFile(),append);
	}

	public FileOutputStream getFileOutputStream() {
		return fileOutputStream;
	}

}
