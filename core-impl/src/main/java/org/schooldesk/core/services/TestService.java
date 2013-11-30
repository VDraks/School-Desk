package org.schooldesk.core.services;

import java.util.*;

import org.schooldesk.core.models.CourseModel;
import org.schooldesk.core.models.CourseSectionModel;
import org.schooldesk.core.models.TestModel;
import org.schooldesk.core.models.TestResultModel;
import org.schooldesk.core.models.UserTestPassing;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;

public class TestService extends Service implements ITestService {
	@Override
	public List<CourseModel> getCourses(long stageId) {

		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public List<CourseSectionModel> getCourseSections(long courseId) {
		IDao<ICourseDto> courseDao;
		// IDao<ICourseDto> daoCourse = daoFactory.getDao(ICourseDto);
		ICourseDto courseDto = courseDao.loadById(courseId);

		List<CourseSectionModel> courseSectionModels = new ArrayList<CourseSectionModel>();
		for (ICourseSectionDto courseSectionDto : courseDto.getCourseSections()) {
			CourseSectionModel courseSectionModel = new CourseSectionModel();
			courseSectionModel.setId(courseSectionDto.getId());
			courseSectionModel.setName(courseSectionDto.getName());
			courseSectionModels.add(courseSectionModel);
		}
		return courseSectionModels;
	}

	@Override
	public TestModel getTest(long courseSectionId)
	{
		IDao<ICourseSectionDto> courseSectionDao;
		// IDao<ICourseDto> daoCourse = daoFactory.getDao(ICourseDto);
		ICourseSectionDto courseSectionDto = courseSectionDao.loadById(courseSectionId);

		ITestDto testDto; ??
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public TestResultModel validateUserTestPassing(UserTestPassing userTestPassing)
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}
}
