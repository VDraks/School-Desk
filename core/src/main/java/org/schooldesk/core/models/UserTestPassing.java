package org.schooldesk.core.models;

import java.util.Set;


public class UserTestPassing {
	private long testId;
	private long userId;
	private Set<UserAnswer> userAnswers;

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Set<UserAnswer> getUserAnswers() {
		return userAnswers;
	}

	public void setUserAnswers(Set<UserAnswer> userAnswers) {
		this.userAnswers = userAnswers;
	}

	public static class UserAnswer {
		private long questionId;
		private long answerId;

		public long getQuestionId() {
			return questionId;
		}

		public void setQuestionId(long questionId) {
			this.questionId = questionId;
		}

		public long getAnswerId() {
			return answerId;
		}

		public void setAnswerId(long answerId) {
			this.answerId = answerId;
		}
	}
}
