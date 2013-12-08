package org.schooldesk.dao.hibernateimpl;

import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class DbHelper {
	private CoreApi coreApi;

//	private static final String QUERY_FOR_TABLE_NAMES_MySQL =
//			"SELECT TABLE_NAME " +
//			"FROM INFORMATION_SCHEMA.TABLES " +
//			"WHERE TABLE_TYPE = 'BASE TABLE'";

	private static final String QUERY_FOR_TABLE_NAMES_H2 =
			"SHOW TABLES";

	public DbHelper(HibernateDaoFactory daoFactory) {
		coreApi = daoFactory.getCoreApi();
	}

	public void clearDb() {
		coreApi.getSession().doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				Set<String> tableNames = queryForTableNames(connection);
				while (!tableNames.isEmpty()) {
					clearTables(connection, tableNames);
				}
			}
		});
	}

	private Set<String> queryForTableNames(Connection connection) throws SQLException {
		Set<String> tableNames = new HashSet<>();
		String queryText = QUERY_FOR_TABLE_NAMES_H2;

		try (Statement statement = connection.createStatement();
		     ResultSet resultSet = statement.executeQuery(queryText)) {
			while (resultSet.next()) {
				tableNames.add(resultSet.getString(1));
			}
		}

		return tableNames;
	}

	private void clearTables(Connection connection, Set<String> tableNames) {
		for (Iterator<String> it = tableNames.iterator(); it.hasNext(); ) {
			if (clearTable(connection, it.next())) {
				it.remove();
			}
		}
	}

	private boolean clearTable(Connection connection, String tableName) {
		try (Statement statement = connection.createStatement()) {
			statement.execute("DELETE FROM " + tableName);
			return true;
		}
		catch (SQLException ex) {
			return false;
		}
	}
}
