package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.IUserTestAnswerDao;
import org.schooldesk.dto.IUserTestAnswer;


public class InMemoryUserTestAnswerDao extends InMemoryAbstractDao<IUserTestAnswer> implements IUserTestAnswerDao {
	@Override
	public IUserTestAnswer createDto() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
