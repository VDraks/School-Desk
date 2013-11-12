package org.schooldesk.dao.impl;

import org.schooldesk.dto.*;

public interface IDtoable
{
	Long getId();
	void setId(Long id);
	IDto toDto();
	void fromDto(IDto dto);
}