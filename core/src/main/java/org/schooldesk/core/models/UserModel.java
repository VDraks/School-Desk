package org.schooldesk.core.models;

import org.schooldesk.dto.IUser;

import java.util.Set;


public class UserModel {
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;

	private String email;

	private Set<Long> groupIds;

	private Long educationStageId;

	public UserModel(Long id, String firstName, String middleName, String lastName, String email, Set<Long> groupIds) {
		setId(id);
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		setEmail(email);
		setGroupIds(groupIds);
	}

	public UserModel(IUser user) {
		this(user.getId(), user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getEmail(), user.getGroupIds());
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

	public Set<Long> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(Set<Long> groupIds) {
		this.groupIds = groupIds;
	}

	public Long getEducationStageId() {
		return educationStageId;
	}

	public void setEducationStageId(Long educationStageId) {
		this.educationStageId = educationStageId;
	}
}
