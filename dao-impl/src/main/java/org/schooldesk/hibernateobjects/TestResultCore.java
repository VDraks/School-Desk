package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
public class TestResultCore extends AbstractCore {
	@OneToOne
	private TestCore test;

	@OneToOne
	private UserCore user;

	@OneToMany
	private Set<UserTestAnswerCore> userTestAnswers;

	public TestResultCore() {}

	public TestCore getTest() {
		return test;
	}

	public void setTest(TestCore test) {
		this.test = test;
	}

	public UserCore getUser() {
		return user;
	}

	public void setUser(UserCore user) {
		this.user = user;
	}

	public Set<UserTestAnswerCore> getUserTestAnswers() {
		return userTestAnswers;
	}

	public void setUserTestAnswers(Set<UserTestAnswerCore> userTestAnswers) {
		this.userTestAnswers = userTestAnswers;
	}

	@Override
	public TestResultDto toDto() {
		return mapDto(new TestResultDto());
	}

	@Override
	protected TestResultDto mapDto(AbstractDto dto) {
		TestResultDto testResultDto = (TestResultDto) super.mapDto(dto);
		testResultDto.setTestId(getTest().getId());
		testResultDto.setUserId(getUser().getId());
		testResultDto.setUserTestAnswerIds(getIds(getUserTestAnswers()));
		return testResultDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		ITestResult testResult = (ITestResult) dto;
		setTest(coreApi.loadById(TestCore.class, testResult.getTestId()));
		setUser(coreApi.loadById(UserCore.class, testResult.getUserId()));
		setUserTestAnswers(new HashSet<>(coreApi.loadByIds(UserTestAnswerCore.class, testResult.getUserTestAnswerIds())));
	}
}
