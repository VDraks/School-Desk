package org.schooldesk.core;

import java.util.*;

import javax.persistence.*;

import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


@Entity
public class UserCore extends AbstractCore {
	private String firstName;
	private String middleName;
	private String lastName;

	private String email;
	private String password;

	@OneToMany
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

	public Set<GroupCore> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupCore> groups) {
		this.groups = groups;
	}

	@Override
	public UserDto toDto() {
		return mapDto(new UserDto());
	}

	@Override
	protected UserDto mapDto(AbstractDto dto) {
		UserDto userDto = (UserDto) super.mapDto(dto);
		userDto.setFirstName(getFirstName());
		userDto.setMiddleName(getMiddleName());
		userDto.setLastName(getLastName());
		userDto.setEmail(getEmail());
		userDto.setPassword(getPassword());
		userDto.setGroupIds(getIds(getGroups()));
		return userDto;
	}

	@Override
	public void fromDto(IDto dto) {
		UserDto userDto = (UserDto) dto;
		setFirstName(userDto.getFirstName());
		setMiddleName(userDto.getMiddleName());
		setLastName(userDto.getLastName());
		setEmail(userDto.getEmail());
		setPassword(userDto.getPassword());
//		setGroups(userDto.getGroupIds()); // FIXME
	}
}
