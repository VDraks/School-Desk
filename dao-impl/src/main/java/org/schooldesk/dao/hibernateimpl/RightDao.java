package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;
import org.schooldesk.hibernateobjects.*;


public class RightDao extends AbstractDao<IRight> implements IRightDao {
	public RightDao(CoreApi coreApi) {
		super(coreApi, IRight.class, RightCore.class);
	}

	@Override
	public IRight createDto(String code) {
		return RightDto.createNew(code);
	}

	@Override
	protected RightCore createCoreObject(IRight entity) throws HibernateException {
		RightCore result = new RightCore();
		result.fromDto(entity, getApi());
		return result;
	}
}
