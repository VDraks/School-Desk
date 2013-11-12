package org.schooldesk.dto.impl;

import java.util.*;

import org.schooldesk.dto.*;

public class TestResultDto extends AbstractDto implements ITestResultDto {
	private Long testId;
	private Long userId;
	private Set<Long> userTestAnswerIds;

	@Deprecated @UsedForMapping
	public TestResultDto() {}

	public static TestResultDto createNew(Long testId, Long userId, Set<Long> userTestAnswerIds) {
		TestResultDto result = new TestResultDto();
		result.setTestId(testId);
		result.setUserId(userId);
		result.setUserTestAnswerIds(userTestAnswerIds);
		return result;
	}

	@Override
	public Long getTestId() {
		return testId;
	}

	@UsedForMapping
	public void setTestId(Long testId) {
		this.testId = testId;
	}

	@Override
	public Long getUserId() {
		return userId;
	}

	@UsedForMapping
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public Set<Long> getUserTestAnswerIds() {
		return userTestAnswerIds;
	}

	@UsedForMapping
	public void setUserTestAnswerIds(Set<Long> userTestAnswerIds) {
		this.userTestAnswerIds = userTestAnswerIds;
	}
}
