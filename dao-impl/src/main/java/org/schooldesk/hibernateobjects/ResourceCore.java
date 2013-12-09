package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;


@MappedSuperclass
public abstract class ResourceCore extends AbstractCore {
//	@OneToOne
//	private RightCore right;
	private String name;

//	public RightCore getRight() {
//		return right;
//	}
//
//	public void setRight(RightCore right) {
//		this.right = right;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected ResourceDto mapDto(AbstractDto dto) {
		ResourceDto resourceDto = (ResourceDto) super.mapDto(dto);
//		resourceDto.setRightId(getRight().getId());
		resourceDto.setName(getName());
		return resourceDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		IResource resource = (IResource) dto;
//		setRight(coreApi.loadById(RightCore.class, resource.getRightId()));
		setName(resource.getName());
	}
}
