package org.schooldesk.webservice;

import org.schooldesk.core.models.CourseModel;
import org.schooldesk.core.models.EducationStageModel;
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
@WebServlet("/getCourse")
public class CourseServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ITestService testService = ApplicationContext.getServiceFactory().getService(ITestService.class);
		IUserService userService = ApplicationContext.getServiceFactory().getService(IUserService.class);

		Long userId = (Long) req.getSession().getAttribute("userId");
		try {
			Set<CourseModel> courseModels = new HashSet<CourseModel>();
			for (Long stageId : userService.getUserById(userId).getGroupIds())
			{
				courseModels.addAll(testService.getCourses(stageId));
			}
			ServletHelper.writeResponse(true, null, courseModels, resp.getOutputStream());
		} catch (DataAccessException e) {
			ServletHelper.writeResponse(false, e.getMessage(), null, resp.getOutputStream());
		}
	}
}
