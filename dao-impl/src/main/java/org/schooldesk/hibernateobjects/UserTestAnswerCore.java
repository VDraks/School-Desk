package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "user_test_answers")
public class UserTestAnswerCore extends AbstractCore {
	private TestQuestionCore testQuestion;
	private Set<TestAnswerCore> answers;

	@UsedForMapping
	private TestResultCore testResult;

	public UserTestAnswerCore() {}

	@OneToOne(cascade = CascadeType.REMOVE)
	@PrimaryKeyJoinColumn
	public TestQuestionCore getTestQuestion() {
		return testQuestion;
	}

	public void setTestQuestion(TestQuestionCore testQuestion) {
		this.testQuestion = testQuestion;
	}

	@OneToMany(mappedBy = "userTestAnswer", cascade = CascadeType.REMOVE)
	public Set<TestAnswerCore> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<TestAnswerCore> answers) {
		this.answers = answers;
	}

	@ManyToOne
	@UsedForMapping
	@SuppressWarnings("unused")
	public TestResultCore getTestResult() {
		return testResult;
	}

	@UsedForMapping
	@SuppressWarnings("unused")
	public void setTestResult(TestResultCore testResult) {
		this.testResult = testResult;
	}

	@Override
	@SuppressWarnings("deprecation")
	public UserTestAnswerDto toDto() {
		return mapDto(new UserTestAnswerDto());
	}

	@Override
	public UserTestAnswerDto mapDto(AbstractDto dto) {
		UserTestAnswerDto userTestAnswerDto = (UserTestAnswerDto) super.mapDto(dto);
		userTestAnswerDto.setQuestionId(getTestQuestion().getId());
		userTestAnswerDto.setAnswerIds(getIds(getAnswers()));
		return userTestAnswerDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		IUserTestAnswer userTestAnswer = (IUserTestAnswer) dto;
		setTestQuestion(coreApi.loadById(TestQuestionCore.class, userTestAnswer.getQuestionId()));
		setAnswers(new HashSet<>(coreApi.loadByIds(TestAnswerCore.class, userTestAnswer.getAnswerIds())));
	}
}
