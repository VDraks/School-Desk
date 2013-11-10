package org.schooldesk.dao;

import org.schooldesk.dto.*;


public interface IUserDao extends IDao<IUserDto> {
	public IUserDto createNew();
}
