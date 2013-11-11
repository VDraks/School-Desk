package org.schooldesk.dao.impl;

import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

public class RightDao extends Dao<IRightDto> implements IRightDao{
	//	public RightDao(API api) {
//		super(api);
//	}

	@Override
	public IRightDto createNew(String code) {
		return RightDto.createNew(code);
	}
}
