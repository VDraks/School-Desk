package org.schooldesk.core.services;

public class ServiceException extends Exception {
	public ServiceException(String messageFormat, Object... args) {
		super(String.format(messageFormat, args));
	}

	public ServiceException(Throwable cause, String messageFormat, Object... args) {
		super(String.format(messageFormat, args), cause);
	}
}
