package org.schooldesk.webservice;

import org.schooldesk.core.models.CourseModel;
import org.schooldesk.core.models.CourseSectionModel;
import org.schooldesk.core.models.UserFetchByEmailModel;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.dao.DataAccessException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
@WebServlet("/getCourseSection")
public class CourseSectionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ITestService testService = ApplicationContext.getServiceFactory().getService(ITestService.class);

		Long courseId = Long.parseLong(req.getParameter("courseId"));
		try {
			Set<CourseSectionModel> courseSectionModels = testService.getCourseSections(courseId);

			ServletHelper.writeResponse(true, null, courseSectionModels, resp.getOutputStream());
		} catch (DataAccessException e) {
			ServletHelper.writeResponse(false, e.getMessage(), null, resp.getOutputStream());
		}
	}
}
