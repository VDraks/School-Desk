package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;
import org.schooldesk.hibernateobjects.*;


public class TestAnswerDao extends AbstractDao<ITestAnswer> implements ITestAnswerDao {
	public TestAnswerDao(CoreApi coreApi) {
		super(coreApi, ITestAnswer.class, TestAnswerCore.class);
	}

	@Override
	public ITestAnswer createDto() {
		return TestAnswerDto.createNew();
	}

	@Override
	protected AbstractCore createCoreObject(ITestAnswer entity) throws HibernateException {
		TestAnswerCore result = new TestAnswerCore();
		result.fromDto(entity, getApi());
		return result;
	}
}
