package org.schooldesk.core;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;


@Entity
public class CourseSectionCore extends AbstractCore {
	private String name;

	@OneToOne
	private TestCore test;

	public CourseSectionCore() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TestCore getTest() {
		return test;
	}

	public void setTest(TestCore test) {
		this.test = test;
	}

	@Override
	public CourseSectionDto toDto() {
		return mapDto(new CourseSectionDto());
	}

	@Override
	protected CourseSectionDto mapDto(AbstractDto dto) {
		CourseSectionDto courseSectionDto = (CourseSectionDto) super.mapDto(dto);
		courseSectionDto.setName(getName());
		courseSectionDto.setTestId(getTest().getId());
		return courseSectionDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		CourseSectionDto courseSectionDto = (CourseSectionDto) dto;
		setName(courseSectionDto.getName());
		setTest(coreApi.loadById(TestCore.class, courseSectionDto.getTestId()));
	}
}
