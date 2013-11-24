package org.schooldesk.dto.impl;

import org.schooldesk.dto.IUser;

import java.util.HashSet;
import java.util.Set;

public class UserDto extends AbstractDto implements IUser {
	private String firstName;
	private String middleName;
	private String lastName;

	private String login;
	private String password;

	private Set<Long> groupIds;

	/**
	 * @deprecated exists for mapping purposes
	 */
	public UserDto() {}

	public static UserDto createNew()
	{
		UserDto dto = new UserDto();
		dto.setGroupIds(new HashSet<Long>());
		return dto;
	}

	@Override
	public String getFirstName()
	{
		return firstName;
	}

	@Override
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	@Override
	public String getMiddleName()
	{
		return middleName;
	}

	@Override
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	@Override
	public String getLastName()
	{
		return lastName;
	}

	@Override
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Override
	public String getLogin()
	{
		return login;
	}

	@Override
	public void setLogin(String login)
	{
		this.login = login;
	}

	@Override
	public String getPassword()
	{
		return password;
	}

	@Override
	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public Set<Long> getGroupIds()
	{
		return groupIds;
	}

	@Override
	public void setGroupIds(Set<Long> groupIds)
	{
		this.groupIds = groupIds;
	}
}
