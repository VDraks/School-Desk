package org.schooldesk.dao;

import org.schooldesk.dto.ITest;

public interface ITestDao extends IDao<ITest> {
	ITest createDto();
}
