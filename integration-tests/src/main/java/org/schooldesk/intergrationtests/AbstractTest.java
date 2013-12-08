package org.schooldesk.intergrationtests;

import org.junit.*;
import org.schooldesk.core.services.*;
import org.schooldesk.dao.*;
import org.schooldesk.dao.hibernateimpl.*;


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
