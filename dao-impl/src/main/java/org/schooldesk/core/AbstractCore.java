package org.schooldesk.core;

import java.util.*;

import javax.persistence.*;

import org.schooldesk.dao.impl.*;
import org.schooldesk.dto.impl.*;

@Entity
public abstract class AbstractCore implements IDtoable
{
	
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

	protected AbstractDto mapDto(AbstractDto dto) {
		dto.setId(id);
		return dto;
	}

	protected static List<Long> getIds(List<? extends IDtoable> dtoables) {
		List<Long> ids = new ArrayList<Long>(dtoables.size());
		for (IDtoable dtoable : dtoables)
		{
			ids.add(dtoable.getId());
		}
		return ids;
	}

	protected static Set<Long> getIds(Set<? extends IDtoable> dtoables) {
		Set<Long> ids = new HashSet<Long>(dtoables.size());
		for (IDtoable dtoable : dtoables)
		{
			ids.add(dtoable.getId());
		}
		return ids;
	}
}
