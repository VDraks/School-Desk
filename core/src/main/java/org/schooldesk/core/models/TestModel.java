package org.schooldesk.core.models;

import java.util.Set;


public class TestModel {
	private long id;
	private String name;
	private Set<TestQuestionModel> questions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TestQuestionModel> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<TestQuestionModel> questions) {
		this.questions = questions;
	}
}
