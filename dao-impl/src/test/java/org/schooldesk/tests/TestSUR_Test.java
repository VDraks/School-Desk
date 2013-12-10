package org.schooldesk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class TestSUR_Test extends CAbstractTest{

	private Long testId;
	private Long testQuestion1Id;
	private Long testQuestion2Id;
	private Long rightId;

	private String testName;
	private String testQuestion1Name;
	private String testQuestion2Name;
	private String rightName;
	private String rightCode;

	@Before
	public void before() throws DataAccessException {

		testQuestion1Name = "test question 1 name";
		ITestQuestionDao testQuestionDao = getFactory().getDao(ITestQuestionDao.class);
		ITestQuestion testQuestion = testQuestionDao.createDto();
		testQuestion.setName(testQuestion1Name);
		testQuestion = testQuestionDao.save(testQuestion);
		testQuestion1Id = testQuestion.getId();

		testQuestion2Name = "test question 2 name";
		testQuestion = testQuestionDao.createDto();
		testQuestion.setName(testQuestion2Name);
		testQuestion = testQuestionDao.save(testQuestion);
		testQuestion2Id = testQuestion.getId();

		rightName = "right name";
		rightCode = "right code";
		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		IRight right = rightDao.createDto(rightCode);
		right = rightDao.save(right);
		rightId = right.getId();

		testName = "test name";
		ITestDao testDao = getFactory().getDao(ITestDao.class);
		ITest test = testDao.createDto();
		test.setName(testName);
		test = testDao.save(test);
		testId = test.getId();
	}

	@Test
	public void test() throws DataAccessException {

		ITestQuestionDao testQuestionDao = getFactory().getDao(ITestQuestionDao.class);
		ITestQuestion testQuestion = testQuestionDao.loadById(testQuestion1Id);

		assertTrue("Test question 1 was not loaded", testQuestion != null);
		assertEquals("Test question 1 name was corrupted during saving", testQuestion1Name, testQuestion.getName());

		testQuestion = testQuestionDao.loadById(testQuestion2Id);

		assertTrue("Test question 2 was not loaded", testQuestion != null);
		assertEquals("Test question 2 name was corrupted during saving", testQuestion2Name, testQuestion.getName());

		ITestDao testDao = getFactory().getDao(ITestDao.class);
		ITest test = testDao.loadById(testId);

		assertTrue("Test was not loaded", test != null);
		assertEquals("Test name was corrupted during saving", testName, test.getName());

		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		IRight right = rightDao.loadById(rightId);

		assertTrue("Right was not loaded", right != null);
		assertEquals("Right code was corrupted during saving", rightCode, right.getCode());
	}

	@After
	public void after() throws DataAccessException {

//		ICourseDao courseDao = getFactory().getDao(ICourseDao.class);
////		courseDao.delete(course1Id);
//
//		IEducationStageDao educationStageDao = getFactory().getDao(IEducationStageDao.class);
//		educationStageDao.delete(educationStageId);
//
//		assertEquals("Course 2 was not successfully deleted", null, courseDao.loadById(course2Id));
//		assertEquals("Course 1 was not successfully deleted", null, courseDao.loadById(course1Id));
//		assertTrue("Course 1 and course 2 was not successfully deleted", courseDao.loadAll().isEmpty());
//		assertEquals("Education stage was not successfully deleted", null, educationStageDao.loadById(educationStageId));
//		assertTrue("Education stage was not successfully deleted", educationStageDao.loadAll().isEmpty());
	}
}
