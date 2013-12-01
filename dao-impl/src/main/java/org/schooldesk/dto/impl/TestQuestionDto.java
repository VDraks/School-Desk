package org.schooldesk.dto.impl;

import org.schooldesk.dto.ITestAnswer;
import org.schooldesk.dto.ITestQuestion;

import java.util.HashSet;
import java.util.Set;


public class TestQuestionDto extends ResourceDto implements ITestQuestion {
	private String question;
	private Set<Long> answerIds;
	private Long correctAnswerId;

	@Deprecated
	@UsedForMapping
	public TestQuestionDto() {}

	public static TestQuestionDto createNew() {
		TestQuestionDto dto = new TestQuestionDto();
		dto.setAnswers(new HashSet<ITestAnswer>());
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
	public Set<ITestAnswer> getAnswers() {
		return answerIds;
	}

	@Override
	public void setAnswers(Set<ITestAnswer> answers) {
		this.answerIds = answerIds;
	}

	@Override
	public Set<ITestAnswer> getCorrectAnswers() {
		return correctAnswerId;
	}

	@Override
	public void setCorrectAnswers(Set<ITestAnswer> answers) {
		this.correctAnswerId = id;
	}
}
