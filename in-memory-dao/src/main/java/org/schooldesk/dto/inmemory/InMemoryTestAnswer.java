package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.ITestAnswer;


public class InMemoryTestAnswer extends InMemoryAbstractDto implements ITestAnswer {
	private String answer;
	private Long rightId;
	private String name;

	@Deprecated
	public InMemoryTestAnswer() {}

	@Override
	public String getAnswer() {
		return answer;
	}

	@Override
	public void setAnswer(String answer) {
		this.answer = answer;
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
