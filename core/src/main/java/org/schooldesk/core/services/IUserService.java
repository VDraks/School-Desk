package org.schooldesk.core.services;

import org.schooldesk.core.models.UserCredentialModel;
import org.schooldesk.core.models.UserFetchByEmailModel;
import org.schooldesk.core.models.UserModel;
import org.schooldesk.dao.DataAccessException;


public interface IUserService extends IService {
	long createUser(UserModel userModel) throws DataAccessException;
	void updateUser(UserModel userModel) throws ServiceException, DataAccessException;
	void deleteUser(long userId) throws DataAccessException;

	UserModel getUserById(Long id) throws DataAccessException;
	UserModel fetchUserByEmail(UserFetchByEmailModel userFetchModel) throws DataAccessException;

	Long checkCredentials(UserCredentialModel userCredentialModel) throws DataAccessException;
}