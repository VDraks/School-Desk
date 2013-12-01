package org.schooldesk.dao;

import org.schooldesk.dto.ICourse;

public interface ICourseDao extends IDao<ICourse>{
	ICourse createNew();
}
