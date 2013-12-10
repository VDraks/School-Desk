package org.schooldesk.webservice;

import org.schooldesk.core.models.UserCreationModel;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.dao.DataAccessException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashSet;


@WebListener
public class ApplicationContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO: populate db
		IUserService userService = ApplicationContext.getServiceFactory().getService(IUserService.class);
		try {
			long userId = userService.createUser(new UserCreationModel("John", "Adam", "Smith", "smith@exmple.com", new HashSet<Long>()));
			System.out.println(userId);
		}
		catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}
}
