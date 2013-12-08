package org.schooldesk.client;

public class Response {
	private boolean success;
	private String message;
	private Object data;

	Response() {
	}

	Response(boolean success, String message, Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}