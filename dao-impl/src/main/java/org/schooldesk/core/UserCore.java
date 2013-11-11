package org.schooldesk.core;

import java.util.*;

import org.schooldesk.dto.impl.*;

public class UserCore extends CoreObject {
	private String firstName;
	private String middleName;
	private String lastName;

	private String login;
	private String password;

	private Set<GroupCore> groups;

	public UserCore() {}

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<GroupCore> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupCore> groups) {
		this.groups = groups;
	}

	@Override
	public UserDto toDto() {
		UserDto dto = new UserDto();
		dto.setId(getId());
		dto.setFirstName(getFirstName());
		dto.setMiddleName(getMiddleName());
		dto.setLastName(getLastName());
		dto.setLogin(getLogin());
		dto.setPassword(getPassword());
		dto.setGroupIds(getIds(getGroups()));
		return dto;
	}

	@Override
	public void fromDto(Dto dto) {
		UserDto userDto = (UserDto) dto;
		setFirstName(userDto.getFirstName());
		setMiddleName(userDto.getMiddleName());
		setLastName(userDto.getLastName());
		setLogin(userDto.getLogin());
		setPassword(userDto.getPassword());
//		setGroups(userDto.getGroupIds()); // FIXME
	}
}
