package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "courses")
public class CourseCore extends AbstractCore {
	private String name;
	private List<CourseSectionCore> courseSections;

	@UsedForMapping
	private EducationStageCore educationStage;

	public CourseCore() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "course", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
	public List<CourseSectionCore> getCourseSections() {
		return courseSections;
	}

	public void setCourseSections(List<CourseSectionCore> courseSections) {
		this.courseSections = courseSections;
	}

	@ManyToOne
	@JoinColumn(name = "education_stage_id")
	@UsedForMapping
	@SuppressWarnings("unused")
	public EducationStageCore getEducationStage() {
		return educationStage;
	}

	@UsedForMapping
	@SuppressWarnings("unused")
	public void setEducationStage(EducationStageCore educationStage) {
		this.educationStage = educationStage;
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
