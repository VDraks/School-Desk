package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.IDto;


public abstract class InMemoryAbstractDto implements IDto {
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
