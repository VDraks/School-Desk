package org.schooldesk.core.services;

import org.schooldesk.core.models.*;
import org.schooldesk.dao.DataAccessException;

import java.util.Set;


public interface ITestService extends IService {
	// TODO: add CRUD methods

	Set<EducationStageModel> getAllAvailableEducationStages() throws DataAccessException;
	Set<CourseModel> getCourses(Long stageId) throws DataAccessException;
	Set<CourseSectionModel> getCourseSections(Long courseId) throws DataAccessException;
	TestModel getTest(Long courseSectionId) throws DataAccessException;

	TestResultModel validateUserTestPassing(UserTestPassing userTestPassing) throws DataAccessException;
}
