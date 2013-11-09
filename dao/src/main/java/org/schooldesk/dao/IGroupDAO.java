package org.schooldesk.dao;

import org.schooldesk.dto.IGroupDTO;


public interface IGroupDAO extends IDAO<IGroupDTO> {
	public IGroupDTO createNew();
}
