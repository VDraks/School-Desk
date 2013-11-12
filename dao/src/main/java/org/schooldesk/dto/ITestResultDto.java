package org.schooldesk.dto;

import java.util.Set;

public interface ITestResultDto extends IDto {
	public Long getTestId();

	public Long getUserId();

	public Set<Long> getUserTestAnswerIds();
}
