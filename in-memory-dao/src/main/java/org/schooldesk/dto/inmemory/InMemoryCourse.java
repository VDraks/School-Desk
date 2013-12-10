package org.schooldesk.dto.inmemory;

import org.schooldesk.dto.ICourse;

import java.util.List;


public class InMemoryCourse extends InMemoryAbstractDto implements ICourse {
	@Override
	public String getName() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public void setName(String name) {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Long> getCourseSectionIds() {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}

	@Override
	public void setCourseSectionIds(List<Long> courseSectionIds) {
		// TODO: implement me
		throw new UnsupportedOperationException();
	}
}
