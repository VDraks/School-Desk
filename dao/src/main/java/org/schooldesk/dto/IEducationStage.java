package org.schooldesk.dto;

import java.util.List;

public interface IEducationStage extends IDto {
	String getName();
	void setName(String name);

	List<Long> getCourseIds();
	void setCourseIds(List<Long> courseIds);
}
