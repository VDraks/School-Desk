package org.schooldesk.dto.impl;

import java.util.*;

import org.schooldesk.dto.*;

public class GroupDto extends Dto implements IGroupDto{
	private String name;
	private Set<Long> rightIds;

	@Deprecated @UsedForMapping
	public GroupDto() {}

	public static GroupDto createNew() {
		GroupDto dto = new GroupDto();
		dto.setRightIds(new HashSet<Long>());
		return dto;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Set<Long> getRightIds() {
		return rightIds;
	}

	@Override
	public void setRightIds(Set<Long> rightIds) {
		this.rightIds = rightIds;
	}
}
