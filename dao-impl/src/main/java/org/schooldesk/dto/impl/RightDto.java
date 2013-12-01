package org.schooldesk.dto.impl;

import org.schooldesk.dto.IRight;

public class RightDto extends AbstractDto implements IRight {
	private String code;

	/**
	 * @deprecated exists for mapping purposes
	 */
	public RightDto() {}

	public static RightDto createNew(String code) {
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
