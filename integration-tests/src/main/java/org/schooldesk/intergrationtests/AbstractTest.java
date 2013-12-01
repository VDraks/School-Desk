package org.schooldesk.intergrationtests;

import org.junit.Before;
import org.schooldesk.core.services.IServiceFactory;
import org.schooldesk.core.services.ServiceFactory;
import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IDaoFactory;
import org.schooldesk.dao.IUserDao;
import org.schooldesk.dto.IUser;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AbstractTest {
	private IServiceFactory serviceFactory;

	@Before
	public void init() {
		// TODO: use DaoFactory.create() when dao-impl will be adjusted
		IUser user = mock(IUser.class);
		when(user.getId()).thenReturn(42L);

		IUserDao userDao = mock(IUserDao.class);
		when(userDao.createDto()).thenReturn(user);
		try {
			when(userDao.save(any(IUser.class))).thenReturn(user);
		}
		catch (DataAccessException e) {
			// never happens
		}

		IDaoFactory daoFactory = mock(IDaoFactory.class);
		when(daoFactory.getDao(IUserDao.class)).thenReturn(userDao);

		setServiceFactory(ServiceFactory.create(daoFactory));
	}

	protected IServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	private void setServiceFactory(IServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}
}
