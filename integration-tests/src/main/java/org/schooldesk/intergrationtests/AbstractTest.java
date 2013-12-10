package org.schooldesk.intergrationtests;

import org.junit.After;
import org.junit.Before;
import org.schooldesk.core.services.IServiceFactory;
import org.schooldesk.core.services.ServiceFactory;
import org.schooldesk.dao.DaoFactory;
import org.schooldesk.dao.IDaoFactory;
import org.schooldesk.dao.inmemory.inmemory.*;
//import org.schooldesk.dao.hibernateimpl.DbHelper;
//import org.schooldesk.dao.hibernateimpl.HibernateDaoFactory;


public class AbstractTest {
	private IDaoFactory daoFactory;
//	private DbHelper dbHelper;
	private IServiceFactory serviceFactory;

	@Before
	public final void init() {
		daoFactory = DaoFactory.create();
//		dbHelper = new DbHelper((HibernateDaoFactory) daoFactory);
		serviceFactory = ServiceFactory.create(daoFactory);
	}

	@After
	public final void dispose() {
//		dbHelper.clearDb();
		Database.resetDB();
	}

	protected IDaoFactory getDaoFactory() {
		return daoFactory;
	}

	protected IServiceFactory getServiceFactory() {
		return serviceFactory;
	}
}
