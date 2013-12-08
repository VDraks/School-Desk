package org.schooldesk.client.models;

import java.util.Set;


public class TestQuestionCreationModel {
	private String question;
	private TestQuestionType testQuestionType;
	private Set<TestAnswerCreationModel> answers;
	private Set<TestAnswerCreationModel> correctAnswers;

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public TestQuestionType getTestQuestionType() {
		return testQuestionType;
	}

	public void setTestQuestionType(TestQuestionType testQuestionType) {
		this.testQuestionType = testQuestionType;
	}

	public Set<TestAnswerCreationModel> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<TestAnswerCreationModel> answers) {
		this.answers = answers;
	}

	public Set<TestAnswerCreationModel> getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(Set<TestAnswerCreationModel> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
}
