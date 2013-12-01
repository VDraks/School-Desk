package org.schooldesk.dao.hibernateimpl;

import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class RightDao extends AbstractDao<IRightDto> implements IRightDao {
	public RightDao(CoreApi coreApi) {
		super(coreApi, IRightDto.class, RightCore.class);
	}

	@Override
	public IRightDto createNew(String code) {
		return RightDto.createNew(code);
	}
}
