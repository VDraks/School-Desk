package org.schooldesk.dao;

import org.schooldesk.dto.IDTO;


public interface IDAO<T extends IDTO> {
	public T save(T entity);
	public T update(T entity);
	public T loadById(Long id);
	public void delete(Long id);
}
