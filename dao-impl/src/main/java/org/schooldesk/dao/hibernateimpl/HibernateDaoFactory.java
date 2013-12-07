package org.schooldesk.dao.hibernateimpl;

import org.schooldesk.dao.*;

import java.io.*;
import java.util.*;


/**
 * A DAO factory based on hibernate implementation
 */
public class HibernateDaoFactory extends DaoFactory implements IDaoFactory {
	private static final String CONFIG_FILE = "dao.factory.config";

	private Map<Class<? extends IDao<?>>, IDao<?>> daoPool = new HashMap<>();
	private CoreApi coreApi;

	/**
	 * Constructor for the DAO factory<br/>
	 * Needs <b>dao.factory.config</b> for the factory configuration with
	 * properties <b>db_login</b>, <b>db_password</b>, <b>db_connection</b> set.
	 *
	 * @throws IOException if config is missing or can't be read
	 */
	public HibernateDaoFactory() throws IOException {
		Properties configuration = getFactoryConfiguration();
		coreApi = new CoreApi(HibernateConfiguration.buildSessionFactory(
				configuration.getProperty("db_login"),
				configuration.getProperty("db_password"),
				configuration.getProperty("db_connection")
		));
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
			if (daoClass == ICourseDao.class) {
				daoPool.put(daoClass, dao = new CourseDao(coreApi));
			}
			else if (daoClass == ICourseSectionDao.class) {
				daoPool.put(daoClass, dao = new CourseSectionDao(coreApi));
			}
			else if (daoClass == IEducationStageDao.class) {
				daoPool.put(daoClass, dao = new EducationStageDao(coreApi));
			}
			else if (daoClass == IGroupDao.class) {
				daoPool.put(daoClass, dao = new GroupDao(coreApi));
			}
			else if (daoClass == IRightDao.class) {
				daoPool.put(daoClass, dao = new RightDao(coreApi));
			}
			else if (daoClass == ITestAnswerDao.class) {
				daoPool.put(daoClass, dao = new TestAnswerDao(coreApi));
			}
			else if (daoClass == ITestDao.class) {
				daoPool.put(daoClass, dao = new TestDao(coreApi));
			}
			else if (daoClass == ITestQuestionDao.class) {
				daoPool.put(daoClass, dao = new TestQuestionDao(coreApi));
			}
			else if (daoClass == ITestResultDao.class) {
				daoPool.put(daoClass, dao = new TestResultDao(coreApi));
			}
			else if (daoClass == IUserDao.class) {
				daoPool.put(daoClass, dao = new UserDao(coreApi));
			}
			else if (daoClass == IUserTestAnswerDao.class) {
				daoPool.put(daoClass, dao = new UserTestAnswerDao(coreApi));
			}
			else {
				throw new NoImplementationException();
			}
		}
		return (T) dao;
	}
}
