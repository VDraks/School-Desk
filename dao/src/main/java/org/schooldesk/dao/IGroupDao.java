package org.schooldesk.dao;

import org.schooldesk.dto.IGroupDto;


public interface IGroupDao extends IDao<IGroupDto> {
	public IGroupDto createNew();
}
