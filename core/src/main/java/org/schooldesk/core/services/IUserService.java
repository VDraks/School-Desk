package org.schooldesk.core.services;

import org.schooldesk.core.models.UserModel;

import java.util.Set;


public interface IUserService extends IService {
	long createUser(UserModel userModel);
	void updateUser(UserModel userModel);
	void deleteUser(long userId);
}
