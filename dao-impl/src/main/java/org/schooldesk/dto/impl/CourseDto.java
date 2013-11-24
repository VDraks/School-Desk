package org.schooldesk.dto.impl;

import org.schooldesk.dto.ICourse;
import org.schooldesk.dto.ICourseSection;

import java.util.ArrayList;
import java.util.List;

public class CourseDto extends AbstractDto implements ICourse {
	private String name;
	private List<ICourseSection> courseSections;

	@Deprecated @UsedForMapping
	public CourseDto() {}

	public static CourseDto createNew() {
		CourseDto result = new CourseDto();
		result.setCourseSections(new ArrayList<ICourseSection>());
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
	public List<ICourseSection> getCourseSections() {
		return courseSections;
	}

	@Override
	public void setCourseSections(List<ICourseSection> courseSections) {
		this.courseSections = courseSections;
	}
}
