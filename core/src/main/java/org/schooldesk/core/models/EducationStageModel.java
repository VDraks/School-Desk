package org.schooldesk.core.models;

import java.util.*;

public class EducationStageModel {
	private long id;
	private String name;
	private Set<CourseModel> courseModels;

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

	public Set<CourseModel> getCourseModels() {
		return courseModels;
	}

	public void setCourseModels(Set<CourseModel> courseModels) {
		this.courseModels = courseModels;
	}
}
