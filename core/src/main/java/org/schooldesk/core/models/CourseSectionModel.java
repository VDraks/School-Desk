package org.schooldesk.core.models;

public class CourseSectionModel {
	private long id;
	private String name;
	private TestModel testModel;

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

	public TestModel getTestModel() {
		return testModel;
	}

	public void setTestModel(TestModel testModel) {
		this.testModel = testModel;
	}
}
