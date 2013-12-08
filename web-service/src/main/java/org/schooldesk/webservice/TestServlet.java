package org.schooldesk.webservice;

import org.schooldesk.core.models.TestModel;
import org.schooldesk.core.models.TestResultModel;
import org.schooldesk.core.models.UserTestPassingModel;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.webservice.dispatching.AbstractDispatchedServlet;
import org.schooldesk.webservice.dispatching.ActionHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.schooldesk.webservice.SessionAttribute.USER_ID;


@WebServlet("/test/*")
public class TestServlet extends AbstractDispatchedServlet {
	private ITestService testService;

	@Override
	public void init() throws ServletException {
		testService = ApplicationContext.getServiceFactory().getService(ITestService.class);
	}

	@ActionHandler
	public void getTest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Long courseSectionId = Long.parseLong(req.getParameter("CourseSectionId"));
		try {
			TestModel testModel = testService.getTest(courseSectionId);
			ServletHelper.writeResponse(true, null, testModel, resp.getOutputStream());
		}
		catch (DataAccessException e) {
			ServletHelper.writeResponse(false, e.getMessage(), null, resp.getOutputStream());
		}
	}

	@ActionHandler
	public void validateUserTestPassing(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserTestPassingModel utpModel = ServletHelper.readValue(req.getParameter("UserTestPassing"), UserTestPassingModel.class);
		utpModel.setUserId((Long) USER_ID.getValue(req.getSession()));
		try {
			TestResultModel trModel = testService.validateUserTestPassing(utpModel);
			ServletHelper.writeResponse(true, null, trModel, resp.getOutputStream());
		}
		catch (DataAccessException e) {
			ServletHelper.writeResponse(false, e.getMessage(), null, resp.getOutputStream());
		}
	}
}
