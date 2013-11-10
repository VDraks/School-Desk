package org.schooldesk.dto;

import java.util.Set;


public interface IGroupDto extends IDto {
	public String getName();

	public void setName(String name);

	public Set<Long> getRightsIds();

	public void setRightsIds(Set<Long> rightsIds);
}
