package org.schooldesk.dao;

import org.schooldesk.dto.IDto;


public interface IDao<T extends IDto> {
	public T save(T entity);
	public T update(T entity);
	public T loadById(Long id);
	public void delete(Long id);
}
