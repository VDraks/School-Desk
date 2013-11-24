package org.schooldesk.dao;

import org.schooldesk.dto.IDto;

import java.util.Set;


public interface IDao<T extends IDto> {
	public T save(T entity) throws DataAccessException;
	public Set<T> save(Set<T> entities) throws DataAccessException;
	public void update(T entity) throws DataAccessException;
	public void update(Set<T> entities) throws DataAccessException;
	public T loadById(Long id) throws DataAccessException;
	public Set<T> loadByIds(Set<Long> ids) throws DataAccessException;
	public Set<T> loadAll() throws DataAccessException;
	public void delete(Long id) throws DataAccessException;
}
