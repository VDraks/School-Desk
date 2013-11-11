package org.schooldesk.core.services;

import org.schooldesk.core.models.CourseModel;
import org.schooldesk.core.models.CourseTopicModel;
import org.schooldesk.core.models.TestModel;

import java.util.Set;


public interface ITestService extends IService {
	Set<CourseModel> getCourses(long stageId);
	Set<CourseTopicModel> getCourseTopics(long courseId);
	TestModel getTest(long topicId);
}
