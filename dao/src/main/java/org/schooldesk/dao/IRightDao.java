package org.schooldesk.dao;

import org.schooldesk.dto.IRightDto;


public interface IRightDao extends IDao<IRightDto> {
	public IRightDto createNew(String code);
}
