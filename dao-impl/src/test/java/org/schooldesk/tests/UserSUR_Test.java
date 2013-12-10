package org.schooldesk.tests;

import com.google.common.collect.*;
import org.junit.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;

import java.util.*;

import static junit.framework.Assert.*;


public class UserSUR_Test extends CAbstractTest {

	private Long userId;

	private String firstName;
	private String middleName;
	private String lastName;

	private String email;
	private String password;

	private Set<Long> groupIds;

	private Long educationStageId;

	@Before
	public void before() throws DataAccessException {
		IGroup group = getFactory().getDao(IGroupDao.class).createDto();
		group.setName("group name");
		groupIds = Sets.newHashSet(getFactory().getDao(IGroupDao.class).save(group).getId());

		IEducationStage educationStage = getFactory().getDao(IEducationStageDao.class).createDto();
		educationStage.setName("education stage");
		educationStageId = getFactory().getDao(IEducationStageDao.class).save(educationStage).getId();

		IUser user = getFactory().getDao(IUserDao.class).createDto();
		user.setFirstName(firstName = "first name");
		user.setMiddleName(middleName = "middle name");
		user.setLastName(lastName = "last name");
		user.setEmail(email = "e-mail");
		user.setPassword(password = "password");
		user.setGroupIds(groupIds);
		user.setEducationStageId(educationStageId);
		userId = getFactory().getDao(IUserDao.class).save(user).getId();
	}

	@Test
	public void test() throws DataAccessException {
		IUser user = getFactory().getDao(IUserDao.class).loadById(userId);

		assertTrue("Stored first name is wrong", firstName.equals(user.getFirstName()));
		assertTrue("Stored middle name is wrong", middleName.equals(user.getMiddleName()));
		assertTrue("Stored last name is wrong", lastName.equals(user.getLastName()));

		assertTrue("Stored e-mail is wrong", email.equals(user.getEmail()));
		assertTrue("Stored password is wrong", password.equals(user.getPassword()));

		assertEquals("Stored groups aren't equal", groupIds, user.getGroupIds());

		assertEquals("Stored education stage is wrong", educationStageId, user.getEducationStageId());
	}

	@Test
	public void testLoadByEmail() throws DataAccessException {
		assertEquals("User loaded by e-mail is different", userId, getFactory().getDao(IUserDao.class).loadByEmail(email).getId());
	}

	@After
	public void after() throws DataAccessException {

//      getFactory().getDao(IGroupDao.class).delete(Iterables.getOnlyElement(groupIds));
//		getFactory().getDao(IEducationStageDao.class).delete(educationStageId);
//		getFactory().getDao(IUserDao.class).delete(userId);
	}
}
