package org.schooldesk.core.models;

import org.schooldesk.dto.IUser;

import java.util.Set;


public class UserUpdateModel {
	private long id;
	private String firstName;
	private String middleName;
	private String lastName;

	private String email;
	private String password;

	private Set<Long> groupIds;

	public UserUpdateModel(long id, String firstName, String middleName, String lastName, String email, String password, Set<Long> groupIds) {
		setId(id);
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		setEmail(email);
		setPassword(password);
		setGroupIds(groupIds);
	}

	public void applyTo(IUser user) {
		user.setFirstName(getFirstName());
		user.setMiddleName(getMiddleName());
		user.setLastName(getLastName());
		user.setEmail(getEmail());
		user.setPassword(getPassword());
		user.setGroupIds(getGroupIds());
	}

	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Long> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(Set<Long> groupIds) {
		this.groupIds = groupIds;
	}
}
