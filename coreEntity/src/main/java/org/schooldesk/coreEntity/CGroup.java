package org.schooldesk.coreEntity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CGroup
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	
	@OneToMany
	private Set<CRight> rights;

	/**
	 * @deprecated exists for mapping purposes
	 */
	public CGroup()
	{
	}

	public static CGroup createNew()
	{
		CGroup coreEntity = new CGroup();
		coreEntity.setRightIds(new HashSet<CRight>());
		return coreEntity;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<CRight> getRights()
	{
		return rights;
	}

	public void setRightIds(Set<CRight> rights)
	{
		this.rights = rights;
	}
}
