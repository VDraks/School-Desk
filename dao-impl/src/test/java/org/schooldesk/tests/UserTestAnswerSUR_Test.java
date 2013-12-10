package org.schooldesk.tests;

import com.google.common.collect.*;
import org.junit.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;

import java.util.*;

import static junit.framework.Assert.*;


public class UserTestAnswerSUR_Test extends CAbstractTest {

	private Long userTestAnswerId;

	private Long questionId;
	private Set<Long> answerIds;

	@Before
	public void before() throws DataAccessException {
		ITestQuestion testQuestion = getFactory().getDao(ITestQuestionDao.class).createDto();
		testQuestion.setQuestion("question?");
		testQuestion.setName("question");
		testQuestion.setType(TestQuestionType.SINGLE_ANSWER_QUESTION);
		questionId = getFactory().getDao(ITestQuestionDao.class).save(testQuestion).getId();

		ITestAnswer testAnswer = getFactory().getDao(ITestAnswerDao.class).createDto();
		testAnswer.setName("answer");
		testAnswer.setAnswer("answer!");
		answerIds = Sets.newHashSet(getFactory().getDao(ITestAnswerDao.class).save(testAnswer).getId());

		IUserTestAnswer userTestAnswer = getFactory().getDao(IUserTestAnswerDao.class).createDto();
		userTestAnswer.setAnswerIds(answerIds);
		userTestAnswer.setQuestionId(questionId);
		userTestAnswerId = getFactory().getDao(IUserTestAnswerDao.class).save(userTestAnswer).getId();
	}

	@Test
	public void test() throws DataAccessException {
		IUserTestAnswer userTestAnswer = getFactory().getDao(IUserTestAnswerDao.class).loadById(userTestAnswerId);

		assertEquals("Stored answers aren't equal", answerIds, userTestAnswer.getAnswerIds());
		assertEquals("Stored question is wrong", questionId, userTestAnswer.getQuestionId());
	}

	@After
	public void after() throws DataAccessException {
//      getFactory().getDao(ITestQuestionDao.class).delete(questionId);
//		getFactory().getDao(ITestAnswerDao.class).delete(Iterables.getOnlyElement(answerIds));
//		getFactory().getDao(IUserTestAnswerDao.class).delete(userTestAnswerId);
	}
}
