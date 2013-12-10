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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		InMemoryTestAnswer that = (InMemoryTestAnswer) o;

		if (!answer.equals(that.answer)) {
			return false;
		}
		if (!name.equals(that.name)) {
			return false;
		}
		if (!rightId.equals(that.rightId)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = answer.hashCode();
		result = 31 * result + rightId.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}
}
