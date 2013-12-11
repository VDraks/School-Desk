package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;


@Entity
@Table(name = "test_answers")
public class TestAnswerCore extends ResourceCore {
	private String answer;

	@UsedForMapping
	private TestQuestionCore testQuestion;
	@UsedForMapping
	private UserTestAnswerCore userTestAnswer;

	public TestAnswerCore() {}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@ManyToOne
	@UsedForMapping
	@SuppressWarnings("unused")
	public TestQuestionCore getTestQuestion() {
		return testQuestion;
	}

	@UsedForMapping
	@SuppressWarnings("unused")
	public void setTestQuestion(TestQuestionCore testQuestion) {
		this.testQuestion = testQuestion;
	}

	@ManyToOne
	@UsedForMapping
	@SuppressWarnings("unused")
	public UserTestAnswerCore getUserTestAnswer() {
		return userTestAnswer;
	}

	@UsedForMapping
	@SuppressWarnings("unused")
	public void setUserTestAnswer(UserTestAnswerCore userTestAnswer) {
		this.userTestAnswer = userTestAnswer;
	}

	@Override
	@SuppressWarnings("deprecation")
	public TestAnswerDto toDto() {
		return mapDto(new TestAnswerDto());
	}

	@Override
	protected TestAnswerDto mapDto(AbstractDto dto) {
		TestAnswerDto testAnswerDto = (TestAnswerDto) super.mapDto(dto);
		testAnswerDto.setAnswer(getAnswer());
		return testAnswerDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		ITestAnswer testAnswer = (TestAnswerDto) dto;
		setAnswer(testAnswer.getAnswer());

		super.fromDto(dto, coreApi);
	}
}
