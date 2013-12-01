package org.schooldesk.dao;

import org.schooldesk.dto.IDto;

import java.util.Set;


public interface IDao<T extends IDto> {
	T save(T entity) throws DataAccessException;
	Set<T> save(Set<T> entities) throws DataAccessException;
	void update(T entity) throws DataAccessException;
	void update(Set<T> entities) throws DataAccessException;
	T loadById(Long id) throws DataAccessException;
	Set<T> loadByIds(Set<Long> ids) throws DataAccessException;
	Set<T> loadAll() throws DataAccessException;
	void delete(Long id) throws DataAccessException;
}
