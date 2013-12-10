package org.schooldesk.core.models;

public class CourseCreationModel {
	private String name;
	private Long educationStageId;

	public CourseCreationModel() {}

	public CourseCreationModel(String name, Long educationStageId) {
		this.name = name;
		this.educationStageId = educationStageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getEducationStageId() {
		return educationStageId;
	}

	public void setEducationStageId(Long educationStageId) {
		this.educationStageId = educationStageId;
	}
}
