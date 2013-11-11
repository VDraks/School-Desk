package org.schooldesk.coreEntity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CUser
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String firstName;
	private String middleName;
	private String lastName;

	private String login;
	private String password;

	@OneToMany
	private Set<CGroup> groups;

	/**
	 * @deprecated exists for mapping purposes
	 */
	public CUser()
	{
	}

	public static CUser createNew()
	{
		CUser coreEntity = new CUser();
		coreEntity.setGroups(new HashSet<CGroup>());
		return coreEntity;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Set<CGroup> getGroups()
	{
		return groups;
	}

	public void setGroups(Set<CGroup> groups)
	{
		this.groups = groups;
	}
}
