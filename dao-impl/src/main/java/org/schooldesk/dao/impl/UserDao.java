package org.schooldesk.dao.impl;

import org.schooldesk.dao.IUserDao;
import org.schooldesk.dto.IUserDto;
import org.schooldesk.dto.impl.UserDto;

public class UserDao extends Dao<IUserDto> implements IUserDao{
	//	public UserDao(API api) {
//		super(api);
//	}

	@Override
	public IUserDto createNew() {
		return UserDto.createNew();
	}
}
