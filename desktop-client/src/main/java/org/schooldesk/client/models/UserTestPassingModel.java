package org.schooldesk.client.models;

import java.util.Set;


public class UserTestPassingModel {
	private Long testId;
	private Long userId;
	private Set<UserAnswerModel> userAnswers;

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

	public Set<UserAnswerModel> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(Set<UserAnswerModel> userAnswers) {
		this.userAnswers = userAnswers;
	}

	public static class UserAnswerModel {
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
