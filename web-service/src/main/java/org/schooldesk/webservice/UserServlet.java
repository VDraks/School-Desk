package org.schooldesk.webservice;

import org.schooldesk.core.models.UserCredentialModel;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.webservice.dispatching.AbstractDispatchedServlet;
import org.schooldesk.webservice.dispatching.ActionHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.schooldesk.webservice.SessionAttribute.USER_ID;


@WebServlet("/user/*")
public class UserServlet extends AbstractDispatchedServlet {
	@ActionHandler
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UserCredentialModel ucm = ServletHelper.readValue(req.getParameter("UserCredentials"), UserCredentialModel.class);
		try {
			Long result = ApplicationContext.getServiceFactory().getService(IUserService.class).checkCredentials(ucm);
			ServletHelper.writeResponse(result != null, null, null, resp.getOutputStream());
			USER_ID.setValue(req.getSession(), result);
		}
		catch (DataAccessException e) {
			ServletHelper.writeResponse(false, e.getMessage(), null, resp.getOutputStream());
		}
	}
}
