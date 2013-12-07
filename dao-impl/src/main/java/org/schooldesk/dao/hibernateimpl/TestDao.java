package org.schooldesk.dao.hibernateimpl;

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
}
