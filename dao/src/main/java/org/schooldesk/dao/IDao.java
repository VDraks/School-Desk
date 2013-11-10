package org.schooldesk.dao;

import org.schooldesk.dto.IDto;

import java.util.Set;


public interface IDao<T extends IDto> {
	public T save(T entity);
	public T update(T entity);
	public T loadById(Long id);
	public Set<T> loadByIds(Set<Long> ids);
	public Set<T> loadAll();
	public void delete(Long id);
}
