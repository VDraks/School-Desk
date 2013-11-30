package org.schooldesk.dto;

import java.util.List;

public interface ICourse extends IDto {
	public String getName();
	public void setName(String name);
	public List<ICourseSection> getCourseSections();
	public void setCourseSections(List<ICourseSection> courseSections);
}
