package org.schooldesk.core.services.impl;

import org.schooldesk.core.models.UserCredentialModel;
import org.schooldesk.core.models.UserFetchByEmailModel;
import org.schooldesk.core.models.UserModel;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.core.services.ServiceException;
import org.schooldesk.core.services.impl.utils.IPasswordGenerator;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IDaoFactory;
import org.schooldesk.dao.IUserDao;
import org.schooldesk.dto.IUser;


class UserServiceImpl extends AbstractServiceImpl implements IUserService {
	private final IPasswordGenerator passwordGenerator;

	public UserServiceImpl(IDaoFactory daoFactory, IPasswordGenerator passwordGenerator) {
		super(daoFactory);
		this.passwordGenerator = passwordGenerator;
	}

	@Override
	public long createUser(UserModel userModel) throws DataAccessException {
		final IUserDao userDao = getDaoFactory().getDao(IUserDao.class);
		final IUser user = userDao.createDto();
		userModel.applyTo(user);
		user.setPassword(passwordGenerator.generate());

		final IUser savedUser = userDao.save(user);
		return savedUser.getId();
	}

	@Override
	public void updateUser(UserModel userModel) throws ServiceException, DataAccessException {
		final IUserDao userDao = getDaoFactory().getDao(IUserDao.class);
		final IUser user = userDao.loadById(userModel.getId());
		if (user == null) {
			throw new ServiceException("Couldn't find user with id '%d'", userModel.getId());
		}

		userModel.applyTo(user);
		userDao.update(user);
	}

	@Override
	public void deleteUser(long userId) throws DataAccessException {
		final IUserDao userDao = getDaoFactory().getDao(IUserDao.class);
		userDao.delete(userId);
	}

	@Override
	public UserModel fetchUserByEmail(UserFetchByEmailModel userFetchModel) throws DataAccessException {
		if (userFetchModel.getEmail() == null) {
			throw new IllegalArgumentException("userFetchModel have no email specified");
		}

		final IUserDao userDao = getDaoFactory().getDao(IUserDao.class);
		final IUser user = userDao.loadByEmail(userFetchModel.getEmail());

		return (user == null) ? null : new UserModel(user);
	}

	@Override
	public Long checkCredentials(UserCredentialModel userCredentialModel) throws DataAccessException {
		final IUserDao userDao = getDaoFactory().getDao(IUserDao.class);
		final IUser user = userDao.loadByEmail(userCredentialModel.getEmail());

		boolean validPassword = user.getPassword().equals(userCredentialModel.getPassword());
		return validPassword ? user.getId() : null;
	}
}
