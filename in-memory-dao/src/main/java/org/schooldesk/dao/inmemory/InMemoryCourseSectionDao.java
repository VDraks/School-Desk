package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ICourseSectionDao;
import org.schooldesk.dto.ICourseSection;
import org.schooldesk.dto.inmemory.InMemoryCourseSection;


public class InMemoryCourseSectionDao extends InMemoryAbstractDao<ICourseSection> implements ICourseSectionDao {
	@Override
	public ICourseSection createDto() {
		return new InMemoryCourseSection();
	}
}
