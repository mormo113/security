package org.security;

import java.io.FileWriter;
import java.io.IOException;

public class SecuredFileWriter {

	private FileWriter fileWriter;

	public SecuredFileWriter(String pathname) throws IOException {
		// This will check if file is allowed
		fileWriter = new FileWriter(new SecuredFile(pathname).getFile());
	}

	public SecuredFileWriter(String pathname, boolean append) throws IOException {
		// This will check if file is allowed
		fileWriter = new FileWriter(new SecuredFile(pathname).getFile(), append);
	}

	public SecuredFileWriter(SecuredFile file) throws IOException {
		fileWriter = new FileWriter(file.getFile());
	}

	public SecuredFileWriter(SecuredFile file, boolean append) throws IOException {
		fileWriter = new FileWriter(file.getFile(),append);
	}

	public FileWriter getFileWriter() {
		return fileWriter;
	}
}
