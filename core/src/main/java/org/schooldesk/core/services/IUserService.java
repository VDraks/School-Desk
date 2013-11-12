package org.schooldesk.core.services;

import org.schooldesk.core.models.UserCredentialModel;
import org.schooldesk.core.models.UserFetchModel;
import org.schooldesk.core.models.UserModel;


public interface IUserService extends IService {
	long createUser(UserModel userModel);
	void updateUser(UserModel userModel);
	void deleteUser(long userId);

	UserModel fetchUser(UserFetchModel userFetchModel);

	boolean checkCredentials(UserCredentialModel userCredentialModel);
}