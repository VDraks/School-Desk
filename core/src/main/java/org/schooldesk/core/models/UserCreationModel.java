package org.schooldesk.core.models;

import org.schooldesk.dto.IUser;

import java.util.Set;


public class UserCreationModel {
	private String firstName;
	private String middleName;
	private String lastName;

	private String email;

	private Set<Long> groupIds;

	public UserCreationModel(String firstName, String middleName, String lastName, String email, Set<Long> groupIds) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.groupIds = groupIds;
	}

	public void applyTo(IUser user) {
		user.setFirstName(getFirstName());
		user.setMiddleName(getMiddleName());
		user.setLastName(getLastName());
		user.setEmail(getEmail());
		user.setGroupIds(getGroupIds());
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Long> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(Set<Long> groupIds) {
		this.groupIds = groupIds;
	}
}
