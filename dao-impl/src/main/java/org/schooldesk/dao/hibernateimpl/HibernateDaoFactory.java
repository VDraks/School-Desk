package org.schooldesk.dao.hibernateimpl;

import java.io.*;
import java.util.*;

import org.hibernate.*;
import org.schooldesk.dao.*;


public class HibernateDaoFactory extends DaoFactory implements IDaoFactory {
	private static final String CONFIG_FILE = "dao.factory.config";

	private Map<Class<? extends IDao<?>>, IDao<?>> daoPool = new HashMap<>();
	private SessionFactory sessionFactory;

	public HibernateDaoFactory() throws IOException {
		Properties configuration = getFactoryConfiguration();
		sessionFactory = HibernateConfiguration.buildSessionFactory(
				configuration.getProperty("db_login"),
				configuration.getProperty("db_password"),
				configuration.getProperty("db_connection")
		);
	}

	private Properties getFactoryConfiguration() throws IOException {
		Properties configuration = new Properties();
		try (InputStream is = new BufferedInputStream(new FileInputStream(CONFIG_FILE))) {
			configuration.load(is);
		}
		return configuration;
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
