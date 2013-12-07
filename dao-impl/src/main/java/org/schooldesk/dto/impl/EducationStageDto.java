package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;

import java.util.*;


public class EducationStageDto extends AbstractDto implements IEducationStage {
	private String name;
	private List<Long> courseIds;

	@Deprecated
	@UsedForMapping
	public EducationStageDto() {}

	public static EducationStageDto createNew() {
		EducationStageDto result = new EducationStageDto();
		result.setCourseIds(new ArrayList<Long>());
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
	public List<Long> getCourseIds() {
		return courseIds;
	}

	@Override
	public void setCourseIds(List<Long> courseIds) {
		this.courseIds = courseIds;
	}
}
