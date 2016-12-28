package org.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class SecuredLogger implements org.slf4j.Logger {

	/*
	 * Cleanup string representation of an object
	 */
	private String cleanup(Object object) {
		if (object==null) return null;

		String string = object.toString();

		String newString = string.replaceAll("\\r\\n|\\n|\\r", " ");
		if (!string.equals(newString)) newString+=" (changed)";

		return newString;
	}

	/*
	 * Cleanup string representation of an objects table
	 */
	private String[] cleanup(Object[] objects) {
		if (objects==null) return null;

		String strings[] = new String[objects.length];

		int i=0;
		for (Object object: objects) {
			String string = object.toString();

			String newString = string.replaceAll("\\r\\n|\\n|\\r", " ");
			if (!string.equals(newString)) newString+=" (changed)";

			strings[i++]=newString;
		}

		return strings;
	}

	/*
	 * The target logger
	 */
	Logger logger;

	SecuredLogger(@SuppressWarnings("rawtypes") Class clazz) {
		logger = LoggerFactory.getLogger(clazz);
	}

	SecuredLogger(@SuppressWarnings("rawtypes") String name) {
		logger = LoggerFactory.getLogger(name);
	}

	@Override
	public void debug(String arg0) {
		logger.debug(cleanup(arg0));
	}

	@Override
	public void debug(String arg0, Object arg1) {
		logger.debug(cleanup(arg0),cleanup(arg1));
	}

	@Override
	public void debug(String arg0, Throwable throwable1) {
		logger.debug(cleanup(arg0),throwable1);
	}

	@Override
	public void debug(Marker marker0, String arg1) {
		logger.debug(marker0,cleanup(arg1));
	}

	@Override
	public void debug(String arg0, Object[] arg1) {
		logger.debug(cleanup(arg0),cleanup(arg1));
	}

	@Override
	public void debug(String arg0, Object arg1, Object arg2) {
		logger.debug(cleanup(arg0),cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void debug(Marker marker0, String arg1, Object arg2) {
		logger.debug(marker0,cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void debug(Marker marker0, String arg1, Object[] arg2) {
		logger.debug(marker0,cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void debug(Marker marker0, String arg1, Throwable throwable2) {
		logger.debug(marker0,cleanup(arg1),throwable2);
	}

	@Override
	public void debug(Marker marker0, String arg1, Object arg2, Object arg3) {
		logger.debug(marker0,cleanup(arg1),cleanup(arg2),cleanup(arg2));
	}

	@Override
	public void error(String arg0) {
		logger.error(cleanup(arg0));
	}

	@Override
	public void error(String arg0, Object arg1) {
		logger.error(cleanup(arg0),cleanup(arg1));
	}

	@Override
	public void error(String arg0, Object[] arg1) {
		logger.error(cleanup(arg0),cleanup(arg1));
	}

	@Override
	public void error(String arg0, Throwable throwable1) {
		logger.error(cleanup(arg0),throwable1);
	}

	@Override
	public void error(Marker marker0, String arg1) {
		logger.error(marker0,cleanup(arg1));
	}

	@Override
	public void error(String arg0, Object arg1, Object arg2) {
		logger.error(cleanup(arg0),cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void error(Marker marker0, String arg1, Object arg2) {
		logger.error(marker0,cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void error(Marker marker0, String arg1, Object[] arg2) {
		logger.error(marker0,cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void error(Marker marker0, String arg1, Throwable throwable2) {
		logger.error(marker0,cleanup(arg1),throwable2);
	}

	@Override
	public void error(Marker marker0, String arg1, Object arg2, Object arg3) {
		logger.error(marker0,cleanup(arg1),cleanup(arg2),cleanup(arg3));
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public void info(String arg0) {
		logger.info(cleanup(arg0));
	}

	@Override
	public void info(String arg0, Object arg1) {
		logger.info(cleanup(arg0),cleanup(arg1));
	}

	@Override
	public void info(String arg0, Object[] arg1) {
		logger.info(cleanup(arg0),cleanup(arg1));
	}

	@Override
	public void info(String arg0, Throwable throwable1) {
		logger.info(cleanup(arg0),throwable1);
	}

	@Override
	public void info(Marker marker0, String arg1) {
		logger.info(marker0,cleanup(arg1));
	}

	@Override
	public void info(String arg0, Object arg1, Object arg2) {
		logger.info(cleanup(arg0),cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void info(Marker marker0, String arg1, Object arg2) {
		logger.info(marker0,cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void info(Marker marker0, String arg1, Object[] arg2) {
		logger.info(marker0,cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void info(Marker marker0, String arg1, Throwable throwable2) {
		logger.info(marker0,cleanup(arg1),throwable2);
	}

	@Override
	public void info(Marker marker0, String arg1, Object arg2, Object arg3) {
		logger.info(marker0,cleanup(arg1),cleanup(arg2),cleanup(arg3));
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isDebugEnabled(Marker marker0) {
		return logger.isDebugEnabled(marker0);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public boolean isErrorEnabled(Marker marker0) {
		return logger.isErrorEnabled(marker0);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public boolean isInfoEnabled(Marker marker0) {
		return logger.isInfoEnabled(marker0);
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public boolean isTraceEnabled(Marker marker0) {
		return logger.isTraceEnabled(marker0);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public boolean isWarnEnabled(Marker marker0) {
		return logger.isWarnEnabled(marker0);
	}

	@Override
	public void trace(String arg0) {
		logger.trace(cleanup(arg0));
	}

	@Override
	public void trace(String arg0, Object arg1) {
		logger.trace(cleanup(arg0),cleanup(arg1));
	}

	@Override
	public void trace(String arg0, Object[] arg1) {
		logger.trace(cleanup(arg0),cleanup(arg1));
	}

	@Override
	public void trace(String arg0, Throwable throwable1) {
		logger.trace(cleanup(arg0),throwable1);
	}

	@Override
	public void trace(Marker marker0, String arg1) {
		logger.trace(marker0,cleanup(arg1));
	}

	@Override
	public void trace(String arg0, Object arg1, Object arg2) {
		logger.trace(cleanup(arg0),cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void trace(Marker marker0, String arg1, Object arg2) {
		logger.trace(marker0,cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void trace(Marker marker0, String arg1, Object[] arg2) {
		logger.trace(marker0,cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void trace(Marker marker0, String arg1, Throwable throwable2) {
		logger.trace(marker0,cleanup(arg1),throwable2);
	}

	@Override
	public void trace(Marker marker0, String arg1, Object arg2, Object arg3) {
		logger.trace(marker0,cleanup(arg1),cleanup(arg2),cleanup(arg3));
	}

	@Override
	public void warn(String arg0) {
		logger.warn(cleanup(arg0));
	}

	@Override
	public void warn(String arg0, Object arg1) {
		logger.warn(cleanup(arg0),cleanup(arg1));
	}

	@Override
	public void warn(String arg0, Object[] arg1) {
		logger.warn(cleanup(arg0),cleanup(arg1));
	}

	@Override
	public void warn(String arg0, Throwable throwable1) {
		logger.warn(cleanup(arg0),throwable1);
	}

	@Override
	public void warn(Marker marker0, String arg1) {
		logger.warn(marker0,cleanup(arg1));
	}

	@Override
	public void warn(String arg0, Object arg1, Object arg2) {
		logger.warn(cleanup(arg0),cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void warn(Marker marker0, String arg1, Object arg2) {
		logger.warn(marker0,cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void warn(Marker marker0, String arg1, Object[] arg2) {
		logger.warn(marker0,cleanup(arg1),cleanup(arg2));
	}

	@Override
	public void warn(Marker marker0, String arg1, Throwable throwable2) {
		logger.warn(marker0,cleanup(arg1),throwable2);
	}

	@Override
	public void warn(Marker marker0, String arg1, Object arg2, Object arg3) {
		logger.warn(marker0,cleanup(arg1),cleanup(arg2),cleanup(arg3));
	}
}
