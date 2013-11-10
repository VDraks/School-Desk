package org.schooldesk.dao.impl;

import org.schooldesk.dao.*;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory implements IDaoFactory {
	// hibernate api should be here
	private Map<Class<? extends IDao<?>>, IDao<?>> daoPool = new HashMap<Class<? extends IDao<?>>, IDao<?>>();

//	public DaoFactory(settings) {
//	}

	@Override
	public <T extends IDao<?>> T getDao(Class<T> daoClass) {
		return getFromPool(daoClass);
	}

	@SuppressWarnings("unchecked")
	private <T extends IDao<?>> T getFromPool(Class<T> daoClass)
	{
		IDao<?> dao = daoPool.get(daoClass);
		if (dao == null)
		{
			if (daoClass == IGroupDao.class)
			{
				daoPool.put(daoClass, dao = new GroupDao());
			}
			else if (daoClass == IRightDao.class)
			{
				daoPool.put(daoClass, dao = new RightDao());
			}
			else if (daoClass == IUserDao.class)
			{
				daoPool.put(daoClass, dao = new UserDao());
			}
		}
		return (T) dao;
	}
}
