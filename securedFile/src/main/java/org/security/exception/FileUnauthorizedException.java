package org.security.exception;

/**
 * Created by b010qds on 23/12/2016.
 */
public class FileUnauthorizedException extends Exception {

	private static final long serialVersionUID = -1194379627892201068L;

	FileUnauthorizedException() {
		super();
	}
	FileUnauthorizedException(String message) {
		super(message);
	}
	FileUnauthorizedException(Throwable t) {
		super(t);
	}
	FileUnauthorizedException(String message,Throwable t) {
		super(message,t);
	}
}
