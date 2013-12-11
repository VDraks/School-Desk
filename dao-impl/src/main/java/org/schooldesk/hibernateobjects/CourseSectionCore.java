package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;


@Entity
@Table(name = "course_sections")
public class CourseSectionCore extends AbstractCore {
	private String name;
	private TestCore test;

	@UsedForMapping
	private CourseCore course;

	public CourseSectionCore() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	public TestCore getTest() {
		return test;
	}

	public void setTest(TestCore test) {
		this.test = test;
	}

	@ManyToOne
	@UsedForMapping
	@SuppressWarnings("unused")
	public CourseCore getCourse() {
		return course;
	}

	@UsedForMapping
	@SuppressWarnings("unused")
	public void setCourse(CourseCore course) {
		this.course = course;
	}

	@Override
	@SuppressWarnings("deprecation")
	public CourseSectionDto toDto() {
		return mapDto(new CourseSectionDto());
	}

	@Override
	protected CourseSectionDto mapDto(AbstractDto dto) {
		CourseSectionDto courseSectionDto = (CourseSectionDto) super.mapDto(dto);
		courseSectionDto.setName(getName());
		courseSectionDto.setTestId(getId(getTest()));
		return courseSectionDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		ICourseSection courseSection = (ICourseSection) dto;
		setName(courseSection.getName());
		setTest(coreApi.loadById(TestCore.class, courseSection.getTestId()));
	}
}
