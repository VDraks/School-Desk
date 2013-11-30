package org.schooldesk.dto;

import java.util.Set;


public interface IGroup extends IDto {
	public String getName();

	public void setName(String name);

	public Set<Long> getRightIds();

	public void setRightIds(Set<Long> rightsIds);
}
