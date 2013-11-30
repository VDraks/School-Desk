package org.schooldesk.dto.impl;

import org.schooldesk.dto.ITest;

import java.util.HashSet;
import java.util.Set;


public class TestDto extends ResourceDto implements ITest {
	private Set<Long> testQuestionIds;

	@Deprecated
	@UsedForMapping
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
