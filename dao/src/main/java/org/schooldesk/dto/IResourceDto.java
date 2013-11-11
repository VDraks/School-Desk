package org.schooldesk.dto;

public interface IResourceDto extends IDto {
	public IRightDto getRight();

	public void setRight(IRightDto right);

	public String getName();

	public void setName(String name);
}
