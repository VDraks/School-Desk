package org.schooldesk.dto;

import java.util.Set;

public interface ITestQuestion extends IResource {
	public String getQuestion();

	public void setQuestion(String question);

	public Set<Long> getAnswerIds();

	public void setAnswerIds(Set<Long> answerIds);

	public Long getCorrectAnswerId();

	public void setCorrectAnswerId(Long id);
}
