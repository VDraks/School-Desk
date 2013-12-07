package org.schooldesk.core;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
public class GroupCore extends AbstractCore {
	private String name;

	@OneToMany
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
	protected GroupDto mapDto(AbstractDto dto) {
		GroupDto groupDto = (GroupDto) super.mapDto(dto);
		groupDto.setName(getName());
		groupDto.setRightIds(getIds(getRights()));
		return groupDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		GroupDto groupDto = (GroupDto) dto;
		setName(groupDto.getName());
		setRights(new HashSet<>(coreApi.loadByIds(RightCore.class, groupDto.getRightIds())));
	}
}
