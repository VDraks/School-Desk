package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;


public class CourseSectionDto extends AbstractDto implements ICourseSection {
	private String name;
	private Long testId;

	@Deprecated
	@UsedForMapping
	public CourseSectionDto() {}

	@SuppressWarnings("deprecated")
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

	@Override
	public Long getTestId() {
		return testId;
	}

	@Override
	public void setTestId(Long testId) {
		this.testId = testId;
	}
}
