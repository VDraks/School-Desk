package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;
import org.schooldesk.hibernateobjects.*;


public class CourseDao extends AbstractDao<ICourse> implements ICourseDao {
	public CourseDao(CoreApi coreApi) {
		super(coreApi, ICourse.class, CourseCore.class);
	}

	@Override
	public ICourse createDto() {
		return CourseDto.createNew();
	}

	@Override
	protected CourseCore createCoreObject(ICourse entity) throws HibernateException {
		CourseCore result = new CourseCore();
		result.fromDto(entity, getApi());
		return result;
	}
}
