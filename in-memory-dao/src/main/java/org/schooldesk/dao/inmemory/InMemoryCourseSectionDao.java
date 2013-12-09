package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ICourseSectionDao;
import org.schooldesk.dto.ICourseSection;


public class InMemoryCourseSectionDao extends InMemoryAbstractDao<ICourseSection> implements ICourseSectionDao {
	@Override
	public ICourseSection createDto() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
