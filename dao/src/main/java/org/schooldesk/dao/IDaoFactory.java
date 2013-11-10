package org.schooldesk.dao;

public interface IDaoFactory {
	<T extends IDao> T getDao(Class<T> daoClass);
}
