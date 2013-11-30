package org.schooldesk.core.services.impl;

import org.schooldesk.core.models.*;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.dao.IDaoFactory;

import java.util.Set;


class TestServiceImpl extends AbstractServiceImpl implements ITestService {
	public TestServiceImpl(IDaoFactory daoFactory) {
		super(daoFactory);
	}

	@Override
	public Set<CourseModel> getCourses(long stageId) {
		// ~TODO: implement me!
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<CourseSectionModel> getCourseSections(long courseId) {
		// TODO: implement me!
		throw new UnsupportedOperationException();
	}

	@Override
	public TestModel getTest(long courseSectionId) {
		// ~TODO: implement me!
		throw new UnsupportedOperationException();
	}

	@Override
	public TestResultModel validateUserTestPassing(UserTestPassing userTestPassing) {
		// ~TODO: implement me!
		throw new UnsupportedOperationException();
	}
}
