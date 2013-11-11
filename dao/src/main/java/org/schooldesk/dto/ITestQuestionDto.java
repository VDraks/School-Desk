package org.schooldesk.dto;

import java.util.List;

public interface ITestQuestionDto extends IResourceDto {
	public String getQuestion();

	public void setQuestion(String question);

	public List<String> getAnswers();

	public void setAnswers(List<String> answers);

	public int getRightAnswerIndex();

	public void setRightAnswerIndex(int index);
}
