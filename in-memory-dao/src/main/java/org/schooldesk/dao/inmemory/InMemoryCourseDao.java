package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ICourseDao;
import org.schooldesk.dto.ICourse;


public class InMemoryCourseDao extends InMemoryAbstractDao<ICourse> implements ICourseDao {
	@Override
	public ICourse createDto() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
