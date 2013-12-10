package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.IEducationStageDao;
import org.schooldesk.dto.IEducationStage;
import org.schooldesk.dto.inmemory.InMemoryEducationStage;


public class InMemoryEducationStageDao extends InMemoryAbstractDao<IEducationStage> implements IEducationStageDao {
	@Override
	public IEducationStage createDto() {
		return new InMemoryEducationStage();
	}
}
