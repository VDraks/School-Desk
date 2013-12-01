package org.schooldesk.webservice;

import org.schooldesk.core.models.UserCredentialModel;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.dao.DataAccessException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Authentication extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UserCredentialModel ucm = ServletHelper.readValue(req.getParameter("userInfo"), UserCredentialModel.class);
		try {
			boolean result = ApplicationContext.getServiceFactory().getService(IUserService.class).checkCredentials(ucm);
			ServletHelper.writeResponse(result, null, null, resp.getOutputStream());
		}
		catch (DataAccessException e){
			ServletHelper.writeResponse(false, e.getMessage(), null, resp.getOutputStream());
		}
	}
}
