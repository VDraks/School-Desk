package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.dao.*;
import org.schooldesk.dto.*;
import org.schooldesk.hibernateobjects.*;
import org.slf4j.*;

import java.util.*;


public abstract class AbstractDao<T extends IDto> implements IDao<T> {
	private static final Logger logger = LoggerFactory.getLogger(CoreApi.class);

	private Class<? extends T> dtoClass;
	private Class<? extends AbstractCore> coreClass;

	private CoreApi coreApi;

	public AbstractDao(CoreApi coreApi, Class<? extends T> dtoClass, Class<? extends AbstractCore> coreClass) {
		this.coreApi = coreApi;
		this.dtoClass = dtoClass;
		this.coreClass = coreClass;
	}

	protected CoreApi getApi() {
		return coreApi;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T save(T entity) throws DataAccessException {
		try {
			return (T) getApi().save(createCoreObject(entity)).toDto();
		}
		catch (HibernateException ex) {
			throw new DataAccessException("Could not save entity %s", entity);
		}
	}

	@Override
	public Set<T> save(Set<T> entities) throws DataAccessException {
		try {
			Set<AbstractCore> coreObjects = new HashSet<>(entities.size());
			for (T entity : entities) {
				coreObjects.add(createCoreObject(entity));
			}
			getApi().save(coreObjects);
			return toDto(getApi().refresh(coreObjects));
		}
		catch (HibernateException ex) {
			throw new DataAccessException("Could not save entities");
		}
	}

	@Override
	public void update(T entity) throws DataAccessException {
		try {
			getApi().update(getCoreObject(entity));
		}
		catch (HibernateException ex) {
			throw new DataAccessException("Could not update entity %s", entity);
		}
	}

	@Override
	public void update(Set<T> entities) throws DataAccessException {
		try {
			Set<AbstractCore> coreObjects = new HashSet<>(entities.size());
			for (T entity : entities) {
				coreObjects.add(getCoreObject(entity));
			}
			getApi().update(coreObjects);
		}
		catch (HibernateException ex) {
			throw new DataAccessException("Could not update entities");
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public T loadById(Long id) throws DataAccessException {
		try {
			AbstractCore result = getApi().loadById(coreClass, id);
			return result == null ?
			       null :
			       (T) result.toDto();
		}
		catch (HibernateException e) {
			throw new DataAccessException("Could not load entity of class %s", dtoClass.getName());
		}
	}

	@Override
	public Set<T> loadByIds(Set<Long> ids) throws DataAccessException {
		try {
			return toDto(new HashSet<>(getApi().loadByIds(coreClass, ids)));
		}
		catch (HibernateException e) {
			throw new DataAccessException("Could not load entities of class %s", dtoClass.getName());
		}
	}

	@SuppressWarnings("unchecked")
	public Set<T> loadAll() throws DataAccessException {
		try {
			return toDto(getApi().loadAll(coreClass));
		}
		catch (HibernateException e) {
			throw new DataAccessException("Could not load entities of class %s", dtoClass.getName());
		}
	}

	@Override
	public void delete(Long id) throws DataAccessException {
		try {
			getApi().delete(coreClass, id);
		}
		catch (HibernateException e) {
			throw new DataAccessException("Could not delete entity of class %s with id = %d", dtoClass.getName(), id);
		}
	}

	protected AbstractCore getCoreObject(IDto entity) throws HibernateException {
		AbstractCore coreObject = getApi().loadById(coreClass, entity.getId());
		coreObject.fromDto(entity, getApi());
		return coreObject;
	}

	protected abstract AbstractCore createCoreObject(T entity);

	@SuppressWarnings("unchecked")
	private Set<T> toDto(Set<? extends IDtoable> objects) {
		Set<T> result = new HashSet<>(objects.size());
		for (IDtoable coreObject : objects) {
			result.add((T) coreObject.toDto());
		}
		return result;
	}
}
