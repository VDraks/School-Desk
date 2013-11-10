package org.schooldesk.core.services;

import org.schooldesk.dao.IDao;
import org.schooldesk.dto.IGroupDto;


public interface IServiceFactory extends IDao<IGroupDto> {
	<T extends IService> T getService(Class<T> serviceClass);
}
