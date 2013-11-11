package org.schooldesk.dao.impl;

import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

public class UserDao extends Dao<IUserDto> implements IUserDao{
	//	public UserDao(API api) {
//		super(api);
//	}

	@Override
	public IUserDto createNew() {
		return UserDto.createNew();
	}
}
