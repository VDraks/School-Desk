package org.schooldesk.dto.impl;

import java.util.*;

import org.schooldesk.dto.*;

public class TestQuestionDto extends ResourceDto implements ITestQuestionDto {
	private String question;
	private Set<Long> answerIds;
	private Long correctAnswerId;

	@Deprecated @UsedForMapping
	public TestQuestionDto() {}

	public static TestQuestionDto createNew() {
		TestQuestionDto dto = new TestQuestionDto();
		dto.setAnswerIds(new HashSet<Long>());
		return dto;
	}

	@Override
	public String getQuestion() {
		return question;
	}

	@Override
	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public Set<Long> getAnswerIds()
	{
		return answerIds;
	}

	@Override
	public void setAnswerIds(Set<Long> answerIds)
	{
		this.answerIds = answerIds;
	}

	@Override
	public Long getCorrectAnswerId()
	{
		return correctAnswerId;
	}

	@Override
	public void setCorrectAnswerId(Long id)
	{
		this.correctAnswerId = id;
	}
}
