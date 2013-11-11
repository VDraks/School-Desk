package org.schooldesk.core;

import java.util.*;

public abstract class CoreObject implements Dtoable {
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	protected static List<Long> getIds(List<? extends Dtoable> dtoables) {
		List<Long> ids = new ArrayList<Long>(dtoables.size());
		for (Dtoable dtoable : dtoables)
		{
			ids.add(dtoable.getId());
		}
		return ids;
	}

	protected static Set<Long> getIds(Set<? extends Dtoable> dtoables) {
		Set<Long> ids = new HashSet<Long>(dtoables.size());
		for (Dtoable dtoable : dtoables)
		{
			ids.add(dtoable.getId());
		}
		return ids;
	}
}
