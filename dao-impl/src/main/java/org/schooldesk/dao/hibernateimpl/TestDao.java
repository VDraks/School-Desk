package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class TestDao extends AbstractDao<ITest> implements ITestDao {
	public TestDao(CoreApi coreApi) {
		super(coreApi, ITest.class, TestCore.class);
	}

	@Override
	public ITest createDto() {
		return TestDto.createNew();
	}

	@Override
	protected TestCore createCoreObject(ITest entity) throws HibernateException {
		TestCore result = new TestCore();
		result.fromDto(entity, getApi());
		return result;
	}
}
