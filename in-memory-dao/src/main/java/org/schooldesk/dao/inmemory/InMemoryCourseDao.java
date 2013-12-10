package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ICourseDao;
import org.schooldesk.dto.ICourse;
import org.schooldesk.dto.inmemory.InMemoryCourse;


public class InMemoryCourseDao extends InMemoryAbstractDao<ICourse> implements ICourseDao {
	@Override
	public ICourse createDto() {
		return new InMemoryCourse();
	}
}
