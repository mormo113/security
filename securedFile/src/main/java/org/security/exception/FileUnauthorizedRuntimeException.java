package org.security.exception;

/**
 * Created by b010qds on 23/12/2016.
 */
public class FileUnauthorizedRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1194379627892201068L;

	FileUnauthorizedRuntimeException() {
		super();
	}
	FileUnauthorizedRuntimeException(String message) {
		super(message);
	}
	FileUnauthorizedRuntimeException(Throwable t) {
		super(t);
	}
	FileUnauthorizedRuntimeException(String message,Throwable t) {
		super(message,t);
	}
}
