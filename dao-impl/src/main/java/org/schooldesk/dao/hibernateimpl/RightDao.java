package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class RightDao extends AbstractDao<IRightDto> implements IRightDao {
	public RightDao(SessionFactory sessionFactory) {
		super(sessionFactory, RightCore.class);
	}

	@Override
	public IRightDto createNew(String code) {
		return RightDto.createNew(code);
	}
}
