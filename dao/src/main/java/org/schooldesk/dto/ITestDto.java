package org.schooldesk.dto;

import java.util.Set;

public interface ITestDto extends IResourceDto {
	public Set<Long> getTestQuestionIds();

	public void setTestQuestionIds(Set<Long> testQuestionIds);
}
