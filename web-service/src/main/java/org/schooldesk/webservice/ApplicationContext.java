package org.schooldesk.webservice;

import org.schooldesk.core.services.IServiceFactory;
import org.schooldesk.core.services.ServiceFactory;
import org.schooldesk.dao.DaoFactory;
import org.schooldesk.dao.IDaoFactory;

public class ApplicationContext {
	private final static IServiceFactory serviceFactory;
	static {
		IDaoFactory daoFactory = DaoFactory.create();
		serviceFactory = ServiceFactory.create(daoFactory);
	}

	private ApplicationContext(){}

	public static IServiceFactory getServiceFactory(){
		return serviceFactory;
	}
}
