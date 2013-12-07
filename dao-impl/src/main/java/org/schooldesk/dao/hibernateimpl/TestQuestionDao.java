package org.schooldesk.dao.hibernateimpl;

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
}
