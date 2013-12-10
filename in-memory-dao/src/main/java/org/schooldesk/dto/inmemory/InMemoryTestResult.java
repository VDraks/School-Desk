package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.ITestResult;

import java.util.Set;


public class InMemoryTestResult extends InMemoryAbstractDto implements ITestResult {
	private Long testId;
	private Long userId;
	private Set<Long> userTestAnswerIds;

	@Deprecated
	public InMemoryTestResult() {}

	@Override
	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	@Override
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public Set<Long> getUserTestAnswerIds() {
		return userTestAnswerIds;
	}

	public void setUserTestAnswerIds(Set<Long> userTestAnswerIds) {
		this.userTestAnswerIds = userTestAnswerIds;
	}
}
