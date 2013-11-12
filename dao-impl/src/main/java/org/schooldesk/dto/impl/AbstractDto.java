package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;

public abstract class AbstractDto implements IDto{
	private Long id;

	@Override
	public Long getId()
	{
		return id;
    }

	@UsedForMapping
	public void setId(Long id)
	{
		this.id = id;
	}
}
