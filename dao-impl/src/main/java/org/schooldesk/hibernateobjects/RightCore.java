package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;


@Entity
public class RightCore extends AbstractCore {
	private String code;

	private ResourceCore resource;
	private GroupCore group;

	@ManyToOne
	public GroupCore getGroup() {
		return group;
	}

	public void setGroup(GroupCore group) {
		this.group = group;
	}

	@OneToOne
	public ResourceCore getResource() {
		return resource;
	}

	public void setResource(ResourceCore resource) {
		this.resource = resource;
	}

	public RightCore() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	@SuppressWarnings("deprecation")
	public RightDto toDto() {
		return mapDto(new RightDto());
	}

	@Override
	protected RightDto mapDto(AbstractDto dto) {
		RightDto rightDto = (RightDto) super.mapDto(dto);
		rightDto.setCode(getCode());
		return rightDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		IRight right = (IRight) dto;
		setCode(right.getCode());
	}
}
