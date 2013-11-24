package org.schooldesk.dto;

public interface IResource extends IDto {
	public Long getRightId();

	public void setRight(Long rightId);

	public String getName();

	public void setName(String name);
}
