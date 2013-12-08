package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


//@Entity
public class TestCore extends ResourceCore {
//	@ManyToMany
	private Set<TestQuestionCore> testQuestions;

	public TestCore() {}

	public Set<TestQuestionCore> getTestQuestions() {
		return testQuestions;
	}

	public void setTestQuestions(Set<TestQuestionCore> testQuestions) {
		this.testQuestions = testQuestions;
	}

	@Override
	@SuppressWarnings("deprecation")
	public TestDto toDto() {
		return mapDto(new TestDto());
	}

	@Override
	protected TestDto mapDto(AbstractDto dto) {
		TestDto testDto = (TestDto) super.mapDto(dto);
		testDto.setTestQuestionIds(getIds(getTestQuestions()));
		return testDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		ITest test = (ITest) dto;
		setTestQuestions(new HashSet<>(coreApi.loadByIds(TestQuestionCore.class, test.getTestQuestionIds())));

		super.fromDto(dto, coreApi);
	}
}
