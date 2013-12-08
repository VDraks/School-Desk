package org.schooldesk.dto.impl;

import org.schooldesk.dto.*;


public class RightDto extends AbstractDto implements IRight {
	private String code;

	@Deprecated
	@UsedForMapping
	public RightDto() {}

	public static RightDto createNew(String code) {
		@SuppressWarnings("deprecation")
		RightDto dto = new RightDto();
		dto.setCode(code);
		return dto;
	}

	@Override
	public String getCode() {
		return code;
	}

	@UsedForMapping
	public void setCode(String code) {
		this.code = code;
	}
}
