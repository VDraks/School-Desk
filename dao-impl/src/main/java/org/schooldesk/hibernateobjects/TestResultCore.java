package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


@Entity
public class TestResultCore extends AbstractCore {

	private TestCore test;
	private UserCore user;


	private Set<UserTestAnswerCore> userTestAnswers;

	public TestResultCore() {}

	@OneToOne(mappedBy = "testResult", cascade = CascadeType.REMOVE)
	public TestCore getTest() {
		return test;
	}

	public void setTest(TestCore test) {
		this.test = test;
	}

	@OneToOne(mappedBy = "testResult", cascade = CascadeType.REMOVE)
	public UserCore getUser() {
		return user;
	}

	public void setUser(UserCore user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "testResult", cascade = CascadeType.REMOVE)
	public Set<UserTestAnswerCore> getUserTestAnswers() {
		return userTestAnswers;
	}


	public void setUserTestAnswers(Set<UserTestAnswerCore> userTestAnswers) {
		this.userTestAnswers = userTestAnswers;
	}

	@Override
	@SuppressWarnings("deprecation")
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
