package org.schooldesk.core.services.impl;

import org.schooldesk.core.models.*;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


class TestServiceImpl extends AbstractServiceImpl implements ITestService {
	private final IEducationStageDao educationStageDao;
	private final ICourseDao courseDao;
	private final ICourseSectionDao courseSectionDao;
	private final ITestDao testDao;
	private final ITestQuestionDao testQuestionDao;
	private final ITestAnswerDao testAnswerDao;

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
	public long createTest(TestCreationModel testCreationModel) throws DataAccessException {
		final ITest test = testDao.createDto();
		test.setName(testCreationModel.getName());

		Set<Long> testQuestionIds = new HashSet<>(testCreationModel.getQuestions().size());
		for (TestQuestionCreationModel testQuestionModel : testCreationModel.getQuestions()) {
			testQuestionIds.add(createTestQuestion(testQuestionModel));
		}

		test.setTestQuestionIds(testQuestionIds);

		return testDao.save(test).getId();
	}

	private long createTestQuestion(TestQuestionCreationModel testQuestionCreationModel) throws DataAccessException {
		final ITestQuestion testQuestion = testQuestionDao.createDto();
		testQuestion.setQuestion(testQuestionCreationModel.getQuestion());
		testQuestion.setType(testQuestionCreationModel.getTestQuestionType());

		Set<Long> testAnswerIds = new HashSet<>();
		Set<Long> correctTestAnswersIds = new HashSet<>();
		for (TestAnswerCreationModel testAnswerModel : testQuestionCreationModel.getAnswers()) {
			final long testAnswerId = createTestAnswer(testAnswerModel);
			testAnswerIds.add(testAnswerId);

			if (testQuestionCreationModel.getCorrectAnswers().contains(testAnswerModel)) {
				correctTestAnswersIds.add(testAnswerId);
			}
		}

		testQuestion.setAnswerIds(testAnswerIds);
		testQuestion.setCorrectAnswerIds(correctTestAnswersIds);

		return testQuestionDao.save(testQuestion).getId();
	}

	private long createTestAnswer(TestAnswerCreationModel testAnswerCreationModel) throws DataAccessException {
		final ITestAnswer testAnswer = testAnswerDao.createDto();
		testAnswer.setAnswer(testAnswerCreationModel.getAnswer());

		return testAnswerDao.save(testAnswer).getId();
	}

	@Override
	public void updateTest(TestModel testModel) {
		// TODO: implement me!
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteTest(long testId) throws DataAccessException {
		testDao.delete(testId);
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
		for (Long courseSectionId : course.getCourseSectionIds()) {
			courseSectionModels.add(loadCourseSectionModel(courseSectionId));
		}
		return courseSectionModels;
	}

	@Override
	public TestModel getTest(Long courseSectionId) throws DataAccessException {
		ICourseSection courseSection = courseSectionDao.loadById(courseSectionId);
		return loadTestModel(courseSection.getTestId());
	}

	@Override
	public TestResultModel validateUserTestPassing(UserTestPassingModel userTestPassing) throws DataAccessException {
		ITest test = testDao.loadById(userTestPassing.getTestId());

		TestResultModel testResultModel = new TestResultModel();
		testResultModel.setTestName(test.getName());

		List<TestResultModel.ValidatedTestAnswerModel> validatedTestAnswers = new ArrayList<>();
		for (UserTestPassingModel.UserAnswerModel userAnswer : userTestPassing.getUserAnswers()) {
			TestResultModel.ValidatedTestAnswerModel validatedTestAnswer = new TestResultModel.ValidatedTestAnswerModel();

			final Long questionId = userAnswer.getQuestionId();
			validatedTestAnswer.setQuestionModel(loadTestQuestionModel(questionId));
			validatedTestAnswer.setCorrectAnswerIds(testQuestionDao.loadById(questionId).getCorrectAnswerIds());
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

	private EducationStageModel getEducationStageModel(IEducationStage stage) throws DataAccessException {
		EducationStageModel educationStageModel = new EducationStageModel();
		educationStageModel.setId(stage.getId());
		educationStageModel.setName(stage.getName());

		return educationStageModel;
	}

	private CourseModel loadCourseModel(Long id) throws DataAccessException {
		CourseModel courseModel = new CourseModel();
		ICourse course = courseDao.loadById(id);

		courseModel.setId(id);
		courseModel.setName(course.getName());

		return courseModel;
	}

	private CourseSectionModel loadCourseSectionModel(Long id) throws DataAccessException {
		CourseSectionModel courseSectionModel = new CourseSectionModel();
		ICourseSection courseSection = courseSectionDao.loadById(id);

		courseSectionModel.setId(id);
		courseSectionModel.setName(courseSection.getName());

		return courseSectionModel;
	}

	private TestModel loadTestModel(Long id) throws DataAccessException {
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

	private TestQuestionModel loadTestQuestionModel(Long id) throws DataAccessException {
		TestQuestionModel testQuestionModel = new TestQuestionModel();
		ITestQuestion testQuestion = testQuestionDao.loadById(id);

		testQuestionModel.setId(id);
		testQuestionModel.setQuestion(testQuestion.getQuestion());
		testQuestionModel.setTestQuestionType(testQuestion.getType());

		Set<TestAnswerModel> answerModels = new HashSet<>();
		for (Long answerId : testQuestion.getAnswerIds()) {
			answerModels.add(loadTestAnswerModel(answerId));
		}
		testQuestionModel.setAnswers(answerModels);

		return testQuestionModel;
	}

	private TestAnswerModel loadTestAnswerModel(Long id) throws DataAccessException {
		TestAnswerModel testAnswerModel = new TestAnswerModel();
		ITestAnswer testAnswer = testAnswerDao.loadById(id);

		testAnswerModel.setId(id);
		testAnswerModel.setAnswer(testAnswer.getAnswer());

		return testAnswerModel;
	}
}
