package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.IUserTestAnswer;

import java.util.HashSet;
import java.util.Set;


public class InMemoryUserTestAnswer extends InMemoryAbstractDto implements IUserTestAnswer {
	private Long questionId;
	private Set<Long> answerIds;

	@Deprecated
	public InMemoryUserTestAnswer() {}

	@Override
	public Long getQuestionId() {
		return questionId;
	}

	@Override
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Override
	public Set<Long> getAnswerIds() {
		return answerIds;
	}

	@Override
	public void setAnswerIds(Set<Long> answerIds) {
		this.answerIds = answerIds;
	}
}
