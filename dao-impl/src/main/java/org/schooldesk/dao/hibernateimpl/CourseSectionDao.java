package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;
import org.schooldesk.hibernateobjects.*;


public class CourseSectionDao extends AbstractDao<ICourseSection> implements ICourseSectionDao {
	public CourseSectionDao(CoreApi coreApi) {
		super(coreApi, ICourseSection.class, CourseSectionCore.class);
	}

	@Override
	public ICourseSection createDto() {
		return CourseSectionDto.createNew();
	}

	@Override
	protected CourseSectionCore createCoreObject(ICourseSection entity) throws HibernateException {
		CourseSectionCore result = new CourseSectionCore();
		result.fromDto(entity, getApi());
		return result;
	}

	@Override
	public void delete(Long id) throws DataAccessException{

//		CourseSectionCore courseSection = getApi().loadById(CourseSectionCore.class, id);

//		CourseCore course = courseSection.getCourseCore();
//		course.getCourseSections().remove(courseSection);
//		getApi().update(course);

//		if (courseSection.getTest() != null)
//		{
//			TestDao testDao = new TestDao(getApi());
//			TestCore test = getApi().loadById(TestCore.class, courseSection.getTest().getId());
//			testDao.delete(test.getId());
//		}



		super.delete(id);
	}
}
