package org.schooldesk.core.models;

import java.util.Set;


public class UserTestPassing {
	private Long testId;
	private Long userId;
	private Set<UserAnswer> userAnswers;

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<UserAnswer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(Set<UserAnswer> userAnswers) {
		this.userAnswers = userAnswers;
	}

	public static class UserAnswer {
		private Long questionId;
		private Set<Long> answerIds;

		public Long getQuestionId() {
			return questionId;
		}

		public void setQuestionId(Long questionId) {
			this.questionId = questionId;
		}

		public Set<Long> getAnswerIds() {
			return answerIds;
		}

		public void setAnswerIds(Set<Long> answerIds) {
			this.answerIds = answerIds;
		}
	}
}
