package org.schooldesk.core.models;

public class CourseSectionCreationModel {
	private String name;
	private Long courseId;

	public CourseSectionCreationModel() {}

	public CourseSectionCreationModel(String name, Long courseId) {
		this.name = name;
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
}
