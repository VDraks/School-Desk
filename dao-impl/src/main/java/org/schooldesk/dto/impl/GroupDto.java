package org.schooldesk.dto.impl;

import org.schooldesk.dto.IGroup;

import java.util.HashSet;
import java.util.Set;

public class GroupDto extends AbstractDto implements IGroup {
	private String name;
	private Set<Long> rightIds;

	/**
	 * @deprecated exists for mapping purposes
	 */
	public GroupDto() {}

	public static GroupDto createNew()
	{
		GroupDto dto = new GroupDto();
		dto.setRightIds(new HashSet<Long>());
		return dto;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public Set<Long> getRightIds()
	{
		return rightIds;
	}

	@Override
	public void setRightIds(Set<Long> rightIds)
	{
		this.rightIds = rightIds;
	}
}
