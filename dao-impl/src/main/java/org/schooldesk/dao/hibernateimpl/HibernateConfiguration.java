package org.schooldesk.dao.hibernateimpl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.schooldesk.hibernateobjects.CourseCore;
import org.schooldesk.hibernateobjects.CourseSectionCore;
import org.schooldesk.hibernateobjects.ResourceCore;
import org.schooldesk.hibernateobjects.TestCore;

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
		CONFIGURATION.addAnnotatedClass(ResourceCore.class);
        CONFIGURATION.addAnnotatedClass(TestCore.class);
        CONFIGURATION.addAnnotatedClass(CourseSectionCore.class);
        CONFIGURATION.addAnnotatedClass(CourseCore.class);
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
	public static SessionFactory buildSessionFactory(String login, String password, String connectionURL, Boolean dropBeforeCreate) throws HibernateException {
		CONFIGURATION.setProperty("hibernate.connection.url", connectionURL + ";DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE");
		CONFIGURATION.setProperty("hibernate.connection.username", login);
		CONFIGURATION.setProperty("hibernate.connection.password", password);
		CONFIGURATION.setProperty("hibernate.hbm2ddl.auto", dropBeforeCreate ? "create" : "update");

		return CONFIGURATION.buildSessionFactory();
	}
}
