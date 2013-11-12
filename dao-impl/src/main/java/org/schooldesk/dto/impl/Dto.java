package org.schooldesk.dto.impl;

import org.schooldesk.dto.IDto;

public abstract class Dto implements IDto{
	private Long id;

	@Override
	public Long getId()
	{
		return id;
    }

	public void setId(Long id)
	{
		this.id = id;
	}
}
