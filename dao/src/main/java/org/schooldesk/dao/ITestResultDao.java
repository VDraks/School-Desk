package org.schooldesk.dao;

import org.schooldesk.dto.*;

import java.util.*;


public interface ITestResultDao extends IDao<ITestResult> {
	ITestResult createDto(Long testId, Long userId, Set<Long> userTestAnswerIds);
}
