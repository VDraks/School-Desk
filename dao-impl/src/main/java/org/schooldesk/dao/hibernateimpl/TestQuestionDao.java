package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class TestQuestionDao extends AbstractDao<ITestQuestion> implements ITestQuestionDao {
	public TestQuestionDao(CoreApi coreApi) {
		super(coreApi, ITestQuestion.class, TestQuestionCore.class);
	}

	@Override
	public ITestQuestion createDto() {
		return TestQuestionDto.createNew();
	}

	@Override
	protected TestQuestionCore createCoreObject(ITestQuestion entity) throws HibernateException {
		TestQuestionCore result = new TestQuestionCore();
		result.fromDto(entity, getApi());
		return result;
	}
}
