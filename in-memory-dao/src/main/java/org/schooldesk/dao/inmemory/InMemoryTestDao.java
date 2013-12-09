package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ITestDao;
import org.schooldesk.dto.ITest;


public class InMemoryTestDao extends InMemoryAbstractDao<ITest> implements ITestDao {
	@Override
	public ITest createDto() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
