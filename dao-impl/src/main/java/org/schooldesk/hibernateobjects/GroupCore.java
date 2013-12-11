package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "groups")
public class GroupCore extends AbstractCore {
	private String name;
	private Set<RightCore> rights;

	@UsedForMapping
	private UserCore user;

	public GroupCore() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "group", cascade = {CascadeType.REMOVE})
	public Set<RightCore> getRights() {
		return rights;
	}

	public void setRights(Set<RightCore> rights) {
		this.rights = rights;
	}

	@ManyToOne
	@UsedForMapping
	@SuppressWarnings("unused")
	public UserCore getUser() {
		return user;
	}

	@UsedForMapping
	@SuppressWarnings("unused")
	public void setUser(UserCore user) {
		this.user = user;
	}

	@Override
	@SuppressWarnings("deprecation")
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
