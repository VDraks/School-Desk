package org.schooldesk.dao.hibernateimpl;

import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class UserTestAnswerDao extends AbstractDao<IUserTestAnswer> implements IUserTestAnswerDao {
	public UserTestAnswerDao(CoreApi coreApi) {
		super(coreApi, IUserTestAnswer.class, UserTestAnswerCore.class);
	}

	@Override
	public IUserTestAnswer createDto() {
		return UserTestAnswerDto.createNew();
	}
}
