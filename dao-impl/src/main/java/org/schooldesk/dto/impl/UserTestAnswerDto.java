package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;


public class UserTestAnswerDto extends AbstractDto implements IUserTestAnswer {
	private Long questionId;

	@Deprecated
	@UsedForMapping
	public UserTestAnswerDto() {}

	public static UserTestAnswerDto createNew(Long questionId) {
		UserTestAnswerDto result = new UserTestAnswerDto();
		result.setQuestionId(questionId);
		return result;
	}

	@Override
	public Long getQuestionId() {
		return questionId;
	}

	@Override
	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
}
