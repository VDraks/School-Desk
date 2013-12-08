package org.schooldesk.core.services.impl;

import org.schooldesk.core.models.*;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.core.services.ServiceException;
import org.schooldesk.core.services.impl.utils.IPasswordGenerator;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IDaoFactory;
import org.schooldesk.dao.IUserDao;
import org.schooldesk.dto.IUser;


class UserServiceImpl extends AbstractServiceImpl implements IUserService {
	private final IPasswordGenerator passwordGenerator;
	private final IUserDao userDao;

	public UserServiceImpl(IDaoFactory daoFactory, IPasswordGenerator passwordGenerator) {
		super(daoFactory);
		this.passwordGenerator = passwordGenerator;
		userDao = getDaoFactory().getDao(IUserDao.class);
	}

	@Override
	public long createUser(UserCreationModel userCreationModel) throws DataAccessException {
		final IUser user = userDao.createDto();
		userCreationModel.applyTo(user);
		user.setPassword(passwordGenerator.generate());

		final IUser savedUser = userDao.save(user);
		return savedUser.getId();
	}

	@Override
	public void updateUser(UserUpdateModel userUpdateModel) throws ServiceException, DataAccessException {
		final IUser user = userDao.loadById(userUpdateModel.getId());
		if (user == null) {
			throw new ServiceException("Couldn't find user with id '%d'", userUpdateModel.getId());
		}

		userUpdateModel.applyTo(user);
		userDao.update(user);
	}

	@Override
	public void deleteUser(long userId) throws DataAccessException {
		userDao.delete(userId);
	}

	@Override
	public UserModel getUserById(Long id) throws DataAccessException {
		IUserDao userDao = getDaoFactory().getDao(IUserDao.class);
		IUser user = userDao.loadById(id);
		return  user == null ? null : new UserModel(user);
	}

	@Override
	public UserModel fetchUserByEmail(UserFetchByEmailModel userFetchModel) throws DataAccessException {
		if (userFetchModel.getEmail() == null) {
			throw new IllegalArgumentException("userFetchModel have no email specified");
		}

		final IUser user = userDao.loadByEmail(userFetchModel.getEmail());

		return (user == null) ? null : new UserModel(user);
	}

	@Override
	public Long checkCredentials(UserCredentialModel userCredentialModel) throws DataAccessException {
		final IUser user = userDao.loadByEmail(userCredentialModel.getEmail());

		boolean validPassword = user.getPassword().equals(userCredentialModel.getPassword());
		return validPassword ? user.getId() : null;
	}
}
