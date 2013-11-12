package org.schooldesk.core.models;

import java.util.List;


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
		private String question;
		private String userAnswer;
		private String correctAnswer;

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getUserAnswer() {
			return userAnswer;
		}

		public void setUserAnswer(String userAnswer) {
			this.userAnswer = userAnswer;
		}

		public String getCorrectAnswer() {
			return correctAnswer;
		}

		public void setCorrectAnswer(String correctAnswer) {
			this.correctAnswer = correctAnswer;
		}
	}
}
