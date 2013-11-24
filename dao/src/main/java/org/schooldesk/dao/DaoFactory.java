package org.schooldesk.dao;

import java.util.Iterator;
import java.util.ServiceLoader;


public abstract class DaoFactory implements IDaoFactory {
	private static final ServiceLoader<DaoFactory> factoryLoader = ServiceLoader.load(DaoFactory.class);

	public static DaoFactory create() {
		final Iterator<DaoFactory> factoryLoaderIterator = factoryLoader.iterator();
		if (factoryLoaderIterator.hasNext()) {
			return factoryLoaderIterator.next();
		}

		throw new NoImplementationException();
	}
}
