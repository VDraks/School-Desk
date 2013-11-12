package org.schooldesk.dao;

import org.schooldesk.dto.IDto;

import java.util.Set;


public interface IDao<T extends IDto>
{
	public T save(T entity) throws Exception;
	public T update(T entity) throws Exception;
	public T loadById(Long id) throws Exception;
	public Set<T> loadByIds(Set<Long> ids) throws Exception;
	public Set<T> loadAll() throws Exception;
	public void delete(Long id) throws Exception;
}
