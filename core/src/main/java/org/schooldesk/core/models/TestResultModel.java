package org.schooldesk.core.models;

import java.util.*;


public class TestResultModel {
	private String testName;
	private List<ValidatedTestAnswer> validatedTestAnswers;

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public List<ValidatedTestAnswer> getValidatedTestAnswers() {
		return validatedTestAnswers;
	}

	public void setValidatedTestAnswers(List<ValidatedTestAnswer> validatedTestAnswers) {
		this.validatedTestAnswers = validatedTestAnswers;
	}


	public static class ValidatedTestAnswer {
		private TestQuestionModel questionModel;
		private Set<Long> userAnswerIds;
		private Set<Long> correctAnswerIds;

		public TestQuestionModel getQuestionModel() {
			return questionModel;
		}

		public void setQuestionModel(TestQuestionModel questionModel) {
			this.questionModel = questionModel;
		}

		public Set<Long> getUserAnswerIds() {
			return userAnswerIds;
		}

		public void setUserAnswerIds(Set<Long> userAnswerIds) {
			this.userAnswerIds = userAnswerIds;
		}

		public Set<Long> getCorrectAnswerIds() {
			return correctAnswerIds;
		}

		public void setCorrectAnswerIds(Set<Long> correctAnswerIds) {
			this.correctAnswerIds = correctAnswerIds;
		}
	}
}
