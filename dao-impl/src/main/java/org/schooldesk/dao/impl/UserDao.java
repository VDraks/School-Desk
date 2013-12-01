package org.schooldesk.dao.impl;

import org.hibernate.SessionFactory;
import org.schooldesk.core.UserCore;
import org.schooldesk.dao.IUserDao;
import org.schooldesk.dto.IUser;
import org.schooldesk.dto.impl.UserDto;

public class UserDao extends AbstractDao<IUser> implements IUserDao
{
	public UserDao(SessionFactory sessionFactory)
	{
		super(sessionFactory, UserCore.class);
	}

	@Override
	public IUser createDto()
	{
		return UserDto.createNew();
	}
}
