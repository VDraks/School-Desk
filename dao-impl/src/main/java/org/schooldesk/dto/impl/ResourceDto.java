package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;

public abstract class ResourceDto extends Dto implements IResourceDto {
	private Long rightId;
	private String name;

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
