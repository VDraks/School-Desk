package org.schooldesk.core;

import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
public class TestQuestionCore extends ResourceCore {
	private String question;
	private TestQuestionType type;

	@OneToMany
	private Set<TestAnswerCore> answers;

	@OneToMany
	private Set<TestAnswerCore> correctAnswers;

	public TestQuestionCore() {}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public TestQuestionType getType() {
		return type;
	}

	public void setType(TestQuestionType type) {
		this.type = type;
	}

	public Set<TestAnswerCore> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<TestAnswerCore> answers) {
		this.answers = answers;
	}

	public Set<TestAnswerCore> getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(Set<TestAnswerCore> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	@Override
	public TestQuestionDto toDto() {
		return mapDto(new TestQuestionDto());
	}

	@Override
	protected TestQuestionDto mapDto(AbstractDto dto) {
		TestQuestionDto testQuestionDto = (TestQuestionDto) super.mapDto(dto);
		testQuestionDto.setQuestion(getQuestion());
		testQuestionDto.setType(getType());
		testQuestionDto.setAnswerIds(getIds(getAnswers()));
		testQuestionDto.setCorrectAnswerIds(getIds(getCorrectAnswers()));
		return testQuestionDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) {
		ITestQuestion testQuestion = (ITestQuestion) dto;
		setQuestion(testQuestion.getQuestion());
		setType(testQuestion.getType());
		setAnswers(new HashSet<>(coreApi.loadByIdsSafe(TestAnswerCore.class, testQuestion.getAnswerIds())));
		setCorrectAnswers(new HashSet<>(coreApi.loadByIdsSafe(TestAnswerCore.class, testQuestion.getCorrectAnswerIds())));

		super.fromDto(dto, coreApi);
	}
}
