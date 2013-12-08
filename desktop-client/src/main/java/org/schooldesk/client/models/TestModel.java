package org.schooldesk.client.models;

import java.util.Set;


public class TestModel {
	private Long id;
	private String name;
	private Set<TestQuestionModel> questions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
