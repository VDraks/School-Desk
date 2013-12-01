package org.schooldesk.dao.hibernateimpl;

import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class CourseDao extends AbstractDao<ICourse> implements ICourseDao {
	public CourseDao(CoreApi coreApi) {
		super(coreApi, ICourse.class, CourseCore.class);
	}

	@Override
	public ICourse createDto() {
		return CourseDto.createNew();
	}
}
