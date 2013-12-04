package org.schooldesk.core.models;

import java.util.Set;


public class TestCreationModel {
	private String name;
	private Set<TestQuestionCreationModel> questions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TestQuestionCreationModel> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<TestQuestionCreationModel> questions) {
		this.questions = questions;
	}
}
