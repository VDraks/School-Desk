package org.schooldesk.dao;

import org.schooldesk.dto.IRight;


public interface IRightDao extends IDao<IRight> {
	public IRight createNew(String code);
}
