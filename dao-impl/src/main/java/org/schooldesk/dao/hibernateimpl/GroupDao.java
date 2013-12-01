package org.schooldesk.dao.hibernateimpl;

import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class GroupDao extends AbstractDao<IGroup> implements IGroupDao {

	public GroupDao(CoreApi coreApi) {
		super(coreApi, IGroup.class, GroupCore.class);
	}

	@Override
	public IGroup createNew() {
		return GroupDto.createNew();
	}
}
