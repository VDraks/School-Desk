package org.schooldesk.core.services.impl;

import org.schooldesk.core.services.IService;
import org.schooldesk.core.services.ITestService;
import org.schooldesk.core.services.IUserService;
import org.schooldesk.core.services.ServiceFactory;
import org.schooldesk.core.services.impl.utils.StupidPasswordGenerator;
import org.schooldesk.dao.IDaoFactory;

import java.util.HashMap;
import java.util.Map;


public class ServiceFactoryImpl extends ServiceFactory {
	private ServicePool servicePool;

	@Override
	protected void configure(IDaoFactory daoFactory) {
		servicePool = new ServicePool();
		servicePool.registerService(ITestService.class, new TestServiceImpl(daoFactory));
		servicePool.registerService(IUserService.class, new UserServiceImpl(daoFactory, new StupidPasswordGenerator()));
	}

	@Override
	public <T extends IService> T getService(Class<T> serviceInterface) {
		return servicePool.getService(serviceInterface);
	}


	static class ServicePool {
		private final Map<Class<? extends IService>, IService> internalPool;

		public ServicePool() {
			this.internalPool = new HashMap<>();
		}

		public <T extends IService> void registerService(Class<T> serviceInterface, T service) {
			if (serviceInterface == null) {
				throw new NullPointerException("serviceInterface should not be null");
			}
			internalPool.put(serviceInterface, service);
		}

		public <T extends IService> T getService(Class<T> serviceInterface) {
			return serviceInterface.cast(internalPool.get(serviceInterface));
		}
	}
}
