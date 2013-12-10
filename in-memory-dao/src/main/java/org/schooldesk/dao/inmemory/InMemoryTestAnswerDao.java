package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ITestAnswerDao;
import org.schooldesk.dto.ITestAnswer;


public class InMemoryTestAnswerDao extends InMemoryAbstractDao<ITestAnswer> implements ITestAnswerDao {
	@Override
	public ITestAnswer createDto() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
