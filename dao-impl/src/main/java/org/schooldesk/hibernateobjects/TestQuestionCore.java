package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
public class TestQuestionCore extends ResourceCore {

	private String question;

	@Enumerated(EnumType.STRING)
	private TestQuestionType type;

	private Set<TestAnswerCore> answers;

	private Set<TestAnswerCore> correctAnswers;

	@UsedForMapping
	private List<TestCore> testCores;

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

	@OneToMany(mappedBy = "testQuestion1", cascade = {CascadeType.REMOVE})
	public Set<TestAnswerCore> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<TestAnswerCore> answers) {
		this.answers = answers;
	}

	@OneToMany(mappedBy = "testQuestion2", cascade = {CascadeType.REMOVE})
	public Set<TestAnswerCore> getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(Set<TestAnswerCore> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	@ManyToMany
	@UsedForMapping
	@SuppressWarnings("unused")
	public List<TestCore> getTestCores() {
		return testCores;
	}

	@UsedForMapping
	@SuppressWarnings("unused")
	public void setTestCores(List<TestCore> testCores) {
		this.testCores = testCores;
	}

	@Override
	@SuppressWarnings("deprecation")
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
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		ITestQuestion testQuestion = (ITestQuestion) dto;
		setQuestion(testQuestion.getQuestion());
		setType(testQuestion.getType());
		setAnswers(new HashSet<>(coreApi.loadByIds(TestAnswerCore.class, testQuestion.getAnswerIds())));
		setCorrectAnswers(new HashSet<>(coreApi.loadByIds(TestAnswerCore.class, testQuestion.getCorrectAnswerIds())));

		super.fromDto(dto, coreApi);
	}
}
