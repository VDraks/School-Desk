package org.schooldesk.dao.impl;

import java.util.*;

import org.schooldesk.dao.*;
import org.schooldesk.dto.*;

public abstract class Dao<T extends IDto> implements IDao<T> {
	// hibernate api should be here

//	public Dao(API api)	{
//	}

	@Override
	public T save(T entity) {
		return entity; //TODO add save code
	}

	@Override
	public T update(T entity) {
		return entity; //TODO add update code
	}

	@Override
	public T loadById(Long id) {
		return null; //TODO add load code
	}

	@Override
	public Set<T> loadByIds(Set<Long> ids) {
		return Collections.emptySet(); //TODO add load code
	}

	@Override
	public Set<T> loadAll() {
		return Collections.emptySet(); //TODO add load code
	}

	@Override
	public void delete(Long id) {
		//TODO add delete code
	}
}
