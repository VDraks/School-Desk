package org.schooldesk.dao.inmemory.inmemory;

import org.schooldesk.dto.IDto;
import org.schooldesk.dto.inmemory.*;

import java.util.HashMap;
import java.util.Map;


public class Database {
	public static Map<Long, ? extends IDto> courses = new HashMap<>();
	public static Map<Long, ? extends IDto> courseSections = new HashMap<>();
	public static Map<Long, ? extends IDto> educationStages = new HashMap<>();
	public static Map<Long, ? extends IDto> tests = new HashMap<>();
	public static Map<Long, ? extends IDto> testAnswers = new HashMap<>();
	public static Map<Long, ? extends IDto> testQuestions = new HashMap<>();
	public static Map<Long, ? extends IDto> testResults = new HashMap<>();
	public static Map<Long, ? extends IDto> users = new HashMap<>();
	public static Map<Long, ? extends IDto> userTestAnswers = new HashMap<>();

	public static Map<Class<? extends IDto>, Map<Long, ? extends IDto>> database = new HashMap<Class<? extends IDto>, Map<Long, ? extends IDto>>(){{
			put(InMemoryCourse.class, courses);
			put(InMemoryCourseSection.class, courseSections);
			put(InMemoryEducationStage.class, educationStages);
		}
	};
}
