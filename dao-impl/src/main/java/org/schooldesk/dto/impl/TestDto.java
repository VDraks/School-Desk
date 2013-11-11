package org.schooldesk.dto.impl;

import java.util.*;

import org.schooldesk.dto.*;

public class TestDto extends ResourceDto implements ITestDto {
	private Set<Long> testQuestionIds;

	@Deprecated @UsedForMapping
	public TestDto() {}

	public static TestDto createNew() {
		TestDto dto = new TestDto();
		dto.setTestQuestionIds(new HashSet<Long>());
		return dto;
	}

	@Override
	public Set<Long> getTestQuestionIds() {
		return testQuestionIds;
	}

	@Override
	public void setTestQuestionIds(Set<Long> testQuestionIds) {
		this.testQuestionIds = testQuestionIds;
	}
}
