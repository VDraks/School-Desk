package org.schooldesk.core;

import javax.persistence.*;

import org.schooldesk.dto.*;
import org.schooldesk.dto.impl.*;


@Entity
public class RightCore extends AbstractCore {
	private String code;

	public RightCore() {}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public RightDto toDto() {
		return mapDto(new RightDto());
	}

	@Override
	protected RightDto mapDto(AbstractDto dto) {
		RightDto rightDto = (RightDto) super.mapDto(dto);
		rightDto.setCode(getCode());
		return rightDto;
	}

	@Override
	public void fromDto(IDto dto) {
		RightDto rightDto = (RightDto) dto;
		setCode(rightDto.getCode());
	}
}
