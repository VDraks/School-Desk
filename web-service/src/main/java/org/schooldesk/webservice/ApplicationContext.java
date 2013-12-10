package org.schooldesk.webservice;

import org.schooldesk.core.services.IServiceFactory;
import org.schooldesk.core.services.ServiceFactory;
import org.schooldesk.dao.DaoFactory;
import org.schooldesk.dao.IDaoFactory;

public class ApplicationContext {
	// TODO: delete this field
	private final static IDaoFactory DAO_FACTORY;
	private final static IServiceFactory SERVICE_FACTORY;
	static {
		DAO_FACTORY = DaoFactory.create();
		SERVICE_FACTORY = ServiceFactory.create(DAO_FACTORY);
	}

	private ApplicationContext(){}

	public static IDaoFactory getDaoFactory() {
		return DAO_FACTORY;
	}

	public static IServiceFactory getServiceFactory(){
		return SERVICE_FACTORY;
	}
}
