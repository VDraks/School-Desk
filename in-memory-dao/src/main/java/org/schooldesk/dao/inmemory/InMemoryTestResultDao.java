package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ITestResultDao;
import org.schooldesk.dto.ITestResult;

import java.util.Set;


public class InMemoryTestResultDao extends InMemoryAbstractDao<ITestResult> implements ITestResultDao {
	@Override
	public ITestResult createDto(Long testId, Long userId, Set<Long> userTestAnswerIds) {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
