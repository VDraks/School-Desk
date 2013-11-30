package org.schooldesk.core.services.impl;

import org.schooldesk.core.models.*;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;

import java.util.*;


class TestServiceImpl extends AbstractServiceImpl implements ITestService {
	interface IEducationStageDao extends IDao<IEducationStage> {}
	final private IDao<IEducationStage> educationStageDao;

	interface ICourseDao extends IDao<ICourse> {}
	final private IDao<ICourse> courseDao;

	interface ICourseSectionDao extends IDao<ICourseSection> {}
	final private IDao<ICourseSection> courseSectionDao;

	interface ITestDao extends IDao<ITest> {}
	final private IDao<ITest> testDao;

	interface ITestQuestionDao extends IDao<ITestQuestion> {}
	final private IDao<ITestQuestion> testQuestionDao;

	interface ITestAnswerDao extends IDao<ITestAnswer> {}
	final private IDao<ITestAnswer> testAnswerDao;

	public TestServiceImpl(IDaoFactory daoFactory) {
		super(daoFactory);
		educationStageDao = getDaoFactory().getDao(IEducationStageDao.class);
		courseDao = getDaoFactory().getDao(ICourseDao.class);
		courseSectionDao = getDaoFactory().getDao(ICourseSectionDao.class);
		testDao = getDaoFactory().getDao(ITestDao.class);
		testQuestionDao = getDaoFactory().getDao(ITestQuestionDao.class);
		testAnswerDao = getDaoFactory().getDao(ITestAnswerDao.class);
	}

	@Override
	public Set<CourseModel> getCourses(long educationStageId) {
		Set<CourseModel> courseModels = null;
		try {
			EducationStageModel educationStageModel = loadEducationStageModel(educationStageId);
			courseModels = educationStageModel.getCourseModels();
		}
		catch (DataAccessException e) {
			e.printStackTrace();  //TODO change body of catch statement use File | Settings | File Templates.
		}
		return courseModels;
	}

	@Override
	public Set<CourseSectionModel> getCourseSections(long courseId) {
		Set<CourseSectionModel> courseSectionModels = null;
		try {
			CourseModel courseModel = loadCourseModel(courseId);
			courseSectionModels = courseModel.getSectionModels();
		}
		catch (DataAccessException e) {
			e.printStackTrace();  //TODO change body of catch statement use File | Settings | File Templates.
		}
		return courseSectionModels;
	}

	@Override
	public TestModel getTest(long courseSectionId) {
		TestModel testModel = null;
		try {
			CourseSectionModel courseSectionModel = loadCourseSectionModel(courseSectionId);
			testModel = courseSectionModel.getTestModel();
		}
		catch (DataAccessException e) {
			e.printStackTrace();  //TODO change body of catch statement use File | Settings | File Templates.
		}
		return testModel;
	}

	@Override
	public TestResultModel validateUserTestPassing(UserTestPassing userTestPassing) {
		TestResultModel testResultModel = null;
		try {
			ITest test = testDao.loadById(userTestPassing.getTestId());

			testResultModel = new TestResultModel();
			testResultModel.setTestName(test.getName());

			List<TestResultModel.ValidatedTestAnswer> validatedTestAnswers = new ArrayList<>();
			for (UserTestPassing.UserAnswer userAnswer : userTestPassing.getUserAnswers()) {
				TestResultModel.ValidatedTestAnswer validatedTestAnswer = new TestResultModel.ValidatedTestAnswer();

				ITestQuestion testQuestion = testQuestionDao.loadById(userAnswer.getQuestionId());
				validatedTestAnswer.setQuestion(testQuestion.getQuestion());

				ITestAnswer testUserAnswer = testAnswerDao.loadById(userAnswer.getAnswerId());
				validatedTestAnswer.setUserAnswer(testUserAnswer.getAnswer());

				ITestAnswer testCorrectAnswer = testAnswerDao.loadById(testQuestion.getCorrectAnswerId());
				validatedTestAnswer.setCorrectAnswer(testCorrectAnswer.getAnswer());

				validatedTestAnswers.add(validatedTestAnswer);
			}
			testResultModel.setValidatedTestAnswers(validatedTestAnswers);

		}
		catch (DataAccessException e) {
			e.printStackTrace();  //TODO change body of catch statement use File | Settings | File Templates.
		}

		return testResultModel;
	}

	private EducationStageModel loadEducationStageModel(long id) throws DataAccessException{
		EducationStageModel educationStageModel = new EducationStageModel();
		IEducationStage educationStage = educationStageDao.loadById(id);

		educationStageModel.setId(id);
		educationStageModel.setName(educationStage.getName());

		Set<CourseModel> courseModels = new HashSet<>();
		for (long courseId : educationStage.getCourseIds()) {
			courseModels.add(loadCourseModel(courseId));
		}
		educationStageModel.setCourseModels(courseModels);

		return educationStageModel;
	}

	private CourseModel loadCourseModel(long id) throws DataAccessException{
		CourseModel courseModel = new CourseModel();
		ICourse course = courseDao.loadById(id);

		courseModel.setId(id);
		courseModel.setName(course.getName());

		Set<CourseSectionModel> courseSectionModels = new HashSet<>();

		for (ICourseSection courseSection : course.getCourseSections()) {
			courseSectionModels.add(loadCourseSectionModel(courseSection.getId()));
		}
		courseModel.setSectionModels(courseSectionModels);

		return courseModel;
	}

	private CourseSectionModel loadCourseSectionModel(long id) throws DataAccessException{
		CourseSectionModel courseSectionModel = new CourseSectionModel();
		ICourseSection courseSection = courseSectionDao.loadById(id);

		courseSectionModel.setId(id);
		courseSectionModel.setName(courseSection.getName());
		courseSectionModel.setTestModel(loadTestModel(courseSection.getId()));

		return courseSectionModel;
	}

	private TestModel loadTestModel(long id) throws DataAccessException{
		TestModel testModel = new TestModel();
		ITest test = testDao.loadById(id);

		testModel.setId(id);
		testModel.setName(test.getName());

		Set<TestQuestionModel> testQuestionModels = new HashSet<>();
		for (long testQuestionId : test.getTestQuestionIds()) {
			testQuestionModels.add(loadTestQuestionModel(testQuestionId));
		}
		testModel.setQuestions(testQuestionModels);

		return testModel;
	}

	private TestQuestionModel loadTestQuestionModel(long id) throws DataAccessException{
		TestQuestionModel testQuestionModel = new TestQuestionModel();
		ITestQuestion testQuestion = testQuestionDao.loadById(id);

		testQuestionModel.setId(id);
		testQuestionModel.setQuestion(testQuestion.getQuestion());

		Set<TestAnswerModel> answerModels = new HashSet<>();
		for (long answerId : testQuestion.getAnswerIds()) {
			answerModels.add(loadTestAnswerModel(answerId));
		}
		testQuestionModel.setAnswers(answerModels);

		return testQuestionModel;
	}

	private TestAnswerModel loadTestAnswerModel(long id) throws DataAccessException{
		TestAnswerModel testAnswerModel = new TestAnswerModel();
		ITestAnswer testAnswer = testAnswerDao.loadById(id);

		testAnswerModel.setId(id);
		testAnswerModel.setAnswer(testAnswer.getAnswer());

		return testAnswerModel;
	}
}
