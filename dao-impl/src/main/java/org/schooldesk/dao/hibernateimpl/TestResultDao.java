package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;
import org.schooldesk.hibernateobjects.*;

import java.util.*;


public class TestResultDao extends AbstractDao<ITestResult> implements ITestResultDao {
	public TestResultDao(CoreApi coreApi) {
		super(coreApi, ITestResult.class, TestResultCore.class);
	}

	@Override
	public ITestResult createDto(Long testId, Long userId, Set<Long> userTestAnswerIds) {
		return TestResultDto.createNew(testId, userId, userTestAnswerIds);
	}

	@Override
	protected TestResultCore createCoreObject(ITestResult entity) throws HibernateException {
		TestResultCore result = new TestResultCore();
		result.fromDto(entity, getApi());
		return result;
	}
}
