package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ITestQuestionDao;
import org.schooldesk.dto.ITestQuestion;


public class InMemoryTestQuestionDao extends InMemoryAbstractDao<ITestQuestion> implements ITestQuestionDao {
	@Override
	public ITestQuestion createDto() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
