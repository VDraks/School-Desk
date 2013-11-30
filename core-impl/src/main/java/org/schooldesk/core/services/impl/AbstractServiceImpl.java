package org.schooldesk.core.services.impl;

import org.schooldesk.dao.IDaoFactory;


abstract class AbstractServiceImpl {
	private final IDaoFactory daoFactory;

	public AbstractServiceImpl(IDaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	protected IDaoFactory getDaoFactory() {
		return daoFactory;
	}
}
