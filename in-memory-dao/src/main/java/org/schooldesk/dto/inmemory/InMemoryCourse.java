package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.ICourse;

import java.util.List;


public class InMemoryCourse extends InMemoryAbstractDto implements ICourse {
	private String name;
	private List<Long> courseSectionIds;

	public InMemoryCourse() {}

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
