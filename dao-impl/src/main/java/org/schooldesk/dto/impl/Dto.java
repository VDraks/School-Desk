package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;

public abstract class Dto implements IDto{
	private Long id;

	@Deprecated @UsedForMapping
	public Dto() {}

	@Override
	public Long getId() {
		return id;
    }

	public void setId(Long id) {
		this.id = id;
	}
}
