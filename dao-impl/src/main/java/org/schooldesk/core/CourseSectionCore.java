package org.schooldesk.core;

import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


public class CourseSectionCore extends AbstractCore {
	private String name;

	public CourseSectionCore() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public CourseSectionDto toDto() {
		return mapDto(new CourseSectionDto());
	}

	@Override
	protected CourseSectionDto mapDto(AbstractDto dto) {
		CourseSectionDto courseSectionDto = (CourseSectionDto) super.mapDto(dto);
		courseSectionDto.setName(getName());
		return courseSectionDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) {
		CourseSectionDto courseSectionDto = (CourseSectionDto) dto;
		setName(courseSectionDto.getName());
	}
}
