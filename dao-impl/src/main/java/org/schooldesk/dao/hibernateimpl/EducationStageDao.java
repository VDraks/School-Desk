package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class EducationStageDao extends AbstractDao<IEducationStage> implements IEducationStageDao {
	public EducationStageDao(CoreApi coreApi) {
		super(coreApi, IEducationStage.class, EducationStageCore.class);
	}

	@Override
	public IEducationStage createDto() {
		return EducationStageDto.createNew();
	}

	@Override
	protected EducationStageCore createCoreObject(IEducationStage entity) throws HibernateException {
		EducationStageCore result = new EducationStageCore();
		result.fromDto(entity, getApi());
		return result;
	}
}
