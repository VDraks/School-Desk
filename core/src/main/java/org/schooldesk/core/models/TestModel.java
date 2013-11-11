package org.schooldesk.core.models;

import java.util.Set;


public class TestModel {
	private long id;
	private String name;
	private Set<TestQuestionModel> questions;

	public TestModel(long id, String name, Set<TestQuestionModel> questions) {
		this.id = id;
		this.name = name;
		this.questions = questions;
	}
}
