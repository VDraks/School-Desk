package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;

import java.util.*;


public class TestQuestionDto extends ResourceDto implements ITestQuestion {
	private String question;
	private TestQuestionType type;
	private Set<ITestAnswer> answers;
	private Set<ITestAnswer> correctAnswers;

	@Deprecated
	@UsedForMapping
	public TestQuestionDto() {}

	public static TestQuestionDto createNew() {
		TestQuestionDto dto = new TestQuestionDto();
		dto.setAnswers(new HashSet<ITestAnswer>());
		dto.setCorrectAnswers(new HashSet<ITestAnswer>());
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
	public Set<ITestAnswer> getAnswers() {
		return answers;
	}

	@Override
	public void setAnswers(Set<ITestAnswer> answers) {
		this.answers = answers;
	}

	@Override
	public Set<ITestAnswer> getCorrectAnswers() {
		return correctAnswers;
	}

	@Override
	public void setCorrectAnswers(Set<ITestAnswer> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
}
