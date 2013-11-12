package org.schooldesk.dao.impl;

import java.lang.reflect.*;
import java.util.*;
import java.util.logging.*;

import org.hibernate.*;
import org.hibernate.criterion.*;
import org.schooldesk.core.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;

import java.lang.InstantiationException;

public abstract class AbstractDao<T extends IDto> implements IDao<T>
{
	private Class<? extends AbstractCore> coreClass;
	
	private /*final ThreadLocal<*/Session/*>*/ session/* = new ThreadLocal<Session>()*/;
	private SessionFactory sessionFactory;
	private static final Logger log = Logger.getAnonymousLogger();
	
	public AbstractDao(SessionFactory sessionFactory, Class<? extends AbstractCore> coreClass)
	{
		this.sessionFactory = sessionFactory;
		this.coreClass = coreClass;
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
	
	protected void beginTransaction()
	{
		getSession().beginTransaction();
	}

	protected void commitTransaction()
	{
		getSession().getTransaction().commit();
	}

	protected void rollbackTransaction()
	{
		try
		{
			getSession().getTransaction().rollback();
		}
		catch (HibernateException e)
		{
			log.log(Level.WARNING, "Cannot rollback transaction", e);
		}
		
		try
		{
			getSession().close();
		}
		catch (HibernateException e)
		{
			log.log(Level.WARNING, "Cannot close session", e);
		}
		
//		CAbstractAPI.session.set(null);
	}

	@Override
	public T save(T entity) throws Exception
	{
		try
		{
			beginTransaction();

			AbstractCore coreObject = createCoreObject(entity);
			getSession().save(coreObject);
			commitTransaction();

			return (T) coreObject.toDto();
		}
		catch (HibernateException e)
		{
			rollbackTransaction();
			throw new Exception("Could not save entity " + entity, e);
		}
		finally
		{
			close();
		}
	}

	@Override
	public Set<T> save(Set<T> entities) throws Exception
	{
		try
		{
			Set<AbstractCore> result = new HashSet<>(entities.size());
			beginTransaction();
			
			for (T entity : entities)
			{
				AbstractCore coreObject = createCoreObject(entity);
				getSession().saveOrUpdate(coreObject);
				result.add(coreObject);
			}
			
			commitTransaction();
			return toDto(result);
		}
		catch (HibernateException e)
		{
			rollbackTransaction();
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
	public void update(T entity) throws Exception
	{
		try
		{
			beginTransaction();

			AbstractCore coreObject = getCoreObject(entity.getId());
			coreObject.fromDto(entity);
			getSession().saveOrUpdate(coreObject);
			
			commitTransaction();
		}
		catch (HibernateException e)
		{
			rollbackTransaction();
			throw new Exception("Could not save entity " + entity, e);
		}
		finally
		{
			close();
		}
	}

	@Override
	public void update(Set<T> entities) throws Exception
	{
		try
		{
			beginTransaction();
			
			for (T entity : entities)
			{
				AbstractCore coreObject = getCoreObject(entity.getId());
				coreObject.fromDto(entity);
				getSession().saveOrUpdate(coreObject);
			}
			
			commitTransaction();
		}
		catch (HibernateException e)
		{
			rollbackTransaction();
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
		try
		{
			beginTransaction();

			AbstractCore coreObject = getCoreObject(id);
			
			rollbackTransaction();

			return (T) coreObject.toDto();
		}
		catch (HibernateException e)
		{
			rollbackTransaction();
			throw new Exception("Could not load entities with class " + coreClass.getName(), e);
		}
		finally
		{
			close();
		}
	}

	@Override
	public Set<T> loadByIds(Set<Long> ids) throws Exception
	{
		try
		{
			beginTransaction();

			Set<? extends AbstractCore> result = getCoreObjects(ids);

			rollbackTransaction();
			return toDto(result);
		}
		catch (HibernateException e)
		{
			rollbackTransaction();
			throw new Exception("Could not load entities with class " + coreClass.getName(), e);
		}
		finally
		{
			close();
		}
	}

	@SuppressWarnings("unchecked")
	public Set<T> loadAll() throws Exception
	{
		try
		{
			beginTransaction();
			
			Criteria criteria = getSession().createCriteria(coreClass);
			Set<? extends AbstractCore> result = new HashSet<AbstractCore>(criteria.list());
			
			rollbackTransaction();
			return toDto(result);
		}
		catch (HibernateException e)
		{
			rollbackTransaction();
			throw new Exception("Could not load entities with class " + coreClass.getName(), e);
		}
		finally
		{
			close();
		}
	}

	@Override
	public void delete(Long id) throws Exception
	{
		try
		{
			beginTransaction();
			deleteCoreObject(id);
			commitTransaction();
		}
		catch (HibernateException e)
		{
			rollbackTransaction();
			throw new Exception("Could not delete entity with class " + coreClass.getName() + " with id = " + id, e);
		}
		finally
		{
			close();
		}
	}

	protected AbstractCore getCoreObject(Long id) {
		Criteria criteria = getSession().createCriteria(coreClass);
		criteria.add(Restrictions.eq("id", id));
		return (AbstractCore) criteria.uniqueResult();
	}

	protected Set<? extends AbstractCore> getCoreObjects(Set<Long> ids)
	{
		Criteria criteria = getSession().createCriteria(coreClass);
		criteria.add(Restrictions.in("id", ids));

		return new HashSet<AbstractCore>(criteria.list());
	}

	private AbstractCore createCoreObject(T entity)
	{
		try
		{
			AbstractCore coreObject = coreClass.getConstructor(null).newInstance(null);
			coreObject.fromDto(entity);
			return coreObject;
		}
		catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e)
		{
			log.log(Level.WARNING, "Cannot create core object", e);
		}
		return null;
	}

	private void deleteCoreObject(Long id)
	{
		Query query = getSession().createQuery("delete " + coreClass.getName() + " where id = " + id);
		query.executeUpdate();
	}

	private Set<T> toDto(Set<? extends IDtoable> objects)
	{
		Set<T> result = new HashSet<>(objects.size());
		for (IDtoable coreObject : objects)
		{
			result.add((T) coreObject.toDto());
		}
		return result;
	}
}
