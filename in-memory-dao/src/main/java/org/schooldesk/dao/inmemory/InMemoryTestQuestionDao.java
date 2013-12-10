package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ITestQuestionDao;
import org.schooldesk.dto.ITestQuestion;
import org.schooldesk.dto.inmemory.InMemoryTestQuestion;


public class InMemoryTestQuestionDao extends InMemoryAbstractDao<ITestQuestion> implements ITestQuestionDao {
	@Override
	public ITestQuestion createDto() {
		return new InMemoryTestQuestion();
	}
}
