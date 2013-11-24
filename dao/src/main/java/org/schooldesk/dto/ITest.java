package org.schooldesk.dto;

import java.util.Set;

public interface ITest extends IResource {
	public Set<Long> getTestQuestionIds();

	public void setTestQuestionIds(Set<Long> testQuestionIds);
}
