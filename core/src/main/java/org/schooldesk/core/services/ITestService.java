package org.schooldesk.core.services;

import org.schooldesk.core.models.*;

import java.util.Set;


public interface ITestService extends IService {
	// TODO: add CRUD methods

	Set<CourseModel> getCourses(long stageId);
	Set<CourseSectionModel> getCourseSections(long courseId);
	TestModel getTest(long courseSectionId);

	TestResultModel validateUserTestPassing(UserTestPassing userTestPassing);
}
