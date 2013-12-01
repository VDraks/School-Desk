package org.schooldesk.dao;

import org.schooldesk.dto.IUser;


public interface IUserDao extends IDao<IUser> {
	IUser createDto();
	IUser loadByEmail(String email) throws DataAccessException;
}
