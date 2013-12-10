package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IDao;
import org.schooldesk.dao.inmemory.inmemory.Database;
import org.schooldesk.dto.IDto;

import java.util.HashSet;
import java.util.Set;


public abstract class InMemoryAbstractDao<T extends IDto> implements IDao<T> {
	@Override
	public T save(T entity) throws DataAccessException {
		Database.database.get(getClass()).put(entity.getId(), entity);
		return  entity;
	}

	@Override
	public Set<T> save(Set<T> entities) throws DataAccessException {
		for (T entity : entities){
			save(entity);
		}
		return entities;
	}

	@Override
	public void update(T entity) throws DataAccessException {
		Database.database.get(getClass()).put(entity.getId(), entity);
	}

	@Override
	public void update(Set<T> entities) throws DataAccessException {
		for (T entity : entities){
			update(entity);
		}
	}

	@Override
	public T loadById(Long id) throws DataAccessException {
		return (T) Database.database.get(getClass()).get(id);
	}

	@Override
	public Set<T> loadByIds(Set<Long> ids) throws DataAccessException {
		Set<T> result = new HashSet<>();
		for (Long id : ids){
			result.add(loadById(id));
		}
		return result;
	}

	@Override
	public Set<T> loadAll() throws DataAccessException {
		return (Set<T>) Database.database.get(getClass()).values();
	}

	@Override
	public void delete(Long id) throws DataAccessException {
		Database.database.get(getClass()).remove(id);
	}
}
