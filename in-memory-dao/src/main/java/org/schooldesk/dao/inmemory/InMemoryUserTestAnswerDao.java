package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.IUserTestAnswerDao;
import org.schooldesk.dto.IUserTestAnswer;
import org.schooldesk.dto.inmemory.InMemoryUserTestAnswer;


public class InMemoryUserTestAnswerDao extends InMemoryAbstractDao<IUserTestAnswer> implements IUserTestAnswerDao {
	@Override
	public IUserTestAnswer createDto() {
		return new InMemoryUserTestAnswer();
	}
}
