package org.schooldesk.dto;

import java.util.List;

public interface ITestDto extends IResourceDto {
	public List<ITestQuestionDto> getTestQuestions();

	public void setTestQuestions(List<ITestQuestionDto> testQuestions);
}
