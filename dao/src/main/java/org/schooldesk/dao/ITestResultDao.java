package org.schooldesk.dao;

import org.schooldesk.dto.ITestResult;

public interface ITestResultDao extends IDao<ITestResult> {
	ITestResult createNew();
}
