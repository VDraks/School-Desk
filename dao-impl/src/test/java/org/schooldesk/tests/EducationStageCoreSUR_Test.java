package org.schooldesk.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.schooldesk.dao.*;
import org.schooldesk.dto.ICourse;
import org.schooldesk.dto.ICourseSection;
import org.schooldesk.dto.IEducationStage;
import org.schooldesk.dto.ITest;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class EducationStageCoreSUR_Test extends CAbstractTest{

	private Long educationStageId;
	private Long course1Id;
	private Long course2Id;

	private String educationStageName;
	private String course1Name;
	private String course2Name;

	@Before
	public void before() throws DataAccessException {

		course1Name = "course 1 name";
		ICourseDao courseDao = getFactory().getDao(ICourseDao.class);
		ICourse course = courseDao.createDto();
		course.setName(course1Name);
		course = courseDao.save(course);
		course1Id = course.getId();

		course2Name = "course 2 name";
		course = courseDao.createDto();
		course.setName(course2Name);
		course = courseDao.save(course);
		course2Id = course.getId();

		educationStageName = "education stage name";
		IEducationStageDao educationStageDao = getFactory().getDao(IEducationStageDao.class);
		IEducationStage educationStage = educationStageDao.createDto();
		educationStage.setName(educationStageName);
		educationStage.setCourseIds(Arrays.asList(course1Id, course2Id));
		educationStage = educationStageDao.save(educationStage);
		educationStageId = educationStage.getId();
	}

	@Test
	public void test() throws DataAccessException {

		ICourseDao courseDao = getFactory().getDao(ICourseDao.class);
		ICourse course = courseDao.loadById(course1Id);

		assertTrue("Course 1 was not loaded", course != null);
		assertEquals("Course 1 name was corrupted during saving", course1Name, course.getName());

		course = courseDao.loadById(course2Id);

		assertTrue("Course 2 was not loaded", course != null);
		assertEquals("Course 2 name was corrupted during saving", course2Name, course.getName());

		IEducationStageDao educationStageDao = getFactory().getDao(IEducationStageDao.class);
		IEducationStage educationStage = educationStageDao.loadById(educationStageId);

		assertTrue("Education stage was not loaded", educationStage != null);
		assertEquals("Education stage name was corrupted during saving", educationStageName, educationStage.getName());
	}

	@After
	public void after() throws DataAccessException {

		ICourseDao courseDao = getFactory().getDao(ICourseDao.class);
//		courseDao.delete(course1Id);

		IEducationStageDao educationStageDao = getFactory().getDao(IEducationStageDao.class);
		educationStageDao.delete(educationStageId);

		assertEquals("Course 2 was not successfully deleted", null, courseDao.loadById(course2Id));
		assertEquals("Course 1 was not successfully deleted", null, courseDao.loadById(course1Id));
		assertTrue("Course 1 and course 2 was not successfully deleted", courseDao.loadAll().isEmpty());
		assertEquals("Education stage was not successfully deleted", null, educationStageDao.loadById(educationStageId));
		assertTrue("Education stage was not successfully deleted", educationStageDao.loadAll().isEmpty());
	}
}
