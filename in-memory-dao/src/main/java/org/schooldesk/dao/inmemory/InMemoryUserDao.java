package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IUserDao;
import org.schooldesk.dto.IUser;


public class InMemoryUserDao extends InMemoryAbstractDao<IUser> implements IUserDao {
	@Override
	public IUser createDto() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public IUser loadByEmail(String email) throws DataAccessException {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
