package org.schooldesk.dao;

import org.schooldesk.dto.IRightDTO;


public interface IRightDAO extends IDAO<IRightDTO> {
	public IRightDTO createNew(String code);
}
