package org.schooldesk.dto;

import java.util.Set;

public interface ITest extends IResource {
	Set<Long> getTestQuestionIds();
	void setTestQuestionIds(Set<Long> testQuestionIds);
}
