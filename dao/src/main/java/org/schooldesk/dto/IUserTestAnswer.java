package org.schooldesk.dto;

import java.util.Set;


public interface IUserTestAnswer extends IDto {
	Long getQuestionId();
	void setQuestionId(Long questionId);

	Set<Long> getAnswerIds();
	void setAnswerIds(Set<Long> answerIds);
}
