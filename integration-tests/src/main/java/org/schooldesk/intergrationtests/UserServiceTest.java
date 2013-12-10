package org.schooldesk.intergrationtests;

import org.junit.Before;
import org.junit.Test;
import org.schooldesk.core.models.UserCreationModel;
import org.schooldesk.core.models.UserCredentialModel;
import org.schooldesk.core.models.UserModel;
import org.schooldesk.core.models.UserUpdateModel;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.core.services.ServiceException;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IUserDao;
import org.schooldesk.dto.IUser;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class UserServiceTest extends AbstractTest {
	private IUserService userService;
	private IUserDao userDao;

	@Before
	public void initUserServiceTest() {
		userService = getServiceFactory().getService(IUserService.class);
		userDao = getDaoFactory().getDao(IUserDao.class);
	}

	@Test
	public void canCreateUser() throws DataAccessException {
		final UserCreationModel userCreationModel = createDefaultUserCreationModel();
		final long createdUserId = userService.createUser(userCreationModel);

		final UserModel createdUser = userService.getUserById(createdUserId);
		assertEquals(userCreationModel.getFirstName(), createdUser.getFirstName());
		assertEquals(userCreationModel.getMiddleName(), createdUser.getMiddleName());
		assertEquals(userCreationModel.getLastName(), createdUser.getLastName());
		assertEquals(userCreationModel.getEmail(), createdUser.getEmail());
		assertEquals(userCreationModel.getGroupIds(), createdUser.getGroupIds());
	}

	@Test
	public void canUpdateUser() throws DataAccessException, ServiceException {
		final UserCreationModel userCreationModel = createDefaultUserCreationModel();
		final long createdUserId = userService.createUser(userCreationModel);

		final UserUpdateModel userUpdateModel = new UserUpdateModel(
				createdUserId,
				"Will",
				"Adam",
				"Smith",
				"smith@example.com",
				"password",
				new HashSet<Long>()
		);
		userService.updateUser(userUpdateModel);

		final IUser updatedUser = userDao.loadById(createdUserId);
		assertEquals(userUpdateModel.getFirstName(), updatedUser.getFirstName());
		assertEquals(userUpdateModel.getMiddleName(), updatedUser.getMiddleName());
		assertEquals(userUpdateModel.getLastName(), updatedUser.getLastName());
		assertEquals(userUpdateModel.getEmail(), updatedUser.getEmail());
		assertEquals(userUpdateModel.getPassword(), updatedUser.getPassword());
		assertEquals(userUpdateModel.getGroupIds(), updatedUser.getGroupIds());
	}

	@Test
	public void canCheckUserCredentials() throws DataAccessException, ServiceException {
		final UserCreationModel userCreateModel = createDefaultUserCreationModel();
		final long createdUserId = userService.createUser(userCreateModel);

		final UserUpdateModel userUpdateModel = new UserUpdateModel(
				createdUserId,
				userCreateModel.getFirstName(),
				userCreateModel.getMiddleName(),
				userCreateModel.getLastName(),
				userCreateModel.getEmail(),
				"password",
				userCreateModel.getGroupIds()
		);
		userService.updateUser(userUpdateModel);

		Long authUserId = userService.checkCredentials(new UserCredentialModel(userUpdateModel.getEmail(), userUpdateModel.getPassword()));
		assertEquals(authUserId.longValue(), createdUserId);

		Long nonAuthUserId = userService.checkCredentials(new UserCredentialModel(userUpdateModel.getEmail(), "wrong password"));
		assertNull(nonAuthUserId);
	}

	private static UserCreationModel createDefaultUserCreationModel() {
		Set<Long> groupIds = new HashSet<>();
		return new UserCreationModel("John", "William", "Smith", "jonh@example.com", groupIds, null);
	}
}
