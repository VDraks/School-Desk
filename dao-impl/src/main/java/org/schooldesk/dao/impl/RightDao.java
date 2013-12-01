package org.schooldesk.dao.impl;

import org.hibernate.SessionFactory;
import org.schooldesk.core.RightCore;
import org.schooldesk.dao.IRightDao;
import org.schooldesk.dto.IRight;
import org.schooldesk.dto.impl.RightDto;

public class RightDao extends AbstractDao<IRight> implements IRightDao
{
	public RightDao(SessionFactory sessionFactory)
	{
		super(sessionFactory, RightCore.class);
	}

	@Override
	public IRight createNew(String code)
	{
		return RightDto.createNew(code);
	}
}
