package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;
import org.schooldesk.hibernateobjects.*;


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

	@Override
	public void delete(Long id) throws DataAccessException {

		EducationStageCore educationStage = getApi().loadById(EducationStageCore.class, id);
		CourseDao courseDao = new CourseDao(getApi());

		for (CourseCore courseCore : educationStage.getCourses())
		{
			   courseDao.delete(id);
		}

		super.delete(id);
	}
}
