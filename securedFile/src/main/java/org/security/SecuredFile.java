package org.security;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.security.exception.FileUnauthorizedException;
import org.security.exception.FileUnauthorizedRuntimeException;
import org.slf4j.Logger;

public class SecuredFile {

	private static final long serialVersionUID = -3545427256864327747L;

	private File file;

	private static String authorizedPathLocations[] = {};
	private final static String forbiddenPathLocations[] = {"/app/","/bin/","/boot/","/dev/","/etc/","/lib/","/log/","/lib32/","/lib64/","/proc/","/root/","/run/","/srv/","/usr/","/var/","C:\\Program Files\\","C:\\Program Files (x86)\\", "C:\\Windows\\","D:\\Program Files\\","D:\\Program Files (x86)\\", "D:\\Windows\\"};

	/*
	{
		String authorizedPathsString = System.getenv("AUTHORIZED_PATH_LOCATIONS");
		if (authorizedPathsString!=null) authorizedPathLocations = authorizedPathsString.split(",");
	}
	*/

	private Logger logger = SecuredLoggerFactory.getLogger(SecuredFile.class);

	public SecuredFile(File parent, String child) {
		file = new File(parent, child);
	}

	public SecuredFile(String pathname) {
		file  = new File(pathname);
	}

	public SecuredFile(String parent, String child) {
		file  = new File(parent, child);
	}

	public SecuredFile(URI uri) {
		file  = new File(uri);
	}

	public File getFile() {
		try {
			doCanonicalPathCheck();
		} catch (FileUnauthorizedException fue) {
			throw new FileUnauthorizedRuntimeException(fue);
		}
		return file;
	}

	public File getFileOrThrow() throws FileUnauthorizedException {
		doCanonicalPathCheck();
		return file;
	}

	private void doCanonicalPathCheck() throws FileUnauthorizedException {
		try {
			String canonicalPath = file.getCanonicalPath();
			if (canonicalPath!=null) {
				// OK if pathname starts with an authorized Location
				for (String authorizedPath: authorizedPathLocations) {
					if (canonicalPath.startsWith(authorizedPath)) {
						return;
					}
				}

				// KO if pathname starts with a forbidden Location
				for (String forbiddenPath: forbiddenPathLocations) {
					if (canonicalPath.startsWith(forbiddenPath)) {
						// TODO : the move the logger to Exception class
						logger.error("Access to file {} unauthorized", canonicalPath);
						//throw new FileUnauthorizedRuntimeException("Access to '"+canonicalPath+"' unauthorized");
						throw new FileUnauthorizedException("Access to file unauthorized");
					}
				}
			}
		} catch (IOException e) {
			throw new FileUnauthorizedException(e);
		}
	}

	public static String assertValidFilename(String filename) {

		File file = new SecuredFile(filename).getFile();

		if (file != null) {
			return filename;
		}
		return null;
	}

	public static String assertValidFilenameOrThrow(String filename) throws FileUnauthorizedException {

		File file = new SecuredFile(filename).getFileOrThrow();

		if (file != null) {
			return filename;
		}
		return null;
	}
}
