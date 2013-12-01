package org.schooldesk.dao;

import org.schooldesk.dto.IUserTestAnswer;

public interface IUserTestAnswerDao extends IDao<IUserTestAnswer> {
	IUserTestAnswer createDto();
}
