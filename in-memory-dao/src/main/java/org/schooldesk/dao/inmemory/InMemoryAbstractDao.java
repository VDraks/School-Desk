package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.DataAccessException;
import org.schooldesk.dao.IDao;
import org.schooldesk.dto.IDto;

import java.util.Set;


public abstract class InMemoryAbstractDao<T extends IDto> implements IDao<T> {
	@Override
	public T save(T entity) throws DataAccessException {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<T> save(Set<T> entities) throws DataAccessException {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(T entity) throws DataAccessException {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Set<T> entities) throws DataAccessException {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public T loadById(Long id) throws DataAccessException {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<T> loadByIds(Set<Long> ids) throws DataAccessException {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<T> loadAll() throws DataAccessException {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(Long id) throws DataAccessException {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
