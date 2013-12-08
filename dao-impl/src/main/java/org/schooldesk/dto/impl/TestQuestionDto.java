package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;

import java.util.*;


public class TestQuestionDto extends ResourceDto implements ITestQuestion {
	private String question;
	private TestQuestionType type;
	private Set<Long> answersIds;
	private Set<Long> correctAnswersIds;

	@Deprecated
	@UsedForMapping
	public TestQuestionDto() {}

	public static TestQuestionDto createNew() {
		@SuppressWarnings("deprecated")
		TestQuestionDto dto = new TestQuestionDto();
		dto.setAnswerIds(new HashSet<Long>());
		dto.setCorrectAnswerIds(new HashSet<Long>());
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
	public TestQuestionType getType() {
		return type;
	}

	@Override
	public void setType(TestQuestionType type) {
		this.type = type;
	}

	@Override
	public Set<Long> getAnswerIds() {
		return answersIds;
	}

	@Override
	public void setAnswerIds(Set<Long> answerIds) {
		this.answersIds = answerIds;
	}

	@Override
	public Set<Long> getCorrectAnswerIds() {
		return correctAnswersIds;
	}

	@Override
	public void setCorrectAnswerIds(Set<Long> correctAnswers) {
		this.correctAnswersIds = correctAnswers;
	}
}
