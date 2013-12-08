package org.schooldesk.core.models;

public class UserCredentialModel {
	private String email;
	private String password;

	public UserCredentialModel() {
	}

	public UserCredentialModel(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
