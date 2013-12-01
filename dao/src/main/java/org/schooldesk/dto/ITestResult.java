package org.schooldesk.dto;

import java.util.Set;


public interface ITestResult extends IDto {
	Long getTestId();
	Long getUserId();
	Set<Long> getUserTestAnswerIds();
}
