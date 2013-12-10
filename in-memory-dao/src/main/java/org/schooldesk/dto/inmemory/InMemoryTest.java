package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.ITest;

import java.util.HashSet;
import java.util.Set;


public class InMemoryTest extends InMemoryAbstractDto implements ITest {
	private Set<Long> testQuestionIds;
	private Long rightId;
	private String name;

	@Deprecated
	public InMemoryTest() {}

	@Override
	public Set<Long> getTestQuestionIds() {
		return testQuestionIds;
	}

	@Override
	public void setTestQuestionIds(Set<Long> testQuestionIds) {
		this.testQuestionIds = testQuestionIds;
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
