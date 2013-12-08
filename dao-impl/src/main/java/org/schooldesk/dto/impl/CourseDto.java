package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;

import java.util.*;


public class CourseDto extends AbstractDto implements ICourse {
	private String name;
	private List<Long> courseSectionIds;

	@Deprecated
	@UsedForMapping
	public CourseDto() {}

	public static CourseDto createNew() {
		@SuppressWarnings("deprecated")
		CourseDto result = new CourseDto();
		result.setCourseSectionIds(new ArrayList<Long>());
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
	public List<Long> getCourseSectionIds() {
		return courseSectionIds;
	}

	@Override
	public void setCourseSectionIds(List<Long> courseSectionIds) {
		this.courseSectionIds = courseSectionIds;
	}
}
