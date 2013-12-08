package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class GroupDao extends AbstractDao<IGroup> implements IGroupDao {

	public GroupDao(CoreApi coreApi) {
		super(coreApi, IGroup.class, GroupCore.class);
	}

	@Override
	public IGroup createDto() {
		return GroupDto.createNew();
	}

	@Override
	protected GroupCore createCoreObject(IGroup entity) throws HibernateException {
		GroupCore result = new GroupCore();
		result.fromDto(entity, getApi());
		return result;
	}
}
