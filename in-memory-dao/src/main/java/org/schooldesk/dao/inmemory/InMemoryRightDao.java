package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.inmemory.*;


public class InMemoryRightDao extends InMemoryAbstractDao<IRight> implements IRightDao {
	@Override
	public IRight createDto(String code) {
		return new InMemoryRight(code);
	}
}