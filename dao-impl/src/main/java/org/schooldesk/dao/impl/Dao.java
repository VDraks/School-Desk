package org.schooldesk.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.schooldesk.dao.IDao;
import org.schooldesk.dto.IDto;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Dao<T extends IDto> implements IDao<T>
{
	private Class<? extends T> clazz;
	
	private /*final ThreadLocal<*/Session/*>*/ session/* = new ThreadLocal<Session>()*/;
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getAnonymousLogger();
	
	public Dao(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
		clazz = (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected Session getSession()
	{
//		Session session = (Session) CAbstractAPI.session.get();
		if (session == null)
		{
			session = sessionFactory.openSession();
//			CAbstractAPI.session.set(session);
		}
		return session;
	}
	
	protected void begin()
	{
		getSession().beginTransaction();
	}

	protected void commit()
	{
		getSession().getTransaction().commit();
	}

	protected void rollback()
	{
		try
		{
			getSession().getTransaction().rollback();
		}
		catch (HibernateException e)
		{
			log.log(Level.WARNING, "Cannot rollback", e);
		}
		
		try
		{
			getSession().close();
		}
		catch (HibernateException e)
		{
			log.log(Level.WARNING, "Cannot close", e);
		}
		
		getSession().close();
//		CAbstractAPI.session.set(null);
	}

	@Override
	public T save(T entity) throws Exception
	{
		//TODO factually incorrect. Entity should be core, not dao
		try
		{
			begin();
			
			getSession().save(entity);
			commit();
			
			return entity;
		}
		catch (HibernateException e)
		{
			rollback();
			throw new Exception("Could not save entity " + entity, e);
		}
		finally
		{
			close();
		}
	}
	
	public void save(List<T> entityList) throws Exception
	{
		//TODO factually incorrect. Entity should be core, not dao
		try
		{
			begin();
			
			for (T abstractData : entityList)
			{
				getSession().saveOrUpdate(abstractData);
			}
			
			commit();
		}
		catch (HibernateException e)
		{
			rollback();
			throw new Exception("Could not save entity ", e);
		}
		finally
		{
			close();
		}
	}
	
	protected void close()
	{
//		if (CAbstractAPI.session.get() != null && getSession().isOpen())
//		{
//			getSession().close();
//		}
//		CAbstractAPI.session.set(null);
	}

	@Override
	public T update(T entity) throws Exception
	{
		//TODO factually incorrect. Entity should be core, not dao
		try
		{
			begin();
			
			getSession().saveOrUpdate(entity);
			
			commit();
			
			return entity;
		}
		catch (HibernateException e)
		{
			rollback();
			throw new Exception("Could not save entity " + entity, e);
		}
		finally
		{
			close();
		}
	}
	
	public void update(List<T> entityList) throws Exception
	{
		//TODO factually incorrect. Entity should be core, not dao
		try
		{
			begin();
			
			for (T abstractData : entityList)
			{
				getSession().saveOrUpdate(abstractData);
			}
			
			commit();
		}
		catch (HibernateException e)
		{
			rollback();
			throw new Exception("Could not update entity ", e);
		}
		finally
		{
			close();
		}
	}

	@Override
	public T loadById(Long id) throws Exception
	{
		//TODO factually incorrect. Entity should be core, not dao
		try
		{
			begin();
			
			Criteria criteria = getSession().createCriteria(clazz);
			criteria.add(Restrictions.eq("id", id));
			
			@SuppressWarnings("unchecked")
			T result = (T) criteria.uniqueResult();
			
			commit();
			
			return result;
		}
		catch (HibernateException e)
		{
			rollback();
			throw new Exception("Could not load entities with class " + clazz.getName(), e);
		}
		finally
		{
			close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<T> loadByIds(Set<Long> ids) throws Exception
	{
		//TODO factually incorrect. Entity should be core, not dao
		try
		{
			Set<T> result = new HashSet<T>();
			begin();
			
			Criteria criteria = getSession().createCriteria(clazz);
			criteria.add(Restrictions.in("id", ids));
			
			result = new HashSet<T>(criteria.list());
			
			commit();
			
			return result;
		}
		catch (HibernateException e)
		{
			rollback();
			throw new Exception("Could not load entities with class " + clazz.getName(), e);
		}
		finally
		{
			close();
		}
	}

	@SuppressWarnings("unchecked")
	public Set<T> loadAll() throws Exception
	{
		//TODO factually incorrect. Entity should be core, not dao
		try
		{
			Set<T> result = new HashSet<T>();
			begin();
			
			Criteria criteria = getSession().createCriteria(clazz);
			
			result = new HashSet<T>(criteria.list());
			
			commit();
			
			return result;
		}
		catch (HibernateException e)
		{
			rollback();
			throw new Exception("Could not load entities with class " + clazz.getName(), e);
		}
		finally
		{
			close();
		}
	}

	@Override
	public void delete(Long id) throws Exception
	{
		//TODO factually incorrect. Entity should be core, not dao
		try
		{
			begin();
			
			Query query = getSession().createQuery("delete " + clazz.getName() + " where id = " + id);
			query.executeUpdate();
			
			commit();
		}
		catch (HibernateException e)
		{
			rollback();
			throw new Exception("Could not delete entity with class " + clazz.getName() + " with id = " + id, e);
		}
		finally
		{
			close();
		}
	}
}
