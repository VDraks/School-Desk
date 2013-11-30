package org.schooldesk.dao.hibernateimpl;

import java.util.*;

import org.hibernate.*;
import org.schooldesk.dao.*;


public class HibernateDaoFactory extends DaoFactory implements IDaoFactory {
	private Map<Class<? extends IDao<?>>, IDao<?>> daoPool = new HashMap<>();
	private SessionFactory sessionFactory;

	public HibernateDaoFactory(String login, String password, String connectionURL) {
		sessionFactory = HibernateConfiguration.buildSessionFactory(login, password, connectionURL);
	}

	@Override
	public <T extends IDao<?>> T getDao(Class<T> daoClass) {
		return getFromPool(daoClass);
	}

	@SuppressWarnings("unchecked")
	private <T extends IDao<?>> T getFromPool(Class<T> daoClass) {
		IDao<?> dao = daoPool.get(daoClass);
		if (dao == null) {
			if (daoClass == IGroupDao.class) {
				daoPool.put(daoClass, dao = new GroupDao(sessionFactory));
			}
			else if (daoClass == IRightDao.class) {
				daoPool.put(daoClass, dao = new RightDao(sessionFactory));
			}
			else if (daoClass == IUserDao.class) {
				daoPool.put(daoClass, dao = new UserDao(sessionFactory));
			}
		}
		return (T) dao;
	}
}
