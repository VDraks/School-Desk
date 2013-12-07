package org.schooldesk.dto;

public interface ICourseSection extends IDto {
	String getName();
	void setName(String name);

	Long getTestId();
	void setTestId(Long testId);
}
