package org.schooldesk.dao;

import java.util.*;

import org.schooldesk.dto.*;


public interface IDao<T extends IDto>
{
	public T save(T entity) throws Exception;
	public Set<T> save(Set<T> entities) throws Exception;
	public void update(T entity) throws Exception;
	public void update(Set<T> entities) throws Exception;
	public T loadById(Long id) throws Exception;
	public Set<T> loadByIds(Set<Long> ids) throws Exception;
	public Set<T> loadAll() throws Exception;
	public void delete(Long id) throws Exception;
}
