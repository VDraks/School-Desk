package org.schooldesk.dao.hibernateimpl;

import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class TestAnswerDao extends AbstractDao<ITestAnswer> implements ITestAnswerDao {
	public TestAnswerDao(CoreApi coreApi) {
		super(coreApi, ITestAnswer.class, TestAnswerCore.class);
	}

	@Override
	public ITestAnswer createDto() {
		return TestAnswerDto.createNew();
	}
}
