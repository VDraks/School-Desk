package org.schooldesk.hibernateobjects;

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
	@SuppressWarnings("deprecated")
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
		IGroup group = (IGroup) dto;
		setName(group.getName());
		setRights(new HashSet<>(coreApi.loadByIds(RightCore.class, group.getRightIds())));
	}
}
