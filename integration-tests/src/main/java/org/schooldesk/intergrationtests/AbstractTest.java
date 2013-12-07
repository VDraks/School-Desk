package org.schooldesk.intergrationtests;

import org.junit.After;
import org.junit.Before;
import org.schooldesk.DbHelper;
import org.schooldesk.core.services.IServiceFactory;
import org.schooldesk.core.services.ServiceFactory;
import org.schooldesk.dao.DaoFactory;
import org.schooldesk.dao.IDaoFactory;


public class AbstractTest {
	private IDaoFactory daoFactory;
	private IServiceFactory serviceFactory;

	@Before
	public final void init() {
		daoFactory = DaoFactory.create();
		serviceFactory = ServiceFactory.create(daoFactory);
	}

	@After
	public final void dispose() {
		DbHelper.clearDb();
	}

	protected IDaoFactory getDaoFactory() {
		return daoFactory;
	}

	protected IServiceFactory getServiceFactory() {
		return serviceFactory;
	}
}
