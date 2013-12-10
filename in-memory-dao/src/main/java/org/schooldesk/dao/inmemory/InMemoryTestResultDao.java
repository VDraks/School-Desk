package org.schooldesk.dao.inmemory;

import org.schooldesk.dao.ITestResultDao;
import org.schooldesk.dto.ITestResult;
import org.schooldesk.dto.inmemory.InMemoryTestResult;

import java.util.Set;


public class InMemoryTestResultDao extends InMemoryAbstractDao<ITestResult> implements ITestResultDao {
	@Override
	public ITestResult createDto(Long testId, Long userId, Set<Long> userTestAnswerIds) {
		return new InMemoryTestResult();
	}
}
