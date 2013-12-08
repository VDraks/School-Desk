package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;
import org.schooldesk.hibernateobjects.*;


public class UserTestAnswerDao extends AbstractDao<IUserTestAnswer> implements IUserTestAnswerDao {
	public UserTestAnswerDao(CoreApi coreApi) {
		super(coreApi, IUserTestAnswer.class, UserTestAnswerCore.class);
	}

	@Override
	public IUserTestAnswer createDto() {
		return UserTestAnswerDto.createNew();
	}

	@Override
	protected UserTestAnswerCore createCoreObject(IUserTestAnswer entity) throws HibernateException {
		UserTestAnswerCore result = new UserTestAnswerCore();
		result.fromDto(entity, getApi());
		return result;
	}
}
