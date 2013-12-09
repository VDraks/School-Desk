package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.IUserTestAnswer;

import java.util.Set;


public class InMemoryUserTestAnswer extends InMemoryAbstractDto implements IUserTestAnswer {
	@Override
	public Long getQuestionId() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public void setQuestionId(Long questionId) {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Long> getAnswerIds() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public void setAnswerIds(Set<Long> answerIds) {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
