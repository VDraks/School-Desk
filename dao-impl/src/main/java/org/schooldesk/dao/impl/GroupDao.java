package org.schooldesk.dao.impl;

import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

public class GroupDao extends Dao<IGroupDto> implements IGroupDao{
//	public GroupDao(API api) {
//		super(api);
//	}

	@Override
	public IGroupDto createNew() {
		return GroupDto.createNew();
	}
}
