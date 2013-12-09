package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.*;

import java.util.HashMap;
import java.util.Map;


public class InMemoryDaoFactory extends DaoFactory implements IDaoFactory {
	private final DaoPool daoPool;

	public InMemoryDaoFactory() {
		daoPool = new DaoPool();
		daoPool.registerDao(IUserDao.class, new InMemoryUserDao());
		daoPool.registerDao(IEducationStageDao.class, new InMemoryEducationStageDao());
		daoPool.registerDao(ICourseDao.class, new InMemoryCourseDao());
		daoPool.registerDao(ICourseSectionDao.class, new InMemoryCourseSectionDao());
		daoPool.registerDao(ITestDao.class, new InMemoryTestDao());
		daoPool.registerDao(ITestQuestionDao.class, new InMemoryTestQuestionDao());
		daoPool.registerDao(ITestAnswerDao.class, new InMemoryTestAnswerDao());
		daoPool.registerDao(ITestResultDao.class, new InMemoryTestResultDao());
		daoPool.registerDao(IUserTestAnswerDao.class, new InMemoryUserTestAnswerDao());
	}

	@Override
	public <T extends IDao<?>> T getDao(Class<T> daoClass) {
		return daoPool.getDao(daoClass);
	}


	static class DaoPool {
		private final Map<Class<? extends IDao>, IDao> internalPool;

		public DaoPool() {
			this.internalPool = new HashMap<>();
		}

		public <T extends IDao> void registerDao(Class<T> daoInterface, T dao) {
			if (daoInterface == null) {
				throw new NullPointerException("daoInterface should not be null");
			}
			internalPool.put(daoInterface, dao);
		}

		public <T extends IDao> T getDao(Class<T> serviceInterface) {
			return serviceInterface.cast(internalPool.get(serviceInterface));
		}
	}
}
