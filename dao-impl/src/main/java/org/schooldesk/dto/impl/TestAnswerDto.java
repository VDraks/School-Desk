package org.schooldesk.dto.impl;

import org.schooldesk.dto.ITestAnswer;


public class TestAnswerDto extends ResourceDto implements ITestAnswer {
	private String answer;

	@Deprecated
	@UsedForMapping
	public TestAnswerDto() {}

	public static TestAnswerDto createNew() {
		return new TestAnswerDto();
	}

	@Override
	public String getAnswer() {
		return answer;
	}

	@Override
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
