package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.ITestResult;

import java.util.Set;


public class InMemoryTestResult extends InMemoryAbstractDto implements ITestResult {
	@Override
	public Long getTestId() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public Long getUserId() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Long> getUserTestAnswerIds() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
