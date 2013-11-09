package org.schooldesk.core.dao;

import org.schooldesk.core.dto.IGroupDTO;

public interface IGroupDAO extends IDAO<IGroupDTO>{
    public IGroupDTO createNew();
}
