package org.schooldesk.intergrationtests;

import org.junit.Test;
import org.schooldesk.core.models.UserModel;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.dao.DataAccessException;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserServiceTest extends AbstractTest {
	@Test
	public void CanCreateUser() throws DataAccessException {
		final IUserService userService = getServiceFactory().getService(IUserService.class);

		final Set<Long> groupIds = new HashSet<>();
		final UserModel userModel = new UserModel(null, "John", "William", "Smith", "jonh@example.com", groupIds);

		final long createdUserId = userService.createUser(userModel);

		assertThat(createdUserId, is(notNullValue()));
	}
}
