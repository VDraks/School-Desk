package org.schooldesk.dao.hibernateimpl;

import org.hibernate.*;
import org.schooldesk.dto.*;


public interface IDtoable {
	Long getId();
	void setId(Long id);
	IDto toDto();
	void fromDto(IDto dto, CoreApi coreApi) throws HibernateException;
}
