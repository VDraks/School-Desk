package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.hibernate.annotations.*;
import org.hibernate.annotations.FetchMode;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.*;


@Entity
public class CourseCore extends AbstractCore {
	private String name;


	private List<CourseSectionCore> courseSections;

	public CourseCore() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "source", cascade = {javax.persistence.CascadeType.ALL})
	public List<CourseSectionCore> getCourseSections() {
		return courseSections;
	}

	public void setCourseSections(List<CourseSectionCore> courseSections) {
		this.courseSections = courseSections;
	}

	@Override
	@SuppressWarnings("deprecation")
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
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		ICourse course = (ICourse) dto;
		setName(course.getName());
		setCourseSections(coreApi.loadByIds(CourseSectionCore.class, course.getCourseSectionIds()));
	}
}
