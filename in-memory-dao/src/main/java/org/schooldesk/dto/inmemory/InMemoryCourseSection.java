package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.ICourseSection;


public class InMemoryCourseSection extends InMemoryAbstractDto implements ICourseSection {
	private String name;
	private Long testId;

	@Deprecated
	public InMemoryCourseSection() {}

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
