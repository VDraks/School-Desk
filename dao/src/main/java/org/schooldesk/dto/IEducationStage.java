package org.schooldesk.dto;

import java.util.*;

public interface IEducationStage extends IDto {
	public String getName();
	public void setName(String name);
	public List<Long> getCourseIds();
	public void setCourseIds(List<Long> courseIds);
}
