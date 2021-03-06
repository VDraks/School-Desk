package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "users")
public class UserCore extends AbstractCore {
	private String firstName;
	private String middleName;
	private String lastName;

	private String email;
	private String password;

	private Set<GroupCore> groups;
	private EducationStageCore educationStage;

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

	@OneToMany(mappedBy = "user")
	public Set<GroupCore> getGroups() {
		return groups;
	}

	public void setGroups(Set<GroupCore> groups) {
		this.groups = groups;
	}

	@OneToOne(cascade = CascadeType.REMOVE)
	@PrimaryKeyJoinColumn
	public EducationStageCore getEducationStage() {
		return educationStage;
	}

	public void setEducationStage(EducationStageCore educationStage) {
		this.educationStage = educationStage;
	}

	@Override
	@SuppressWarnings("deprecation")
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
		userDto.setEducationStageId(getId(getEducationStage()));
		return userDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		IUser user = (UserDto) dto;
		setFirstName(user.getFirstName());
		setMiddleName(user.getMiddleName());
		setLastName(user.getLastName());
		setEmail(user.getEmail());
		setPassword(user.getPassword());
		setGroups(new HashSet<>(coreApi.loadByIds(GroupCore.class, user.getGroupIds())));
		setEducationStage(coreApi.loadById(EducationStageCore.class, user.getEducationStageId()));
	}
}
