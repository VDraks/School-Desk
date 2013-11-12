package org.schooldesk.dto.impl;

import java.util.*;

import org.schooldesk.dto.*;

public class CourseDto extends AbstractDto implements ICourseDto {
	private String name;
	private List<ICourseSectionDto> courseSections;

	@Deprecated @UsedForMapping
	public CourseDto() {}

	public static CourseDto createNew() {
		CourseDto result = new CourseDto();
		result.setCourseSections(new ArrayList<ICourseSectionDto>());
		return result;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<ICourseSectionDto> getCourseSections() {
		return courseSections;
	}

	@Override
	public void setCourseSections(List<ICourseSectionDto> courseSections) {
		this.courseSections = courseSections;
	}
}
