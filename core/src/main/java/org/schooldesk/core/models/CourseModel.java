package org.schooldesk.core.models;

import java.util.Set;


public class CourseModel {
	private long id;
	private String name;
	private Set<CourseSectionModel> sectionModels;

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

	public Set<CourseSectionModel> getSectionModels() {
		return sectionModels;
	}

	public void setSectionModels(Set<CourseSectionModel> sectionModels) {
		this.sectionModels = sectionModels;
	}
}
