package org.schooldesk.tests;

import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.schooldesk.dao.DaoFactory;
import org.schooldesk.dao.IDao;
import org.schooldesk.dao.hibernateimpl.HibernateConfiguration;
import org.schooldesk.dao.hibernateimpl.HibernateDaoFactory;
import org.schooldesk.dto.IDto;

import java.io.*;
import java.util.Properties;


public class CAbstractTest {

	private static DaoFactory factory;

	@BeforeClass
	public static void initializeDatabase() throws IOException {

		/**
		 * Create file with properties for hibernate (login, password, url)
		 */
		String DATABASE_FILE_NAME = "testDatabase";
		File temporaryProperties = File.createTempFile("test", ".config");
		temporaryProperties.deleteOnExit();
		Properties configuration = new Properties();
		configuration.setProperty("db_login", System.getProperty("user.dir") + "\\" + DATABASE_FILE_NAME);
		configuration.setProperty("db_password", "admin");
		configuration.setProperty("db_connection", "admin");
		try (OutputStream os = new FileOutputStream(temporaryProperties)) {
			configuration.store(os, "");

		}
		factory = new HibernateDaoFactory(temporaryProperties.getAbsolutePath());
		new File(DATABASE_FILE_NAME).deleteOnExit();
	}

	protected DaoFactory getFactory()
	{
		 return factory;
	}
}
