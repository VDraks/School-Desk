package org.schooldesk.tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.schooldesk.dao.*;
import org.schooldesk.dto.ICourse;
import org.schooldesk.dto.ICourseSection;

import java.util.Arrays;

import static junit.framework.Assert.*;

public class CCourseSUR_Test extends CAbstractTest{

	private Long courseId;
	private Long courseSection1Id;
	private Long courseSection2Id;

	private String courseName;
	private String courseSectionName1;
	private String courseSectionName2;

	@Before
	public void before() throws DataAccessException {
		ICourseSectionDao courseSectionDao = getFactory().getDao(ICourseSectionDao.class);

		courseSectionName1 = "New section 1";
		ICourseSection courseSection1 = courseSectionDao.createDto();
		courseSection1.setName(courseSectionName1);
		courseSection1 = courseSectionDao.save(courseSection1);
		courseSection1Id = courseSection1.getId();

		courseSectionName2 = "New section 2";
		ICourseSection courseSection2 = courseSectionDao.createDto();
		courseSection2.setName(courseSectionName2);
		courseSection2 = courseSectionDao.save(courseSection2);
		courseSection2Id = courseSection2.getId();

		courseName = "New course";
		ICourseDao courseDao = getFactory().getDao(ICourseDao.class);
		ICourse course = courseDao.createDto();
		course.setName(courseName);
		course.setCourseSectionIds(Arrays.asList(courseSection1Id, courseSection2Id));
		course = courseDao.save(course);
		courseId = course.getId();
	}

	@Test
	public void test() throws DataAccessException {

		  ICourseDao courseDao = getFactory().getDao(ICourseDao.class);
		  ICourse courseDto = courseDao.loadById(courseId);

		  assertTrue("Course was not loaded", courseDto != null);
		  assertEquals("Course name was corrupted during saving", courseDto.getName(), courseName);

			ICourseSectionDao courseSectionDao = getFactory().getDao(ICourseSectionDao.class);

			ICourseSection courseSectionDto1 = courseSectionDao.loadById(courseSection1Id);
			assertTrue("Course section was not loaded", courseSectionDto1 != null);
			assertEquals("Course section name was corrupted during saving", courseSectionDto1.getName(), courseSectionName1);

			ICourseSection courseSectionDto2 = courseSectionDao.loadById(courseSection2Id);
			assertTrue("Course section was not loaded", courseSectionDto2 != null);
			assertEquals("Course section name was corrupted during saving", courseSectionDto2.getName(), courseSectionName2);
	}

	@After
	public void after() throws DataAccessException {
		ICourseSectionDao courseSectionDao = getFactory().getDao(ICourseSectionDao.class);

		courseSectionDao.delete(courseSection1Id);
		assertEquals("Course section was not successfully deleted", courseSectionDao.loadById(courseSection1Id), null);

		ICourseDao courseDao = getFactory().getDao(ICourseDao.class);
		courseDao.delete(courseId);

		assertEquals("Course was not successfully deleted", courseDao.loadById(courseId), null);
		assertTrue("Course was not successfully deleted", courseDao.loadAll().isEmpty());
		assertEquals("Course section was not successfully deleted", courseSectionDao.loadById(courseSection2Id), null);
		assertTrue("Course section was not successfully deleted", courseSectionDao.loadAll().isEmpty());
	}
}
