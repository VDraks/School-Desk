package org.schooldesk.dto.impl;

import org.schooldesk.dto.IResource;


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
	public void setRight(Long rightId) {
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
