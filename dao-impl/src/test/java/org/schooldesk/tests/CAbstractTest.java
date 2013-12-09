package org.schooldesk.tests;

import org.junit.*;
import org.schooldesk.dao.*;
import org.schooldesk.dao.hibernateimpl.*;

import java.io.*;
import java.util.*;


public class CAbstractTest {

	private static DaoFactory factory;
	private static final String databaseFileName = System.getProperty("user.dir") + "\\testDatabase";

	@BeforeClass
	public static void initializeDatabase() throws IOException {

		factory = new HibernateDaoFactory("test.config");
	}

	protected DaoFactory getFactory() {
		return factory;
	}
}
