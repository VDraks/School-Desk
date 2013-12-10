package org.schooldesk.dao.inmemory.inmemory;

import org.schooldesk.dao.inmemory.*;
import org.schooldesk.dto.IDto;
import org.schooldesk.dto.inmemory.*;

import java.util.HashMap;
import java.util.Map;


public class Database {
	public static Map<Long, IDto> courses;
	public static Map<Long, IDto> courseSections;
	public static Map<Long, IDto> educationStages;
	public static Map<Long, IDto> tests;
	public static Map<Long, IDto> testAnswers;
	public static Map<Long, IDto> testQuestions;
	public static Map<Long, IDto> testResults;
	public static Map<Long, IDto> users;
	public static Map<Long, IDto> userTestAnswers;

	public static Map<Long, IDto> rights;

	public static Map<Class<? extends InMemoryAbstractDao>, Map<Long, IDto>> database = new HashMap<Class<? extends InMemoryAbstractDao>, Map<Long, IDto>>();

	static {
		resetDB();
	}

	public static void resetDB(){
		courses = new HashMap<>();
		courseSections = new HashMap<>();
		educationStages = new HashMap<>();
		tests = new HashMap<>();
		testAnswers = new HashMap<>();
		testQuestions = new HashMap<>();
		testResults = new HashMap<>();
		users = new HashMap<>();
		userTestAnswers = new HashMap<>();
		rights = new HashMap<>();

		database.put(InMemoryCourseDao.class, courses);
		database.put(InMemoryCourseSectionDao.class, courseSections);
		database.put(InMemoryEducationStageDao.class, educationStages);
		database.put(InMemoryTestDao.class, tests);
		database.put(InMemoryTestAnswerDao.class, testAnswers);
		database.put(InMemoryTestQuestionDao.class, testQuestions);
		database.put(InMemoryTestResultDao.class, testResults);
		database.put(InMemoryUserDao.class, users);
		database.put(InMemoryUserTestAnswerDao.class, userTestAnswers);

		database.put(InMemoryRightDao.class, rights);
	}
}
