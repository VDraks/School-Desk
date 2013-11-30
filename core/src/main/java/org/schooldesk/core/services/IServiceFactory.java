package org.schooldesk.core.services;

public interface IServiceFactory {
	<T extends IService> T getService(Class<T> serviceClass);
}
