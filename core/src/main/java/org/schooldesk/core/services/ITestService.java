package org.schooldesk.core.services;

import org.schooldesk.core.models.*;
import org.schooldesk.dao.DataAccessException;

import java.util.Set;


public interface ITestService extends IService {
	long createTest(TestCreationModel testModel) throws DataAccessException;
	void updateTest(TestModel testModel);
	void deleteTest(long testId) throws DataAccessException;

	Set<EducationStageModel> getAllAvailableEducationStages() throws DataAccessException;
	Set<CourseModel> getCourses(Long stageId) throws DataAccessException;
	Set<CourseSectionModel> getCourseSections(Long courseId) throws DataAccessException;
	TestModel getTest(Long courseSectionId) throws DataAccessException;

	TestResultModel validateUserTestPassing(UserTestPassingModel userTestPassing) throws DataAccessException;
}
