package org.schooldesk.core;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.schooldesk.dao.impl.Dtoable;
import org.schooldesk.dto.impl.*;

@Entity
public abstract class CoreObject implements Dtoable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	protected Dto mapDto(Dto dto) {
		dto.setId(getId());
		return dto;
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
