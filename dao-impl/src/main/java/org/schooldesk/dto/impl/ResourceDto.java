package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;


public abstract class ResourceDto extends AbstractDto implements IResource {
	private Long rightId;
	private String name;

	@Deprecated
	@UsedForMapping
	public ResourceDto() {}

	@Override
	public Long getRightId() {
		return rightId;
	}

	@Override
	public void setRightId(Long rightId) {
		this.rightId = rightId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
