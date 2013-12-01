package org.schooldesk.dao;

import org.schooldesk.dto.ICourseSection;

public interface ICourseSectionDao extends IDao<ICourseSection> {
	ICourseSection createDto();
}
