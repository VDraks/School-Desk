package org.schooldesk.dao;

import org.schooldesk.dto.IGroup;


public interface IGroupDao extends IDao<IGroup> {
	public IGroup createNew();
}
