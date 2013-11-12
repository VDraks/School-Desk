package org.schooldesk.dao.impl;

import org.schooldesk.dto.impl.*;

public interface Dtoable {
	Long getId();
	void setId(Long id);
	Dto toDto();
	void fromDto(Dto dto);
}
