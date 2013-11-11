package org.schooldesk.dao.impl;

import org.hibernate.SessionFactory;
import org.schooldesk.dao.IRightDao;
import org.schooldesk.dto.IRightDto;
import org.schooldesk.dto.impl.RightDto;

public class RightDao extends Dao<IRightDto> implements IRightDao
{
	public RightDao(SessionFactory sessionFactory)
	{
		super(sessionFactory);
	}

	@Override
	public IRightDto createNew(String code)
	{
		return RightDto.createNew(code);
	}
}
