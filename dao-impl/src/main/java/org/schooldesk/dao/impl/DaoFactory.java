package org.schooldesk.dao.impl;

import java.io.*;
import java.net.*;
import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.schooldesk.dao.*;

public class DaoFactory implements IDaoFactory
{
	static
	{
		try
		{
			Class.forName("org.h2.Driver");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		new DaoFactory("admin", "pass", "jdbc:h2:test");
	}
	
	private Map<Class<? extends IDao<?>>, IDao<?>> daoPool = new HashMap<Class<? extends IDao<?>>, IDao<?>>();

	private static Configuration CONFIGURATION = new AnnotationConfiguration();
	private SessionFactory sessionFactory;
	
	public DaoFactory(String login, String password, String connectionURL)
	{
		Properties properties = new Properties();
//		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		properties.setProperty("hibernate.connection.url", connectionURL);
		properties.setProperty("hibernate.connection.username", login);
	    properties.setProperty("hibernate.connection.password", password);
	    properties.setProperty("hibernate.show_sql", "false");
//	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	    properties.setProperty("hibernate.hbm2ddl.auto", "update");
		CONFIGURATION.setProperties(properties);
		
		List<Class<?>> classesInPackage = getClassesForPackage("org.schooldesk.dao.impl");
		
		for (Class<?> annotatedClass : classesInPackage)
		{
			CONFIGURATION.addAnnotatedClass(annotatedClass);
		}
		
		sessionFactory = CONFIGURATION.buildSessionFactory();
	}

	@Override
	public <T extends IDao<?>> T getDao(Class<T> daoClass)
	{
		return getFromPool(daoClass);
	}

	@SuppressWarnings("unchecked")
	private <T extends IDao<?>> T getFromPool(Class<T> daoClass)
	{
		IDao<?> dao = daoPool.get(daoClass);
		if (dao == null)
		{
			if (daoClass == IGroupDao.class)
			{
				daoPool.put(daoClass, dao = new GroupDao(sessionFactory));
			}
			else if (daoClass == IRightDao.class)
			{
				daoPool.put(daoClass, dao = new RightDao(sessionFactory));
			}
			else if (daoClass == IUserDao.class)
			{
				daoPool.put(daoClass, dao = new UserDao(sessionFactory));
			}
		}
		return (T) dao;
	}
	
	private static Class<?> loadClass(String className)
	{
        try
        {
        	return Class.forName(className);
        } 
        catch (ClassNotFoundException e)
        {
        	throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
        }
	}

	private static void processDirectory(File directory, String packageName, ArrayList<Class<?>> classes)
	{
        for (String fileName : directory.list())
        {
            String className = null;
            
            if (fileName.endsWith(".class"))
            {
				className = packageName + '.' + fileName.substring(0, fileName.length() - 6);
            }
            if (className != null)
            {
            	classes.add(loadClass(className));
            }
        }
	}

	private static ArrayList<Class<?>> getClassesForPackage(String packageName)
	{
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		
		String relPath = packageName.replace('.', '/');
		
		URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
		if (resource == null)
		{
		        throw new RuntimeException("Unexpected problem: No resource for " + relPath);
		}
		
		processDirectory(new File(resource.getPath()), packageName, classes);
		
		return classes;
	}
}
