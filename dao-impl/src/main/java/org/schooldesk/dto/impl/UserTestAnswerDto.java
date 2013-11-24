package org.schooldesk.dto.impl;

import org.schooldesk.dto.IUserTestAnswer;

public class UserTestAnswerDto extends AbstractDto implements IUserTestAnswer {
	private Long questionId;
	private Long answerId;

	@Deprecated @UsedForMapping
	public UserTestAnswerDto() {}

	public static UserTestAnswerDto createNew(Long questionId, Long answerId) {
		UserTestAnswerDto result = new UserTestAnswerDto();
		result.setQuestionId(questionId);
		result.setAnswerId(answerId);
		return result;
	}

	@Override
	public Long getQuestionId() {
		return questionId;
	}

	@UsedForMapping
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	@Override
	public Long getAnswerId() {
		return answerId;
	}

	@UsedForMapping
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}
}
