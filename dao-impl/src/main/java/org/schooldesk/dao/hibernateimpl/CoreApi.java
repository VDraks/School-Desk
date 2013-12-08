package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.hibernate.criterion.*;
import org.schooldesk.hibernateobjects.*;
import org.slf4j.*;

import java.util.*;


public class CoreApi {
	private static final Logger logger = LoggerFactory.getLogger(CoreApi.class);

	private SessionFactory sessionFactory;
	private /*final ThreadLocal<*/ Session/*>*/ session/* = new ThreadLocal<Session>()*/;

	public CoreApi(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	protected void beginTransaction() {
		getSession().beginTransaction();
	}

	protected void commitTransaction() {
		getSession().getTransaction().commit();
	}

	protected void rollbackTransaction() {
		try {
			getSession().getTransaction().rollback();
		}
		catch (HibernateException ex) {
			logger.warn("Cannot rollback transaction", ex);
		}

		try {
			getSession().close();
		}
		catch (HibernateException ex) {
			logger.warn("Cannot close session", ex);
		}
	}

	public AbstractCore save(AbstractCore object) throws HibernateException {
		try {
			beginTransaction();
			getSession().save(object);
			commitTransaction();
//			getSession().flush();
			getSession().refresh(object);
			return object;
		}
		catch (HibernateException ex) {
			rollbackTransaction();
			throw ex;
		}
	}

	public void save(Set<AbstractCore> objects) throws HibernateException {
		try {
			beginTransaction();
			for (AbstractCore object : objects) {
				getSession().save(object);
			}
			commitTransaction();
		}
		catch (HibernateException ex) {
			rollbackTransaction();
			throw ex;
		}
	}

	public Set<AbstractCore> refresh(Set<AbstractCore> objects) throws HibernateException {
		for (AbstractCore object : objects) {
			getSession().refresh(object);
		}
		return objects;
	}

	public void update(AbstractCore object) throws HibernateException {
		try {
			beginTransaction();
			getSession().saveOrUpdate(object);
			commitTransaction();
		}
		catch (HibernateException ex) {
			rollbackTransaction();
			throw ex;
		}
	}

	public void update(Set<AbstractCore> objects) throws HibernateException {
		try {
			beginTransaction();
			for (AbstractCore object : objects) {
				getSession().saveOrUpdate(object);
			}
			commitTransaction();
		}
		catch (HibernateException ex) {
			rollbackTransaction();
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractCore> T loadById(Class<T> objectClass, Long id) throws HibernateException {
		Criteria criteria = getSession().createCriteria(objectClass);
		criteria.add(Restrictions.eq("id", id));
		return (T) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractCore> List<T> loadByIds(Class<T> objectsClass, Collection<Long> ids) throws HibernateException {
		try {
			Criteria criteria = getSession().createCriteria(objectsClass);
			criteria.add(Restrictions.in("id", ids));
			return (List<T>) criteria.list();
		}
		catch (HibernateException ex) {
			rollbackTransaction();
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractCore> Set<T> loadAll(Class<T> objectClass) throws HibernateException {
		try {
			Criteria criteria = getSession().createCriteria(objectClass);
			return new HashSet<T>(criteria.list());
		}
		catch (HibernateException ex) {
			rollbackTransaction();
			throw ex;
		}
	}

	public void delete(Class<? extends AbstractCore> objectClass, Long id) throws HibernateException {
		try {
			beginTransaction();
			Query query = getSession().createQuery("delete " + objectClass.getName() + " where id = " + id);
			query.executeUpdate();
			commitTransaction();
		}
		catch (HibernateException ex) {
			rollbackTransaction();
			throw ex;
		}
	}
}
