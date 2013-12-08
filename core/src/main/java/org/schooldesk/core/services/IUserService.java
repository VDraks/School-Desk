package org.schooldesk.core.services;

import org.schooldesk.core.models.*;
import org.schooldesk.dao.DataAccessException;


public interface IUserService extends IService {
	long createUser(UserCreationModel userCreationModel) throws DataAccessException;
	void updateUser(UserUpdateModel userUpdateModel) throws ServiceException, DataAccessException;
	void deleteUser(long userId) throws DataAccessException;

	UserModel getUserById(Long id) throws DataAccessException;
	UserModel fetchUserByEmail(UserFetchByEmailModel userFetchModel) throws DataAccessException;

	Long checkCredentials(UserCredentialModel userCredentialModel) throws DataAccessException;
}