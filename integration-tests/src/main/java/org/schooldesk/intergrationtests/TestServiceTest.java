package org.schooldesk.intergrationtests;

//import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.MatcherAssert.assertThat;

import java.util.*;

import junit.framework.*;
import org.junit.*;
import org.junit.Test;
import org.schooldesk.core.models.*;
import org.schooldesk.core.services.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;

import static org.junit.Assert.*;

public class TestServiceTest extends AbstractTest {

	private ITestService testService;

	private IEducationStageDao educationStageDao;
	private ICourseDao courseDao;
	private ICourseSectionDao courseSectionDao;
	private ITestDao testDao;
	private ITestQuestionDao testQuestionDao;
	private ITestAnswerDao testAnswerDao;
	private IRightDao rightDao;

	private Long rightId;


	@Before
	public void initTestService() throws DataAccessException {
		testService = getServiceFactory().getService(ITestService.class);

		IDaoFactory daoFactory = DaoFactory.create();
		educationStageDao = daoFactory.getDao(IEducationStageDao.class);
		courseDao = daoFactory.getDao(ICourseDao.class);
		courseSectionDao = daoFactory.getDao(ICourseSectionDao.class);
		testDao = daoFactory.getDao(ITestDao.class);
		testQuestionDao = daoFactory.getDao(ITestQuestionDao.class);
		testAnswerDao = daoFactory.getDao(ITestAnswerDao.class);
		rightDao = daoFactory.getDao(IRightDao.class);

		IRight right = rightDao.createDto("CodeTest");
		rightId = rightDao.save(right).getId();
	}

	@Test
	public void canCreateTest() throws DataAccessException {
		TestCreationModel testCreationModel = new TestCreationModel();
		testCreationModel.setName("TestServiceTest");

		Set<TestQuestionCreationModel> testQuestionCreationModels = new HashSet<>();
		testQuestionCreationModels.add(createSingleAnswerQuestion());
		testQuestionCreationModels.add(createMultipleAnswerQuestion());
		testCreationModel.setQuestions(testQuestionCreationModels);

		ICourseSection courseSection = courseSectionDao.createDto();
		courseSection.setName("CourseSectionTest");
		courseSection.setTestId(null);
		testCreationModel.setCourseSectionId(courseSectionDao.save(courseSection).getId());

		Long testId = testService.createTest(testCreationModel);

		ITest test = testDao.loadById(testId);

		assertEquals(testCreationModel.getName(), test.getName());

		for (Long testQuestionId : test.getTestQuestionIds()) {
			ITestQuestion testQuestion = testQuestionDao.loadById(testQuestionId);

			TestQuestionCreationModel testQuestionCreationModel = getQuestionModelByQuestion(testQuestionCreationModels, testQuestion.getQuestion());

			assertEquals(testQuestionCreationModel.getTestQuestionType(), testQuestion.getType());

			assertAnswerContain(testQuestionCreationModel.getAnswers(), testQuestion.getAnswerIds());
			assertAnswerContain(testQuestionCreationModel.getCorrectAnswers(), testQuestion.getCorrectAnswerIds());
		}
	}

	private TestQuestionCreationModel createSingleAnswerQuestion() {
		TestQuestionCreationModel singleAnswerQuestion = new TestQuestionCreationModel();
		singleAnswerQuestion.setQuestion("Question_1");

		Set<TestAnswerCreationModel> testAnswerCreationModels = new HashSet<>();
		testAnswerCreationModels.add(new TestAnswerCreationModel("Answer_1_Question_1"));
		testAnswerCreationModels.add(new TestAnswerCreationModel("Answer_2_Question_1"));
		testAnswerCreationModels.add(new TestAnswerCreationModel("Answer_3_Question_1"));
		testAnswerCreationModels.add(new TestAnswerCreationModel("Answer_4_Question_1"));
		singleAnswerQuestion.setAnswers(testAnswerCreationModels);

		Set<TestAnswerCreationModel> testCorrectAnswerCreationModels = new HashSet<>();
		testCorrectAnswerCreationModels.add(new TestAnswerCreationModel("Answer_3_Question_1"));
		singleAnswerQuestion.setCorrectAnswers(testCorrectAnswerCreationModels);

		singleAnswerQuestion.setTestQuestionType(TestQuestionType.SINGLE_ANSWER_QUESTION);
		return singleAnswerQuestion;
	}

	private TestQuestionCreationModel createMultipleAnswerQuestion() {
		TestQuestionCreationModel multipleAnswerQuestion = new TestQuestionCreationModel();
		multipleAnswerQuestion.setQuestion("Question_2");

		Set<TestAnswerCreationModel> testAnswerCreationModels = new HashSet<>();
		testAnswerCreationModels.add(new TestAnswerCreationModel("Answer_1_Question_2"));
		testAnswerCreationModels.add(new TestAnswerCreationModel("Answer_2_Question_2"));
		testAnswerCreationModels.add(new TestAnswerCreationModel("Answer_3_Question_2"));
		multipleAnswerQuestion.setAnswers(testAnswerCreationModels);

		Set<TestAnswerCreationModel> testCorrectAnswerCreationModels = new HashSet<>();
		testCorrectAnswerCreationModels.add(new TestAnswerCreationModel("Answer_1_Question_2"));
		testCorrectAnswerCreationModels.add(new TestAnswerCreationModel("Answer_3_Question_2"));
		multipleAnswerQuestion.setCorrectAnswers(testCorrectAnswerCreationModels);

		multipleAnswerQuestion.setTestQuestionType(TestQuestionType.MULTIPLE_ANSWER_QUESTION);
		return multipleAnswerQuestion;
	}

	private TestQuestionCreationModel getQuestionModelByQuestion(Set<TestQuestionCreationModel> testQuestionModels, String question) {
		for (TestQuestionCreationModel testQuestionCreationModel : testQuestionModels) {
			if (testQuestionCreationModel.getQuestion().equals(question)) {
				return testQuestionCreationModel;
			}
		}
		fail(question + " is not contained");
		return null;
	}

	private void assertAnswerContain(Set<TestAnswerCreationModel> testAnswerCreationModels, Set<Long> answerIds) throws DataAccessException {
		for (Long answerId : answerIds) {
			ITestAnswer testAnswer = testAnswerDao.loadById(answerId);

			boolean isContained = false;
			for (TestAnswerCreationModel testAnswerCreationModel : testAnswerCreationModels) {
				if (testAnswerCreationModel.getAnswer().equals(testAnswer.getAnswer())) {
					isContained = true;
					break;
				}
			}
			assertTrue("Answers contained", isContained);
		}
	}


	@Test
	public void checkGettingAllAvailableEducationStages() throws DataAccessException {
		assertTrue(testService.getAllAvailableEducationStages().isEmpty());

		EducationStageModel expectedEducationStageModel = createEducationStageModel();

		Set<EducationStageModel> educationStageModels = testService.getAllAvailableEducationStages();
		assertTrue(educationStageModels.size() == 1);

		EducationStageModel educationStageModel = educationStageModels.iterator().next();
		assertEquals(expectedEducationStageModel.getId(), educationStageModel.getId());
		assertEquals(expectedEducationStageModel.getName(), educationStageModel.getName());
	}

	@Test
	public void checkGettingCourses() throws DataAccessException {
		IEducationStage expectedEducationStage = createEducationStage();

		Set<CourseModel> courseModels = testService.getCourses(expectedEducationStage.getId());
		assertTrue(courseModels.size() == 1);

		Long expectedCourseId = expectedEducationStage.getCourseIds().get(0);

		CourseModel courseModel = courseModels.iterator().next();
		assertEquals(expectedCourseId, courseModel.getId());
	}

	@Test
	public void checkGettingCourseSections() throws DataAccessException {
		ICourse expectedCourse = createCourse();

		Set<CourseSectionModel> courseSectionModels = testService.getCourseSections(expectedCourse.getId());
		assertTrue(courseSectionModels.size() == 1);

		Long expectedCourseSectionId = expectedCourse.getCourseSectionIds().get(0);

		CourseSectionModel courseSectionModel = courseSectionModels.iterator().next();
		assertEquals(expectedCourseSectionId, courseSectionModel.getId());
	}

	@Test
	public void checkGettingTest() throws DataAccessException {
		ICourseSection expectedCourseSection = createCourseSection();
		ITest expectedTest = testDao.loadById(expectedCourseSection.getTestId());

		TestModel testModel = testService.getTest(expectedCourseSection.getId());
		assertEquals(expectedTest.getId(), testModel.getId());
		assertEquals(expectedTest.getName(), testModel.getName());
	}


	private EducationStageModel createEducationStageModel() throws DataAccessException {
		EducationStageModel educationStageModel = new EducationStageModel();
		IEducationStage educationStage = createEducationStage();
		educationStageModel.setName(educationStage.getName());
		educationStageModel.setId(educationStage.getId());
		return educationStageModel;
	}

	private IEducationStage createEducationStage() throws DataAccessException {
		IEducationStage educationStage = educationStageDao.createDto();
		educationStage.setName("EducationStageTest");
		List<Long> courseIds = new ArrayList<>();
		courseIds.add(createCourseModel().getId());
		educationStage.setCourseIds(courseIds);
		educationStage = educationStageDao.save(educationStage);

//		System.out.println(educationStage.getId() + "!!!!!!!!!!!!!!!!!!");

		return educationStage;
	}

	private CourseModel createCourseModel() throws DataAccessException {
		CourseModel courseModel = new CourseModel();
		ICourse course = createCourse();
		courseModel.setName(course.getName());
		courseModel.setId(courseDao.save(course).getId());
		return courseModel;
	}

	private ICourse createCourse() throws DataAccessException {
		ICourse course = courseDao.createDto();
		course.setName("CourseTest");

		List<Long> courseSectionIds = new ArrayList<>();
		courseSectionIds.add(createCourseSectionModel().getId());
		course.setCourseSectionIds(courseSectionIds);
		course = courseDao.save(course);
		return course;
	}

	private CourseSectionModel createCourseSectionModel() throws DataAccessException  {
		CourseSectionModel courseSectionModel = new CourseSectionModel();
		ICourseSection courseSection = createCourseSection();
		courseSectionModel.setName(courseSection.getName());
		courseSectionModel.setId(courseSectionDao.save(courseSection).getId());
		return courseSectionModel;
	}

	private ICourseSection createCourseSection() throws DataAccessException {
		ICourseSection courseSection = courseSectionDao.createDto();
		courseSection.setName("CourseSectionTest");
		courseSection.setTestId(createTestModel().getId());
		courseSection = courseSectionDao.save(courseSection);
		return courseSection;
	}

	private TestModel createTestModel() throws DataAccessException {
		TestModel testModel = new TestModel();
		ITest test = testDao.createDto();
		test.setName("TestTest");
		testModel.setName(test.getName());

		TestQuestionModel testQuestionModel = createTestQuestionModel();

		Set<TestQuestionModel> questionModels = new HashSet<>();
		questionModels.add(testQuestionModel);
		testModel.setQuestions(questionModels);

		Set<Long> testQuestionIds = new HashSet<>();
		testQuestionIds.add(testQuestionModel.getId());
		test.setTestQuestionIds(testQuestionIds);
		test.setRightId(rightId);

		testModel.setId(testDao.save(test).getId());

		return testModel;
	}

	private TestQuestionModel createTestQuestionModel() throws DataAccessException {
		TestQuestionModel testQuestionModel = new TestQuestionModel();
		ITestQuestion testQuestion = testQuestionDao.createDto();

		testQuestion.setName("TestQuestionTest");

		testQuestion.setQuestion("TestQuestionTest");
		testQuestionModel.setQuestion(testQuestion.getQuestion());

		TestAnswerModel testAnswerModel = new TestAnswerModel();
		ITestAnswer testAnswer = testAnswerDao.createDto();
		testAnswer.setName("TestAnswerTest");
		testAnswer.setAnswer(testAnswer.getName());
		testAnswer.setRightId(rightId);
		testAnswerModel.setAnswer(testAnswer.getAnswer());



		Set<Long> answerIds = new HashSet<>();
		testAnswerModel.setId(testAnswerDao.save(testAnswer).getId());
		answerIds.add(testAnswerModel.getId());
		testQuestion.setAnswerIds(answerIds);
		Set<TestAnswerModel> answerModels = new HashSet<>();
		answerModels.add(testAnswerModel);
		testQuestionModel.setAnswers(answerModels);

		testQuestion.setCorrectAnswerIds(answerIds);

		testQuestion.setType(TestQuestionType.SINGLE_ANSWER_QUESTION);
		testQuestionModel.setTestQuestionType(testQuestion.getType());

		testQuestionModel.setId(testQuestionDao.save(testQuestion).getId());

		return  testQuestionModel;
	}

//	Set<EducationStageModel> getAllAvailableEducationStages() throws DataAccessException;
//	Set<CourseModel> getCourses(Long stageId) throws DataAccessException;
//	Set<CourseSectionModel> getCourseSections(Long courseId) throws DataAccessException;
//	TestModel getTest(Long courseSectionId) throws DataAccessException;
}
