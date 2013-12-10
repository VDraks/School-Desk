package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.IEducationStageDao;
import org.schooldesk.dto.IEducationStage;


public class InMemoryEducationStageDao extends InMemoryAbstractDao<IEducationStage> implements IEducationStageDao {
	@Override
	public IEducationStage createDto() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
