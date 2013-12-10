package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.IUser;

import java.util.HashSet;
import java.util.Set;


public class InMemoryUser extends InMemoryAbstractDto implements IUser {
	private String firstName;
	private String middleName;
	private String lastName;

	private String email;
	private String password;

	private Set<Long> groupIds;

	private Long educationStageId;

	@Deprecated
	public InMemoryUser() {}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getMiddleName() {
		return middleName;
	}

	@Override
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Set<Long> getGroupIds() {
		return groupIds;
	}

	@Override
	public void setGroupIds(Set<Long> groupIds) {
		this.groupIds = groupIds;
	}

	@Override
	public Long getEducationStageId() {
		return educationStageId;
	}

	public void setEducationStageId(Long educationStageId) {
		this.educationStageId = educationStageId;
	}
}
