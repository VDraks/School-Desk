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
}
