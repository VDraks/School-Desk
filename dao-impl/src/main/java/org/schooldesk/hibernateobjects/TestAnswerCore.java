package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;


@Entity
public class TestAnswerCore extends ResourceCore {

	private TestQuestionCore testQuestion1;

	private TestQuestionCore testQuestion2;

	@ManyToOne
	public TestQuestionCore getTestQuestion1() {
		return testQuestion1;
	}

	public void setTestQuestion1(TestQuestionCore testQuestion1) {
		this.testQuestion1 = testQuestion1;
	}

	@ManyToOne
	public TestQuestionCore getTestQuestion2() {
		return testQuestion2;
	}

	public void setTestQuestion2(TestQuestionCore testQuestion2) {
		this.testQuestion2 = testQuestion2;
	}

	private String answer;

	public TestAnswerCore() {}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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
