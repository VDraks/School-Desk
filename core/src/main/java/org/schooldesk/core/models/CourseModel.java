package org.schooldesk.core.models;

import java.util.List;


public class CourseModel {
	private long id;
	private String name;
	private List<CourseSectionModel> sectionModels;

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

	public List<CourseSectionModel> getSectionModels() {
		return sectionModels;
	}

	public void setSectionModels(List<CourseSectionModel> sectionModels) {
		this.sectionModels = sectionModels;
	}
}
