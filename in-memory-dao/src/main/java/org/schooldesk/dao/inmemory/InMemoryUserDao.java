package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IUserDao;
import org.schooldesk.dao.inmemory.inmemory.Database;
import org.schooldesk.dto.IDto;
import org.schooldesk.dto.IUser;
import org.schooldesk.dto.inmemory.InMemoryUser;


public class InMemoryUserDao extends InMemoryAbstractDao<IUser> implements IUserDao {
	@Override
	public IUser createDto() {
		return new InMemoryUser();
	}

	@Override
	public IUser loadByEmail(String email) throws DataAccessException {
		for (IDto dto : Database.users.values()) {
			IUser user = (IUser) dto;
			if (user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}
}
