package org.schooldesk.dto.impl;

import org.schooldesk.dto.ITestAnswer;
import org.schooldesk.dto.ITestQuestion;
import org.schooldesk.dto.TestQuestionType;

import java.util.HashSet;
import java.util.Set;


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
		dto.setAnswerIds(new HashSet<ITestAnswer>());
		dto.setCorrectAnswerIds(new HashSet<ITestAnswer>());
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
	public Set<ITestAnswer> getAnswerIds() {
		return answers;
	}

	@Override
	public void setAnswerIds(Set<Long> answerIds) {
		this.answers = answerIds;
	}

	@Override
	public Set<Long> getCorrectAnswerIds() {
		return correctAnswers;
	}

	@Override
	public void setCorrectAnswerIds(Set<Long> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}
}
