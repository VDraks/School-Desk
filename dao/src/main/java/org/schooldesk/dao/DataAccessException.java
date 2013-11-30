package org.schooldesk.dao;

public class DataAccessException extends Exception {
	public DataAccessException(String messageFormat, Object... args) {
		super(String.format(messageFormat, args));
	}

	public DataAccessException(Throwable cause, String messageFormat, Object... args) {
		super(String.format(messageFormat, args), cause);
	}
}
