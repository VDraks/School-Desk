package org.schooldesk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IGroupDao;
import org.schooldesk.dao.IRightDao;
import org.schooldesk.dto.IGroup;
import org.schooldesk.dto.IRight;

import java.util.Arrays;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class RightCoreSUR_Test extends CAbstractTest{

	private String rightCode;

	private Long rightId;

	@Before
	public void before() throws DataAccessException {

		rightCode = "right code";
		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		IRight right = rightDao.createDto(rightCode);
		right = rightDao.save(right);
		rightId = right.getId();
	}

	@Test
	public void test() throws DataAccessException {

		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		IRight right = rightDao.loadById(rightId);

		assertTrue("Right was not loaded", right != null);
		assertEquals("Right code was corrupted during saving", rightCode, right.getCode());
	}

	@After
	public void after() throws DataAccessException {

		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		rightDao.delete(rightId);

		assertEquals("Right was not successfully deleted", null, rightDao.loadById(rightId));
		assertTrue("Right was not successfully deleted", rightDao.loadAll().isEmpty());
	}
}