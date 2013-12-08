package org.schooldesk.dao.hibernateimpl;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.schooldesk.hibernateobjects.CourseCore;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


public class HibernateConfiguration {
	private static final Configuration CONFIGURATION;

	static {
		@SuppressWarnings("deprecation")
		AnnotationConfiguration config = new AnnotationConfiguration();
		CONFIGURATION = config;
		CONFIGURATION.setProperties(makeCommonProperties());
		for (Class<?> annotatedClass : getClassesInPackage("org.schooldesk.hibernateobjects")) {
			CONFIGURATION.addAnnotatedClass(annotatedClass);
		}
	}

	private static Properties makeCommonProperties() {
		Properties properties = new Properties();
//		properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		properties.setProperty("hibernate.show_sql", "false");
//	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		return properties;
	}

	@SuppressWarnings("deprecation")
	public static SessionFactory buildSessionFactory(String login, String password, String connectionURL, Boolean dropBeforeCreate) {
		CONFIGURATION.setProperty("hibernate.connection.url", connectionURL);
		CONFIGURATION.setProperty("hibernate.connection.username", login);
		CONFIGURATION.setProperty("hibernate.connection.password", password);
		CONFIGURATION.setProperty("hibernate.hbm2ddl.auto", dropBeforeCreate ? "create" : "update");

		return CONFIGURATION.buildSessionFactory();
	}

	private static List<Class<?>> getClassesInPackage(String packageName) {
		String path = makePathFromPackage(packageName);
		URL resource = ClassLoader.getSystemClassLoader().getResource(path);
		if (resource == null) {
			throw new RuntimeException("Unexpected problem: No resource for " + path);
		}

		return getClasses(new File(resource.getPath()), packageName);
	}

	private static String makePathFromPackage(String packageName) {
		return packageName.replace('.', '/');
	}

	private static List<Class<?>> getClasses(File directory, String packageName) {
		List<Class<?>> classes = new ArrayList<>();

		for (String fileName : directory.list()) {
			String className = null;

			if (fileName.endsWith(".class")) {
				className = packageName + '.' + fileName.substring(0, fileName.length() - 6);
			}
			if (className != null) {
				classes.add(loadClass(className));
			}
		}

//		return classes;
		List<Class<?>> result = new ArrayList<Class<?>>();
		try {
			result.add(Class.forName(packageName + ".CourseCore"));
			result.add(Class.forName(packageName + ".CourseSectionCore"));
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		return result;
	}

	private static Class<?> loadClass(String className) {
		try {
			return Class.forName(className);
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
		}
	}
}
