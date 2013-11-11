package org.schooldesk.dto.impl;

import java.util.*;

import org.schooldesk.dto.*;

public class TestQuestionDto extends ResourceDto implements ITestQuestionDto {
	private String question;
	private List<String> answers;
	private int rightAnswerIndex;

	/**
	 * @deprecated exists for mapping purposes
	 */
	public TestQuestionDto() {}

	public static TestQuestionDto createNew() {
		TestQuestionDto dto = new TestQuestionDto();
		dto.setAnswers(new ArrayList<String>());
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
	public List<String> getAnswers() {
		return answers;
	}

	@Override
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	@Override
	public int getRightAnswerIndex() {
		return rightAnswerIndex;
	}

	@Override
	public void setRightAnswerIndex(int index) {
		this.rightAnswerIndex = index;
	}
}
