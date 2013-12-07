package org.schooldesk.core;

import org.schooldesk.dao.hibernateimpl.CoreApi;
import org.schooldesk.dto.ICourseSection;
import org.schooldesk.dto.IDto;
import org.schooldesk.dto.impl.AbstractDto;
import org.schooldesk.dto.impl.CourseDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

		List<ICourseSection> courseSections = new ArrayList<>(getCourseSections().size());
		for (CourseSectionCore courseSectionCore : getCourseSections()) {
			courseSections.add(courseSectionCore.toDto());
		}

		List<Long> courseSectionsIds = new ArrayList<Long>();

		for (ICourseSection courseSection : courseSections)
		{
			courseSectionsIds.add(courseSection.getId());
		}

		courseDto.setCourseSectionIds(courseSectionsIds);
		return courseDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) {
		CourseDto courseDto = (CourseDto) dto;
		setName(courseDto.getName());
		setCourseSections(Collections.<CourseSectionCore>emptyList()); // FIXME
	}
}
