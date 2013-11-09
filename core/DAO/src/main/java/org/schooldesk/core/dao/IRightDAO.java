package org.schooldesk.core.dao;

import org.schooldesk.core.dto.IRightDTO;

public interface IRightDAO extends IDAO<IRightDTO> {
    public IRightDTO createNew(String code);
}
