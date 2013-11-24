package org.schooldesk.dto.impl;

import org.schooldesk.dto.ICourseSection;

public class CourseSectionDto extends AbstractDto implements ICourseSection {
	private String name;

	@Deprecated @UsedForMapping
	public CourseSectionDto() {}

	public static CourseSectionDto createNew() {
		return new CourseSectionDto();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
