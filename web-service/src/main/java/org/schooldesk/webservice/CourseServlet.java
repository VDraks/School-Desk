package org.schooldesk.webservice;

import org.schooldesk.core.models.CourseModel;
import org.schooldesk.core.models.CourseSectionModel;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.webservice.dispatching.AbstractDispatchedServlet;
import org.schooldesk.webservice.dispatching.ActionHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.schooldesk.webservice.SessionAttribute.USER_ID;


@WebServlet("/course/*")
public class CourseServlet extends AbstractDispatchedServlet {
	@ActionHandler
	public void getCourses(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ITestService testService = ApplicationContext.getServiceFactory().getService(ITestService.class);
		IUserService userService = ApplicationContext.getServiceFactory().getService(IUserService.class);

		Long userId = (Long) USER_ID.getValue(req.getSession());
		try {
			Set<CourseModel> courseModels = new HashSet<>();
			courseModels.addAll(testService.getCourses(userService.getUserById(userId).getEducationStageId()));
			ServletHelper.writeResponse(true, null, courseModels, resp.getOutputStream());
		}
		catch (DataAccessException e) {
			ServletHelper.writeResponse(false, e.getMessage(), null, resp.getOutputStream());
		}
	}

	@ActionHandler
	public void getCourseSections(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ITestService testService = ApplicationContext.getServiceFactory().getService(ITestService.class);

		Long courseId = Long.parseLong(req.getParameter("CourseId"));
		try {
			Set<CourseSectionModel> courseSectionModels = testService.getCourseSections(courseId);

			ServletHelper.writeResponse(true, null, courseSectionModels, resp.getOutputStream());
		}
		catch (DataAccessException e) {
			ServletHelper.writeResponse(false, e.getMessage(), null, resp.getOutputStream());
		}
	}
}
