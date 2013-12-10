package org.schooldesk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.schooldesk.dao.*;
import org.schooldesk.dto.ICourse;
import org.schooldesk.dto.IEducationStage;
import org.schooldesk.dto.IGroup;
import org.schooldesk.dto.IRight;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class GroupCoreSUR_Test extends CAbstractTest{

	private String groupName;
	private String right1Code;
	private String right2Code;

	private Long groupId;
	private Long right1Id;
	private Long right2Id;

	@Before
	public void before() throws DataAccessException {

		right1Code = "right 1 code";
		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		IRight right = rightDao.createDto(right1Code);
		right = rightDao.save(right);
		right1Id = right.getId();

		right2Code = "right 2 code";
		right = rightDao.createDto(right2Code);
		right = rightDao.save(right);
		right2Id = right.getId();

		groupName = "group name";
		IGroupDao groupDao = getFactory().getDao(IGroupDao.class);
		IGroup group = groupDao.createDto();
		group.setName(groupName);
		group.setRightIds(new HashSet<Long>(Arrays.asList(right1Id, right2Id)));
		group = groupDao.save(group);
		groupId = group.getId();
	}

	@Test
	public void test() throws DataAccessException {

		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		IRight right = rightDao.loadById(right1Id);

		assertTrue("Right 1 was not loaded", right != null);
		assertEquals("Right 1 code was corrupted during saving", right1Code, right.getCode());

		right = rightDao.loadById(right2Id);

		assertTrue("Right 2 was not loaded", right != null);
		assertEquals("Right 2 code was corrupted during saving", right2Code, right.getCode());

		IGroupDao groupDao = getFactory().getDao(IGroupDao.class);
		IGroup group = groupDao.loadById(groupId);

		assertTrue("Group was not loaded", group != null);
		assertEquals("Group name was corrupted during saving", groupName, group.getName());
	}

	@After
	public void after() throws DataAccessException {

		IRightDao rightDao = getFactory().getDao(IRightDao.class);
//		rightDao.delete(right1Id);

		IGroupDao groupDao = getFactory().getDao(IGroupDao.class);
		groupDao.delete(groupId);

		assertEquals("Right 2 was not successfully deleted", null, rightDao.loadById(right2Id));
		assertEquals("Right 1 was not successfully deleted", null, rightDao.loadById(right1Id));
		assertTrue("Right 1 or right 2 was not successfully deleted", rightDao.loadAll().isEmpty());
		assertEquals("Group was not successfully deleted", null, groupDao.loadById(groupId));
		assertTrue("Group was not successfully deleted", groupDao.loadAll().isEmpty());
	}
}
