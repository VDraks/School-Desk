package org.schooldesk.dto.impl;

import org.schooldesk.dto.IRightDto;

public class RightDto extends Dto implements IRightDto{
	private String code;

	/**
	 * @deprecated exists for mapping purposes
	 */
	public RightDto() {}

	public static RightDto createNew(String code)
	{
		RightDto dto = new RightDto();
		dto.setCode(code);
		return dto;
	}

	@Override
	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}
}
