package org.schooldesk.dao;

import org.schooldesk.dto.ITestQuestion;

public interface ITestQuestionDao extends IDao<ITestQuestion> {
	ITestQuestion createDto();
}
