package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.IEducationStage;

import java.util.ArrayList;
import java.util.List;


public class InMemoryEducationStage extends InMemoryAbstractDto implements IEducationStage {
	private String name;
	private List<Long> courseIds;

	@Deprecated
	public InMemoryEducationStage() {}

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
