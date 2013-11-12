package org.schooldesk.dao.impl;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.schooldesk.dao.IDao;
import org.schooldesk.dao.IDaoFactory;
import org.schooldesk.dao.IGroupDao;
import org.schooldesk.dao.IRightDao;
import org.schooldesk.dao.IUserDao;

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

	private static Configuration INSTANCE = new AnnotationConfiguration();
	private SessionFactory sessionFactory;
	
	public DaoFactory(String login, String password, String connectionURL)
	{
		java.util.Properties properties = new java.util.Properties();
//		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		properties.setProperty("hibernate.connection.url", connectionURL);
		properties.setProperty("hibernate.connection.username", login);
	    properties.setProperty("hibernate.connection.password", password);
	    properties.setProperty("hibernate.show_sql", "false");
//	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	    properties.setProperty("hibernate.hbm2ddl.auto", "update");
		INSTANCE.setProperties(properties);
		
		List<Class<?>> classesInPackage = getClassesForPackage("org.schooldesk.dao.impl");
		
		for (Class<?> classX : classesInPackage)
		{
			INSTANCE.addAnnotatedClass(classX);
		}
		
		sessionFactory = INSTANCE.buildSessionFactory();
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

	private static void processDirectory(File directory, String pkgname, ArrayList<Class<?>> classes)
	{
        String[] files = directory.list();
        for (int i = 0; i < files.length; i++)
        {
            String fileName = files[i];
            String className = null;
            
            if (fileName.endsWith(".class"))
            {
				className = pkgname + '.' + fileName.substring(0, fileName.length() - 6);
            }
            if (className != null)
            {
            	classes.add(loadClass(className));
            }
        }
	}

	private static ArrayList<Class<?>> getClassesForPackage(String pkgname)
	{
		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
		
		String relPath = pkgname.replace('.', '/');
		
		URL resource = ClassLoader.getSystemClassLoader().getResource(relPath);
		if (resource == null)
		{
		        throw new RuntimeException("Unexpected problem: No resource for " + relPath);
		}
		
		processDirectory(new File(resource.getPath()), pkgname, classes);
		
		return classes;
	}
}
