package org.schooldesk.dao;

import org.schooldesk.dto.ITestAnswer;

public interface ITestAnswerDao extends IDao<ITestAnswer> {
	ITestAnswer createDto();
}
