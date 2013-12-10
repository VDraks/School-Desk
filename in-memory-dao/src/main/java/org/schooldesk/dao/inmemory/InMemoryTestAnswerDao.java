package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ITestAnswerDao;
import org.schooldesk.dto.ITestAnswer;
import org.schooldesk.dto.inmemory.InMemoryTestAnswer;


public class InMemoryTestAnswerDao extends InMemoryAbstractDao<ITestAnswer> implements ITestAnswerDao {
	@Override
	public ITestAnswer createDto() {
		return new InMemoryTestAnswer();
	}
}
