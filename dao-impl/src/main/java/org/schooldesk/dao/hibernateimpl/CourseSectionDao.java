package org.schooldesk.dao.hibernateimpl;

import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class CourseSectionDao extends AbstractDao<ICourseSection> implements ICourseSectionDao {
	public CourseSectionDao(CoreApi coreApi) {
		super(coreApi, ICourseSection.class, CourseSectionCore.class);
	}

	@Override
	public ICourseSection createNew() {
		return CourseSectionDto.createNew();
	}
}
