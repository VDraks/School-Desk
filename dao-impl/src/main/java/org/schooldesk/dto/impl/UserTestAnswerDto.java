package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;

import java.util.*;


public class UserTestAnswerDto extends AbstractDto implements IUserTestAnswer {
	private Long questionId;
	private Set<Long> answerIds;

	@Deprecated
	@UsedForMapping
	public UserTestAnswerDto() {}

	public static UserTestAnswerDto createNew() {
		UserTestAnswerDto result = new UserTestAnswerDto();
		result.setAnswerIds(new HashSet<Long>());
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

	@Override
	public Set<Long> getAnswerIds() {
		return answerIds;
	}

	@Override
	public void setAnswerIds(Set<Long> answerIds) {
		this.answerIds = answerIds;
	}
}
