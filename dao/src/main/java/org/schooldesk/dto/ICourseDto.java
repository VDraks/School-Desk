package org.schooldesk.dto;

import java.util.List;

public interface ICourseDto extends IDto {
	public String getName();
	public void setName(String name);
	public List<ICourseSectionDto> getCourseSections();
	public void setCourseSections(List<ICourseSectionDto> courseSections);
}
