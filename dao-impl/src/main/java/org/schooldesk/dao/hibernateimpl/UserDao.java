package org.schooldesk.dao.hibernateimpl;

import com.google.common.collect.*;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;
import org.schooldesk.hibernateobjects.*;

import java.util.*;


public class UserDao extends AbstractDao<IUser> implements IUserDao {
	public UserDao(CoreApi coreApi) {
		super(coreApi, IUser.class, UserCore.class);
	}

	@Override
	public IUser createDto() {
		return UserDto.createNew();
	}

	@Override
	@SuppressWarnings("unchecked")
	public IUser loadByEmail(String email) throws DataAccessException {
		try {
			List<UserCore> users = getApi().getSession().createCriteria(UserCore.class)
					.add(Restrictions.eq("email", email))
					.list();
			UserCore user = Iterables.getOnlyElement(users, null);
			return user == null ?
			       null :
			       user.toDto();
		}
		catch (HibernateException ex) {
			throw new DataAccessException(ex, "Couldn't get user with e-mail \"%s\"", email);
		}
	}

	@Override
	protected UserCore createCoreObject(IUser entity) throws HibernateException {
		UserCore result = new UserCore();
		result.fromDto(entity, getApi());
		return result;
	}
}
