package org.security;

/**
 * Created by b010qds on 23/12/2016.
 */
public class SecuredLoggerFactory {
	public static SecuredLogger getLogger(@SuppressWarnings("rawtypes") Class clazz) {
		return new SecuredLogger(clazz);
	}

	public static SecuredLogger getLogger(String string) {
		return new SecuredLogger(string);
	}
}
