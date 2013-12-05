package org.schooldesk.webservice;

import org.schooldesk.core.models.*;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.dao.DataAccessException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
@WebServlet("/test/*")
public class TestServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		ITestService testService = ApplicationContext.getServiceFactory().getService(ITestService.class);

		if (uri.contains("getTest")){

			Long courseSectionId = Long.parseLong(req.getParameter("courseSectionId"));
			try {
				TestModel testModel = testService.getTest(courseSectionId);
				ServletHelper.writeResponse(true, null, testModel, resp.getOutputStream());
			} catch (DataAccessException e) {
				ServletHelper.writeResponse(false, e.getMessage(), null, resp.getOutputStream());
			}
		}
		else if(uri.contains("checkResultTest")){
			UserTestPassingModel utpModel = ServletHelper.readValue(req.getParameter("userTestPassingModel"), UserTestPassingModel.class);
			try {
				TestResultModel trModel = testService.validateUserTestPassing(utpModel);
				ServletHelper.writeResponse(true, null, trModel, resp.getOutputStream());
			} catch (DataAccessException e) {
				ServletHelper.writeResponse(false, e.getMessage(), null, resp.getOutputStream());
			}
		}
	}
}
