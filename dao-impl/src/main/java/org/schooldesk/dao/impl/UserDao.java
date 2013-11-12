package org.schooldesk.dao.impl;

import org.hibernate.*;
import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

public class UserDao extends AbstractDao<IUserDto> implements IUserDao
{
	public UserDao(SessionFactory sessionFactory)
	{
		super(sessionFactory, UserCore.class);
	}

	@Override
	public IUserDto createNew()
	{
		return UserDto.createNew();
	}
}
