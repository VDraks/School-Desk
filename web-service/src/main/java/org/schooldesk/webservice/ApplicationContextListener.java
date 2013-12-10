package org.schooldesk.webservice;

import org.schooldesk.core.models.*;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.ICourseDao;
import org.schooldesk.dao.ICourseSectionDao;
import org.schooldesk.dao.IEducationStageDao;
import org.schooldesk.dto.ICourse;
import org.schooldesk.dto.ICourseSection;
import org.schooldesk.dto.IEducationStage;
import org.schooldesk.dto.TestQuestionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.HashSet;


@WebListener
public class ApplicationContextListener implements ServletContextListener {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationContextListener.class);

	private static final IEducationStageDao educationStageDao = ApplicationContext.getDaoFactory().getDao(IEducationStageDao.class);
	private static final ICourseDao courseDao = ApplicationContext.getDaoFactory().getDao(ICourseDao.class);
	private static final ICourseSectionDao courseSectionDao = ApplicationContext.getDaoFactory().getDao(ICourseSectionDao.class);

	private static final IUserService USER_SERVICE = ApplicationContext.getServiceFactory().getService(IUserService.class);
	private static final ITestService TEST_SERVICE = ApplicationContext.getServiceFactory().getService(ITestService.class);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			long educationStageId = createEducationStage(new EducationStageCreationModel("Подозрительная секция"));
			long courseId = createCourse(new CourseCreationModel("Забавный курс", educationStageId));
			long courseSectionId = createCourseSection(new CourseSectionCreationModel("Раздел 1", courseId));

			final TestQuestionCreationModel question1 = new TestQuestionCreationModel();
			question1.setQuestion("Каков ответ на главный вопрос жизни, вселенной и всего такого?");
			question1.setTestQuestionType(TestQuestionType.SINGLE_ANSWER_QUESTION);
			final TestAnswerCreationModel answer11 = new TestAnswerCreationModel("42");
			final TestAnswerCreationModel answer12 = new TestAnswerCreationModel("О_О");
			final TestAnswerCreationModel answer13 = new TestAnswerCreationModel("Да");
			question1.setAnswers(new HashSet<TestAnswerCreationModel>() {{ add(answer11); add(answer12); add(answer13); }});
			question1.setCorrectAnswers(new HashSet<TestAnswerCreationModel>() {{ add(answer11); }});

			final TestQuestionCreationModel question2 = new TestQuestionCreationModel();
			question2.setQuestion("Какие из перечисленных частиц считаются элементарными?");
			question2.setTestQuestionType(TestQuestionType.MULTIPLE_ANSWER_QUESTION);
			final TestAnswerCreationModel answer21 = new TestAnswerCreationModel("Фермионы");
			final TestAnswerCreationModel answer22 = new TestAnswerCreationModel("Бозоны");
			final TestAnswerCreationModel answer23 = new TestAnswerCreationModel("Адроны");
			question2.setAnswers(new HashSet<TestAnswerCreationModel>() {{ add(answer21); add(answer22); add(answer23); }});
			question2.setCorrectAnswers(new HashSet<TestAnswerCreationModel>() {{ add(answer21); add(answer22); }});

			TestCreationModel test1 = new TestCreationModel();
			test1.setName("Странный тест");
			test1.setCourseSectionId(courseSectionId);
			test1.setQuestions(new HashSet<TestQuestionCreationModel>() {{ add(question1); add(question2); }});

			TEST_SERVICE.createTest(test1);

			USER_SERVICE.createUser(new UserCreationModel("John", "Adam", "Smith", "smith@exmple.com", new HashSet<Long>(), educationStageId));
		}
		catch (DataAccessException e) {
			LOG.error("Error occurred during DB population", e);
		}
	}

	private static long createEducationStage(EducationStageCreationModel educationStageCreationModel) throws DataAccessException {
		IEducationStage educationStage = educationStageDao.createDto();
		educationStage.setName(educationStageCreationModel.getName());
		educationStage.setCourseIds(new ArrayList<Long>());

		return educationStageDao.save(educationStage).getId();
	}

	private static long createCourse(CourseCreationModel courseCreationModel) throws DataAccessException {
		ICourse course = courseDao.createDto();
		course.setName(courseCreationModel.getName());
		course.setCourseSectionIds(new ArrayList<Long>());
		long savedCourseId = courseDao.save(course).getId();

		IEducationStage educationStage = educationStageDao.loadById(courseCreationModel.getEducationStageId());
		educationStage.getCourseIds().add(savedCourseId);
		educationStageDao.save(educationStage);

		return savedCourseId;
	}

	private static long createCourseSection(CourseSectionCreationModel courseSectionCreationModel) throws DataAccessException {
		ICourseSection courseSection = courseSectionDao.createDto();
		courseSection.setName(courseSectionCreationModel.getName());
		long savedCourseSectionId = courseSectionDao.save(courseSection).getId();

		ICourse course = courseDao.loadById(courseSectionCreationModel.getCourseId());
		course.getCourseSectionIds().add(savedCourseSectionId);
		courseDao.save(course);

		return savedCourseSectionId;
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}
