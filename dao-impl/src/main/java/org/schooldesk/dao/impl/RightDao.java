package org.schooldesk.dao.impl;

import org.schooldesk.dao.IRightDao;
import org.schooldesk.dto.IRightDto;
import org.schooldesk.dto.impl.RightDto;

public class RightDao extends Dao<IRightDto> implements IRightDao{
	//	public RightDao(API api) {
//		super(api);
//	}

	@Override
	public IRightDto createNew(String code) {
		return RightDto.createNew(code);
	}
}
