package org.schooldesk.core;

import java.util.*;

import org.schooldesk.dto.impl.*;

public class GroupCore extends CoreObject {
	private String name;
	private Set<RightCore> rights;

	public GroupCore() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<RightCore> getRights() {
		return rights;
	}

	public void setRights(Set<RightCore> rights) {
		this.rights = rights;
	}

	@Override
	public GroupDto toDto() {
		return mapDto(new GroupDto());
	}

	@Override
	protected GroupDto mapDto(Dto dto)
	{
		GroupDto groupDto = (GroupDto) super.mapDto(dto);
		groupDto.setName(getName());
		groupDto.setRightIds(getIds(getRights()));
		return groupDto;
	}

	@Override
	public void fromDto(Dto dto) {
		GroupDto groupDto = (GroupDto) dto;
		setName(groupDto.getName());
//		setRights(dtoGroup.getRightIds()); // FIXME
	}
}
