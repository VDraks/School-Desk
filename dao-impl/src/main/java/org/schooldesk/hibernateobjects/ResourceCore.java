package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;


@Entity
public abstract class ResourceCore extends AbstractCore {

	protected RightCore right;
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(mappedBy = "resource", cascade = {CascadeType.REMOVE})
	public RightCore getRight() {
		return right;
	}

	public void setRight(RightCore right) {
		this.right = right;
	}

	@Override
	protected ResourceDto mapDto(AbstractDto dto) {
		ResourceDto resourceDto = (ResourceDto) super.mapDto(dto);
		resourceDto.setRightId(getRight() == null ? null : getRight().getId());
		resourceDto.setName(getName());
		return resourceDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		IResource resource = (IResource) dto;
		setRight(coreApi.loadById(RightCore.class, resource.getRightId()));
		setName(resource.getName());
	}
}
