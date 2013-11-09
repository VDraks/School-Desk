package org.schooldesk.dao;

import org.schooldesk.dto.*;


public interface IUserDAO extends IDAO<IUserDTO> {
	public IUserDTO createNew();
}
