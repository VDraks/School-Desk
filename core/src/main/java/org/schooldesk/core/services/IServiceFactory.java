package org.schooldesk.core.services;

import org.schooldesk.dao.IDAO;
import org.schooldesk.dto.IGroupDTO;


public interface IServiceFactory extends IDAO<IGroupDTO> {
	<T extends IService> T getService(Class<T> serviceClass);
}
