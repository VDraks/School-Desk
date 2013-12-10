package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ITestDao;
import org.schooldesk.dto.ITest;
import org.schooldesk.dto.inmemory.InMemoryTest;


public class InMemoryTestDao extends InMemoryAbstractDao<ITest> implements ITestDao {
	@Override
	public ITest createDto() {
		return new InMemoryTest();
	}
}
