package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class GroupDao extends AbstractDao<IGroup> implements IGroupDao {

	public GroupDao(SessionFactory sessionFactory) {
		super(sessionFactory, IGroup.class, GroupCore.class);
	}

	@Override
	public IGroup createNew() {
		return GroupDto.createNew();
	}
}
