package org.schooldesk.client.models;

import java.util.Set;


public class TestQuestionModel {
	private Long id;
	private String question;
	private TestQuestionType testQuestionType;
	private Set<TestAnswerModel> answers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Set<TestAnswerModel> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<TestAnswerModel> answers) {
		this.answers = answers;
	}
}
