package org.schooldesk.dao.hibernateimpl;

import org.hibernate.SessionFactory;
import org.schooldesk.core.GroupCore;
import org.schooldesk.dao.IGroupDao;
import org.schooldesk.dto.IGroup;
import org.schooldesk.dto.impl.GroupDto;


public class GroupDao extends AbstractDao<IGroup> implements IGroupDao {

	public GroupDao(SessionFactory sessionFactory) {
		super(sessionFactory, GroupCore.class);
	}

	@Override
	public IGroup createDto() {
		return GroupDto.createNew();
	}
}
