package org.schooldesk.core;

import org.schooldesk.dto.impl.*;

public class RightCore extends CoreObject {
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
		RightDto dto = new RightDto();
		dto.setId(getId());
		dto.setCode(getCode());
		return dto;
	}

	@Override
	public void fromDto(Dto dto) {
		RightDto rightDto = (RightDto) dto;
		setCode(rightDto.getCode());
	}
}
