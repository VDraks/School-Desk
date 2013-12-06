package org.schooldesk.webservice.dispatching;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public abstract class AbstractDispatchedServlet extends HttpServlet {
	@Override
	protected final void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = getActionString(request);
		if (action == null || action.trim().isEmpty()) {
			throw new ServletException("No action specified");
		}

		Method actionHandler = null;
		try {
			actionHandler = getClass().getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			if (actionHandler.isAnnotationPresent(ActionHandler.class)) {
				actionHandler.invoke(this, request, response);
			}
			else {
				throw new ServletException("Found method is not intended to handle actions");
			}
		}
		catch (NoSuchMethodException e) {
			throw new ServletException(String.format("Cannot dispatch action '%s'. No matching action handler found", action));
		}
		catch (InvocationTargetException e) {
			throw new ServletException("Action handler has thrown an exception", e.getCause());
		}
		catch (IllegalAccessException e) {
			// Here actionHandler is already initialized and not null
			throw new ServletException("Cannot access action handler " + buildFullyQualifiedMethodName(actionHandler));
		}
	}

	private static String getActionString(HttpServletRequest request) {
		String actionPath = request.getPathInfo();
		return (actionPath != null) ? actionPath.substring(1) : null;
	}

	private static String buildFullyQualifiedMethodName(Method method) {
		return method.getDeclaringClass().getCanonicalName() + "." + method.getName();
	}
}
