package org.schooldesk.core.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.schooldesk.core.services.IService;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.core.services.IUserService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;


public class ServicePoolTests {
	private ServiceFactoryImpl.ServicePool servicePool;

	@Before
	public void init() {
		servicePool = new ServiceFactoryImpl.ServicePool();
	}

	@Test
	public void CanRegisterAndGetService() {
		IUserService initialService = mock(IUserService.class);
		servicePool.registerService(IUserService.class, initialService);
		IUserService receivedService = servicePool.getService(IUserService.class);
		assertThat(initialService, is(receivedService));
	}

	@Test
	public void ReturnsNullIfUnregistered() {
		servicePool.registerService(IUserService.class, mock(IUserService.class));
		IService receivedService = servicePool.getService(ITestService.class);
		assertThat(receivedService, is(nullValue()));
	}
}
