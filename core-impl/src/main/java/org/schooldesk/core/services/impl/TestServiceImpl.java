package org.schooldesk.core.services.impl;

import org.schooldesk.core.models.*;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;

import java.util.*;


class TestServiceImpl extends AbstractServiceImpl implements ITestService {
	final private IEducationStageDao educationStageDao;
	final private ICourseDao courseDao;
	final private ICourseSectionDao courseSectionDao;
	final private ITestDao testDao;
	final private ITestQuestionDao testQuestionDao;
	final private ITestAnswerDao testAnswerDao;

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
	public Set<CourseModel> getCourses(Long educationStageId) throws DataAccessException {
		IEducationStage educationStage = educationStageDao.loadById(educationStageId);

		Set<CourseModel> courseModels = new HashSet<>();
		for (long courseId : educationStage.getCourseIds()) {
			courseModels.add(loadCourseModel(courseId));
		}
		return courseModels;
	}

	@Override
	public Set<CourseSectionModel> getCourseSections(Long courseId) throws DataAccessException {
		ICourse course = courseDao.loadById(courseId);

		Set<CourseSectionModel> courseSectionModels = new HashSet<>();
		for (ICourseSection courseSection : course.getCourseSections()) {
			courseSectionModels.add(loadCourseSectionModel(courseSection.getId()));
		}
		return courseSectionModels;
	}

	@Override
	public TestModel getTest(Long courseSectionId) throws DataAccessException {
		ICourseSection courseSection = courseSectionDao.loadById(courseSectionId);
		return loadTestModel(courseSection.getTestId());
	}

	@Override
	public TestResultModel validateUserTestPassing(UserTestPassing userTestPassing) throws DataAccessException {
		ITest test = testDao.loadById(userTestPassing.getTestId());

		TestResultModel testResultModel = new TestResultModel();
		testResultModel.setTestName(test.getName());

		List<TestResultModel.ValidatedTestAnswer> validatedTestAnswers = new ArrayList<>();
		for (UserTestPassing.UserAnswer userAnswer : userTestPassing.getUserAnswers()) {
			TestResultModel.ValidatedTestAnswer validatedTestAnswer = new TestResultModel.ValidatedTestAnswer();

			ITestQuestion testQuestion = testQuestionDao.loadById(userAnswer.getQuestionId());

			validatedTestAnswer.setQuestionModel(loadTestQuestionModel(userAnswer.getQuestionId()));

			Set<Long> correctAnswerIds = new HashSet<>();
			for (ITestAnswer correctAnswer : testQuestion.getAnswers()) {
				correctAnswerIds.add(correctAnswer.getId());
			}
			validatedTestAnswer.setCorrectAnswerIds(correctAnswerIds);

			validatedTestAnswer.setUserAnswerIds(userAnswer.getAnswerIds());

			validatedTestAnswers.add(validatedTestAnswer);
		}
		testResultModel.setValidatedTestAnswers(validatedTestAnswers);

		return testResultModel;
	}

	@Override
	public Set<EducationStageModel> getAllAvailableEducationStages() throws DataAccessException {
		Set<EducationStageModel> result = new HashSet<>();
		for (IEducationStage stage : educationStageDao.loadAll()) {
			result.add(getEducationStageModel(stage));
		}

		return result;
	}

	private EducationStageModel loadEducationStageModel(long id) throws DataAccessException {
		return getEducationStageModel(educationStageDao.loadById(id));
	}

	private EducationStageModel getEducationStageModel(IEducationStage stage) throws DataAccessException{
		EducationStageModel educationStageModel = new EducationStageModel();
		educationStageModel.setId(stage.getId());
		educationStageModel.setName(stage.getName());

		return educationStageModel;
	}

	private CourseModel loadCourseModel(Long id) throws DataAccessException{
		CourseModel courseModel = new CourseModel();
		ICourse course = courseDao.loadById(id);

		courseModel.setId(id);
		courseModel.setName(course.getName());

		return courseModel;
	}

	private CourseSectionModel loadCourseSectionModel(Long id) throws DataAccessException{
		CourseSectionModel courseSectionModel = new CourseSectionModel();
		ICourseSection courseSection = courseSectionDao.loadById(id);

		courseSectionModel.setId(id);
		courseSectionModel.setName(courseSection.getName());

		return courseSectionModel;
	}

	private TestModel loadTestModel(Long id) throws DataAccessException{
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

	private TestQuestionModel loadTestQuestionModel(Long id) throws DataAccessException{
		TestQuestionModel testQuestionModel = new TestQuestionModel();
		ITestQuestion testQuestion = testQuestionDao.loadById(id);

		testQuestionModel.setId(id);
		testQuestionModel.setQuestion(testQuestion.getQuestion());
		testQuestionModel.setTestQuestionType(testQuestion.getType());

		Set<TestAnswerModel> answerModels = new HashSet<>();
		for (ITestAnswer answer : testQuestion.getAnswers()) {
			answerModels.add(loadTestAnswerModel(answer.getId()));
		}
		testQuestionModel.setAnswers(answerModels);

		return testQuestionModel;
	}

	private TestAnswerModel loadTestAnswerModel(Long id) throws DataAccessException{
		TestAnswerModel testAnswerModel = new TestAnswerModel();
		ITestAnswer testAnswer = testAnswerDao.loadById(id);

		testAnswerModel.setId(id);
		testAnswerModel.setAnswer(testAnswer.getAnswer());

		return testAnswerModel;
	}
}
