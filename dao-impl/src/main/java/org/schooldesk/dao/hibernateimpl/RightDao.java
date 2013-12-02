package org.schooldesk.dao.hibernateimpl;

import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class RightDao extends AbstractDao<IRight> implements IRightDao {
	public RightDao(CoreApi coreApi) {
		super(coreApi, IRight.class, RightCore.class);
	}

	@Override
	public IRight createDto(String code) {
		return RightDto.createNew(code);
	}
}
