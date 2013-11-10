package org.schooldesk.dto;

import java.util.Set;


public interface IUserDto extends IDto {
	public String getFirstName();

	public void setFirstName(String firstName);

	public String getMiddleName();

	public void setMiddleName(String middleName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getLogin();

	public void setLogin(String login);

	public String getPassword();

	public void setPassword(String password);

	public Set<Long> getGroupIds();

	public void setGroupIds(Set<Long> groupIds);
}
