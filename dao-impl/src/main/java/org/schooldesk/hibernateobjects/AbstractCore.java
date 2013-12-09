package org.schooldesk.hibernateobjects;

import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@MappedSuperclass
public abstract class AbstractCore implements IDtoable {

	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	protected static Set<Long> getIds(Set<? extends IDtoable> dtoables) {
		Set<Long> ids = new HashSet<Long>(dtoables.size());
		for (IDtoable dtoable : dtoables) {
			ids.add(dtoable.getId());
		}
		return ids;
	}

	protected static List<Long> getIds(List<? extends IDtoable> dtoables) {
		List<Long> ids = new ArrayList<>(dtoables.size());
		for (IDtoable dtoable : dtoables) {
			ids.add(dtoable.getId());
		}
		return ids;
	}
}
