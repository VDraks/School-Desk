package org.schooldesk.core;

import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
public class CourseCore extends AbstractCore {
	private String name;

	@OneToMany
	private List<CourseSectionCore> courseSections;

	public CourseCore() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CourseSectionCore> getCourseSections() {
		return courseSections;
	}

	public void setCourseSections(List<CourseSectionCore> courseSections) {
		this.courseSections = courseSections;
	}

	@Override
	public CourseDto toDto() {
		return mapDto(new CourseDto());
	}

	@Override
	protected CourseDto mapDto(AbstractDto dto) {
		CourseDto courseDto = (CourseDto) super.mapDto(dto);
		courseDto.setName(getName());
		courseDto.setCourseSectionIds(getIds(getCourseSections()));
		return courseDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) {
		CourseDto courseDto = (CourseDto) dto;
		setName(courseDto.getName());
		setCourseSections(coreApi.loadByIdsSafe(CourseSectionCore.class, courseDto.getCourseSectionIds()));
	}
}
