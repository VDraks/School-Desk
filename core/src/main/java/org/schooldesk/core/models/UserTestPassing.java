package org.schooldesk.core.models;

import java.util.Set;


public class UserTestPassing {
	private long testId;
	private long userId;
	private Set<UserAnswer> userAnswers;


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
