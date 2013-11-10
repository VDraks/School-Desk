package org.schooldesk.dao.impl;

import org.schooldesk.dao.IGroupDao;
import org.schooldesk.dto.IGroupDto;
import org.schooldesk.dto.impl.GroupDto;

public class GroupDao extends Dao<IGroupDto> implements IGroupDao{
//	public GroupDao(API api) {
//		super(api);
//	}

	@Override
	public IGroupDto createNew() {
		return GroupDto.createNew();
	}
}
