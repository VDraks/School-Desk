package org.schooldesk.dto.inmemory;

import java.util.*;

import org.schooldesk.dto.*;


public class InMemoryRight extends InMemoryAbstractDto implements IRight {
	private String code;

	@Deprecated
	public InMemoryRight(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
}
