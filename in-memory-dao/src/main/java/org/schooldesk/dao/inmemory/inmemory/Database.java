package org.schooldesk.dao.inmemory.inmemory;

import org.schooldesk.dao.inmemory.*;
import org.schooldesk.dto.IDto;
import org.schooldesk.dto.inmemory.*;

import java.util.HashMap;
import java.util.Map;


public class Database {
	public static Map<Long, IDto> courses = new HashMap<>();
	public static Map<Long, IDto> courseSections = new HashMap<>();
	public static Map<Long, IDto> educationStages = new HashMap<>();
	public static Map<Long, IDto> tests = new HashMap<>();
	public static Map<Long, IDto> testAnswers = new HashMap<>();
	public static Map<Long, IDto> testQuestions = new HashMap<>();
	public static Map<Long, IDto> testResults = new HashMap<>();
	public static Map<Long, IDto> users = new HashMap<>();
	public static Map<Long, IDto> userTestAnswers = new HashMap<>();

	public static Map<Class<? extends InMemoryAbstractDao>, Map<Long, IDto>> database = new HashMap<Class<? extends InMemoryAbstractDao>, Map<Long, IDto>>(){{
			put(InMemoryCourseDao.class, courses);
			put(InMemoryCourseSectionDao.class, courseSections);
			put(InMemoryEducationStageDao.class, educationStages);
			put(InMemoryTestDao.class, tests);
			put(InMemoryTestAnswerDao.class, testAnswers);
			put(InMemoryTestQuestionDao.class, testQuestions);
			put(InMemoryTestResultDao.class, testResults);
			put(InMemoryUserDao.class, users);
			put(InMemoryUserTestAnswerDao.class, userTestAnswers);
	}
	};
}
