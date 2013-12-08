package org.schooldesk.hibernateobjects;

import org.hibernate.*;
import org.schooldesk.dao.hibernateimpl.*;
import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;

import javax.persistence.*;
import java.util.*;


//@Entity
public class EducationStageCore extends AbstractCore {
	private String name;

//	@OneToMany
	private List<CourseCore> courses;

	public EducationStageCore() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CourseCore> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseCore> courses) {
		this.courses = courses;
	}

	@Override
	@SuppressWarnings("deprecation")
	public EducationStageDto toDto() {
		return mapDto(new EducationStageDto());
	}

	@Override
	protected EducationStageDto mapDto(AbstractDto dto) {
		EducationStageDto educationStageDto = (EducationStageDto) super.mapDto(dto);
		educationStageDto.setName(getName());
		educationStageDto.setCourseIds(getIds(getCourses()));
		return educationStageDto;
	}

	@Override
	public void fromDto(IDto dto, CoreApi coreApi) throws HibernateException {
		IEducationStage educationStage = (IEducationStage) dto;
		setName(educationStage.getName());
		setCourses(coreApi.loadByIds(CourseCore.class, educationStage.getCourseIds()));
	}
}
