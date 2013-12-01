package org.schooldesk.dao.hibernateimpl;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.schooldesk.core.UserCore;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IUserDao;
import org.schooldesk.dto.IUser;
import org.schooldesk.dto.impl.UserDto;

import java.util.List;


public class UserDao extends AbstractDao<IUser> implements IUserDao {
	public UserDao(SessionFactory sessionFactory) {
		super(sessionFactory, UserCore.class);
	}

	@Override
	public IUser createDto() {
		return UserDto.createNew();
	}

	@Override
	@SuppressWarnings("unchecked")
	public IUser loadByEmail(String email) throws DataAccessException {
		List<UserCore> users = getSession().createCriteria(UserCore.class)
				.add(Restrictions.eq("email", email))
				.list();
		UserCore user = users == null ? null : users.iterator().next();
		return user == null ? null : user.toDto();
	}
}
