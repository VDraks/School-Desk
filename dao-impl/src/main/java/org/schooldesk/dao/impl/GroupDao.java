package org.schooldesk.dao.impl;

import org.hibernate.*;
import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

public class GroupDao extends AbstractDao<IGroupDto> implements IGroupDao{
	
	public GroupDao(SessionFactory sessionFactory)
	{
		super(sessionFactory, GroupCore.class);
	}

	@Override
	public IGroupDto createNew()
	{
		return GroupDto.createNew();
	}
}
