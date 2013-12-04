package org.schooldesk.core.models;

import java.util.List;
import java.util.Set;


public class TestResultModel {
	private String testName;
	private List<ValidatedTestAnswerModel> validatedTestAnswers;

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public List<ValidatedTestAnswerModel> getValidatedTestAnswers() {
		return validatedTestAnswers;
	}

	public void setValidatedTestAnswers(List<ValidatedTestAnswerModel> validatedTestAnswers) {
		this.validatedTestAnswers = validatedTestAnswers;
	}


	public static class ValidatedTestAnswerModel {
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
