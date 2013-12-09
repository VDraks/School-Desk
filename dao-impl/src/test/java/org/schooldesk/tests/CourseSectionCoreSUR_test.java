package org.schooldesk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.schooldesk.dao.*;
import org.schooldesk.dto.ICourse;
import org.schooldesk.dto.ICourseSection;
import org.schooldesk.dto.ITest;
import org.schooldesk.dto.ITestQuestion;
import org.schooldesk.dto.impl.TestDto;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class CourseSectionCoreSUR_test extends CAbstractTest{

	private Long courseSectionId;
	private Long testId;

	private String courseSectionName;
	private String testName;

	@Before
	public void before() throws DataAccessException{

		testName = "test name";
		ITestDao testDao = getFactory().getDao(ITestDao.class);
		ITest test = testDao.createDto();
		test.setName(testName);
		test = testDao.save(test);
		testId = test.getId();

		courseSectionName = "course section name";
		ICourseSectionDao courseSectionDao = getFactory().getDao(ICourseSectionDao.class);
		ICourseSection courseSection = courseSectionDao.createDto();
		courseSection.setName(courseSectionName);
		courseSection.setTestId(testId);
		courseSection = courseSectionDao.save(courseSection);
		courseSectionId = courseSection.getId();
	}

	@Test
	public void test() throws DataAccessException {

	  ITestDao testDao = getFactory().getDao(ITestDao.class);
	  ITest test = testDao.loadById(testId);

	  assertTrue("Test was not loaded", test != null);
	  assertEquals("Test name was corrupted during saving", testName, test.getName());

	  ICourseSectionDao courseSectionDao = getFactory().getDao(ICourseSectionDao.class);
	  ICourseSection courseSection = courseSectionDao.loadById(courseSectionId);

		assertTrue("Course section was not loaded", courseSection != null);
		assertEquals("Course section name was corrupted during saving", courseSectionName, courseSection.getName());
	}

	@After
	public void after() throws DataAccessException {

	  ICourseSectionDao courseSectionDao = getFactory().getDao(ICourseSectionDao.class);
		courseSectionDao.delete(courseSectionId);

		ITestDao testDao = getFactory().getDao(ITestDao.class);

		assertEquals("Course section was not successfully deleted", null, courseSectionDao.loadById(courseSectionId));
		assertTrue("Course section was not successfully deleted", courseSectionDao.loadAll().isEmpty());
		assertEquals("Test was not successfully deleted", null, testDao.loadById(testId));
		assertTrue("Test was not successfully deleted", testDao.loadAll().isEmpty());
	}
}
