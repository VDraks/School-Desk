package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.ITestQuestion;
import org.schooldesk.dto.TestQuestionType;

import java.util.HashSet;
import java.util.Set;


public class InMemoryTestQuestion extends InMemoryAbstractDto implements ITestQuestion {
	private String question;
	private TestQuestionType type;
	private Set<Long> answersIds;
	private Set<Long> correctAnswersIds;
	private Long rightId;
	private String name;

	@Deprecated
	public InMemoryTestQuestion() {}

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

	@Override
	public Long getRightId() {
		return rightId;
	}

	@Override
	public void setRightId(Long rightId) {
		this.rightId = rightId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
