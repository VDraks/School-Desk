package org.schooldesk.dto;

import java.util.Set;


public interface ISelectedUserTestAnswer extends IUserTestAnswer {
	Set<Long> getAnswerIds();
	void setAnswerIds(Set<Long> answerIds);
}
