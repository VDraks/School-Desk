package org.schooldesk.core.dao;

import org.schooldesk.core.dto.*;

public interface IUserDAO extends IDAO<IUserDTO>{
    public IUserDTO createNew();
}
