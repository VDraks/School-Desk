package org.schooldesk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.hibernateobjects.TestAnswerCore;
import org.schooldesk.hibernateobjects.TestCore;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class TestQuestionSUR_Test extends CAbstractTest{

	private String testAnswer1Name;
	private String testAnswer1;
	private String testAnswer2Name;
	private String testAnswer2;
	private String testQuestionName;
	private String rightCode;
	private String question;
	private TestQuestionType testQuestionType;

	private Long testAnswer1Id;
	private Long testAnswer2Id;
	private Long testQuestionId;
	private Long rightId;

	@Before
	public void before() throws DataAccessException {

		testAnswer1Name = "test answer 1 name";
		testAnswer1 = "test answer 1";
		ITestAnswerDao testAnswerDao = getFactory().getDao(ITestAnswerDao.class);
		ITestAnswer testAnswer = testAnswerDao.createDto();
		testAnswer.setAnswer(testAnswer1);
		testAnswer.setName(testAnswer1Name);
		testAnswer = testAnswerDao.save(testAnswer);
		testAnswer1Id = testAnswer.getId();

		testAnswer2Name = "test answer 2 name";
		testAnswer2 = "test answer 2";
		testAnswer = testAnswerDao.createDto();
		testAnswer.setAnswer(testAnswer2);
		testAnswer.setName(testAnswer2Name);
		testAnswer = testAnswerDao.save(testAnswer);
		testAnswer2Id = testAnswer.getId();

		rightCode = "right code";
		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		IRight right = rightDao.createDto(rightCode);
		right = rightDao.save(right);
		rightId = right.getId();

		testQuestionName = "test question name";
		question = "question";
		testQuestionType = TestQuestionType.SINGLE_ANSWER_QUESTION;
		ITestQuestionDao testQuestionDao = getFactory().getDao(ITestQuestionDao.class);
		ITestQuestion testQuestion = testQuestionDao.createDto();
		testQuestion.setName(testQuestionName);
		testQuestion.setQuestion(question);
		testQuestion.setRightId(rightId);
		testQuestion.setAnswerIds(new HashSet<Long>(Arrays.asList(testAnswer1Id)));
		testQuestion.setCorrectAnswerIds(new HashSet<Long>(Arrays.asList(testAnswer2Id)));
		testQuestion.setType(testQuestionType);
		testQuestion = testQuestionDao.save(testQuestion);
		testQuestionId = testQuestion.getId();
	}

	@Test
	public void test() throws DataAccessException {

		ITestAnswerDao testAnswerDao = getFactory().getDao(ITestAnswerDao.class);
		ITestAnswer testAnswer = testAnswerDao.loadById(testAnswer1Id);

		assertTrue("Test answer 1 was not loaded", testAnswer != null);
		assertEquals("Test answer 1 answer was corrupted during saving", testAnswer1, testAnswer.getAnswer());
		assertEquals("Test answer 1 name was corrupted during saving", testAnswer1Name, testAnswer.getName());

		testAnswer = testAnswerDao.loadById(testAnswer2Id);

		assertTrue("Test answer 2 was not loaded", testAnswer != null);
		assertEquals("Test answer 2 answer was corrupted during saving", testAnswer2, testAnswer.getAnswer());
		assertEquals("Test answer 2 name was corrupted during saving", testAnswer2Name, testAnswer.getName());

		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		IRight right = rightDao.loadById(rightId);

		assertTrue("Right was not loaded", right != null);
		assertEquals("Right code was corrupted during saving", rightCode, right.getCode());

		ITestQuestionDao testQuestionDao = getFactory().getDao(ITestQuestionDao.class);
		ITestQuestion testQuestion = testQuestionDao.loadById(testQuestionId);

		assertTrue("Test question was not loaded", testQuestion != null);
		assertEquals("Test question name was corrupted during saving", testQuestionName, testQuestion.getName());
		assertEquals("Test question question was corrupted during saving", question, testQuestion.getQuestion());
		assertEquals("Test question type was corrupted during saving", testQuestionType, testQuestion.getType());
	}

	@After
	public void after() throws DataAccessException {

		ITestQuestionDao testQuestionDao = getFactory().getDao(ITestQuestionDao.class);
		testQuestionDao.delete(testQuestionId);

		ITestAnswerDao testAnswerDao = getFactory().getDao(ITestAnswerDao.class);
		IRightDao rightDao = getFactory().getDao(IRightDao.class);
//
		assertEquals("Test answer 1 was not successfully deleted", null, testAnswerDao.loadById(testAnswer1Id));
		assertEquals("Test answer 2 was not successfully deleted", null, testAnswerDao.loadById(testAnswer2Id));
		assertTrue("Test answer 1 and test answer 2 was not successfully deleted", testAnswerDao.loadAll().isEmpty());
		assertEquals("Right was not successfully deleted", null, rightDao.loadById(rightId));
		assertTrue("Right was not successfully deleted", rightDao.loadAll().isEmpty());
		assertEquals("Test question was not successfully deleted", null, testQuestionDao.loadById(testQuestionId));
		assertTrue("Test question was not successfully deleted", testQuestionDao.loadAll().isEmpty());
	}
}