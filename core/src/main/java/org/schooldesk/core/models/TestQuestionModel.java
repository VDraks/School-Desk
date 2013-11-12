package org.schooldesk.core.models;

import java.util.Set;


public class TestQuestionModel {
	private long id;
	private String question;
	private Set<TestAnswerModel> answers;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Set<TestAnswerModel> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<TestAnswerModel> answers) {
		this.answers = answers;
	}


}
