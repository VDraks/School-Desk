package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "test_questions")
public class TestQuestionCore extends ResourceCore {
	private String question;
	private TestQuestionType type;
	private Set<TestAnswerCore> answers;
	private Set<TestAnswerCore> correctAnswers;

	@UsedForMapping
	private TestCore test;

	public TestQuestionCore() {}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Enumerated(EnumType.STRING)
	public TestQuestionType getType() {
		return type;
	}

	public void setType(TestQuestionType type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "testQuestion", cascade = {CascadeType.REMOVE})
	public Set<TestAnswerCore> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<TestAnswerCore> answers) {
		this.answers = answers;
	}

	@OneToMany(mappedBy = "testQuestion", cascade = {CascadeType.REMOVE})
	public Set<TestAnswerCore> getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(Set<TestAnswerCore> correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	@ManyToOne
	@UsedForMapping
	@SuppressWarnings("unused")
	public TestCore getTest() {
		return test;
	}

	@UsedForMapping
	@SuppressWarnings("unused")
	public void setTest(TestCore test) {
		this.test = test;
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
