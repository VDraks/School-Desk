package org.schooldesk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IRightDao;
import org.schooldesk.dao.ITestAnswerDao;
import org.schooldesk.dto.IRight;
import org.schooldesk.dto.ITestAnswer;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class TestAnswerSUR_Test extends CAbstractTest{

	private String answer;
	private String answerName;
	private String rightCode;

	private Long testAnswerId;
	private Long rightId;

	@Before
	public void before() throws DataAccessException {

		rightCode = "right code";
		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		IRight right = rightDao.createDto(rightCode);
		right = rightDao.save(right);
		rightId = right.getId();

		answer = "answer";
		answerName = "answer name";
		ITestAnswerDao testAnswerDao = getFactory().getDao(ITestAnswerDao.class);
		ITestAnswer testAnswer = testAnswerDao.createDto();
		testAnswer.setAnswer(answer);
		testAnswer.setName(answerName);
		testAnswer.setRightId(rightId);
		testAnswer = testAnswerDao.save(testAnswer);
		testAnswerId = testAnswer.getId();
	}

	@Test
	public void test() throws DataAccessException {

		IRightDao rightDao = getFactory().getDao(IRightDao.class);
		IRight right = rightDao.loadById(rightId);

		assertTrue("Right was not loaded", right != null);
		assertEquals("Right code was corrupted during saving", rightCode, right.getCode());

		ITestAnswerDao testAnswerDao = getFactory().getDao(ITestAnswerDao.class);
		ITestAnswer testAnswer = testAnswerDao.loadById(testAnswerId);

		assertTrue("Test answer was not loaded", testAnswer != null);
		assertEquals("Test answer answer was corrupted during saving", answer, testAnswer.getAnswer());
		assertEquals("Test answer name was corrupted during saving", answerName, testAnswer.getName());
	}

	@After
	public void after() throws DataAccessException {

		ITestAnswerDao testAnswerDao = getFactory().getDao(ITestAnswerDao.class);
		testAnswerDao.delete(testAnswerId);

		IRightDao rightDao = getFactory().getDao(IRightDao.class);

		assertEquals("Test answer was not successfully deleted", null, testAnswerDao.loadById(testAnswerId));
		assertTrue("Test answer was not successfully deleted", testAnswerDao.loadAll().isEmpty());
		assertEquals("Right was not successfully deleted", null, rightDao.loadById(rightId));
		assertTrue("Right was not successfully deleted", rightDao.loadAll().isEmpty());
	}
}