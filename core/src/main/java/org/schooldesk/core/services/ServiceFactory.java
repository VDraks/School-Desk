package org.schooldesk.core.services;

import org.schooldesk.dao.IDaoFactory;

import java.util.Iterator;
import java.util.ServiceLoader;


public abstract class ServiceFactory implements IServiceFactory {
	private static final ServiceLoader<ServiceFactory> factoryLoader = ServiceLoader.load(ServiceFactory.class);

	public static ServiceFactory create(IDaoFactory daoFactory) {
		final Iterator<ServiceFactory> factoryLoaderIterator = factoryLoader.iterator();
		if (factoryLoaderIterator.hasNext()) {
			final ServiceFactory factory = factoryLoaderIterator.next();
			factory.configure(daoFactory);
			return factory;
		}

		throw new NoImplementationException();
	}

	protected abstract void configure(IDaoFactory daoFactory);
}
