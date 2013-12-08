package org.schooldesk.hibernateobjects;

import com.sun.istack.internal.*;
import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;


@Entity
public class CourseSectionCore extends AbstractCore {
	@ManyToOne
	@NotNull
	CourseCore source;

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
	@SuppressWarnings("deprecation")
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
		ICourseSection courseSection = (ICourseSection) dto;
		setName(courseSection.getName());
		setTest(coreApi.loadById(TestCore.class, courseSection.getTestId()));
	}
}
