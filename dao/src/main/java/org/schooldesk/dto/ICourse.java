package org.schooldesk.dto;

import java.util.List;

public interface ICourse extends IDto {
	String getName();
	void setName(String name);

	List<Long> getCourseSectionIds();
	void setCourseSectionIds(List<Long> courseSectionIds);
}
