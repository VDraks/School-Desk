package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.hibernate.criterion.*;
import org.schooldesk.core.*;
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
		catch (HibernateException e) {
			logger.warn("Cannot rollback transaction", e);
		}

		try {
			getSession().close();
		}
		catch (HibernateException e) {
			logger.warn("Cannot close session", e);
		}
	}

	public AbstractCore save(AbstractCore object) throws HibernateException {
		try {
			beginTransaction();
			getSession().save(object);
			commitTransaction();
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
	public AbstractCore loadById(Class<? extends AbstractCore> objectClass, Long id) throws HibernateException {
		Criteria criteria = getSession().createCriteria(objectClass);
		criteria.add(Restrictions.eq("id", id));
		return (AbstractCore) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractCore> T loadByIdSafe(Class<T> objectClass, Long id) throws HibernateException {
		try {
			return (T) loadById(objectClass, id);
		}
		catch (HibernateException ex) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<AbstractCore> loadByIds(Class<? extends AbstractCore> objectsClass, Collection<Long> ids) throws HibernateException {
		try {
			Criteria criteria = getSession().createCriteria(objectsClass);
			criteria.add(Restrictions.in("id", ids));
			return criteria.list();
		}
		catch (HibernateException ex) {
			rollbackTransaction();
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractCore> List<T> loadByIdsSafe(Class<T> objectsClass, Collection<Long> ids) {
		try {
			return (List<T>) loadByIds(objectsClass, ids);
		}
		catch (HibernateException e) {
			return Collections.emptyList();
		}
	}

	@SuppressWarnings("unchecked")
	public Set<AbstractCore> loadAll(Class<? extends AbstractCore> objectClass) throws HibernateException {
		try {
			Criteria criteria = getSession().createCriteria(objectClass);
			return new HashSet<AbstractCore>(criteria.list());
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
