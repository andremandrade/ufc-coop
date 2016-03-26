package br.ufc.coop.java.dbdriver.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager {

	private static String DB_URL = "jdbc:mysql://localhost/school";
	private static String DB_USER = "root";
	private static String DB_PASSWORD = "root";
	
	public static void loadDriver() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	}
	
	public ResultSet executeQuery(String sqlQuery) throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		stmt = conn.createStatement();
		return stmt.executeQuery(sqlQuery);
	}
	
	public long executeInsert(String sqlCommand) throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		stmt = conn.createStatement();
		stmt.executeUpdate(sqlCommand, Statement.RETURN_GENERATED_KEYS);
		
		rs = stmt.getGeneratedKeys();
		
		if (rs.next()) {
			 return rs.getInt(1);
		}
		return -1;
	}

	public void executeUpdate(String sqlCommand) throws SQLException{
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		stmt = conn.createStatement();
		stmt.executeUpdate(sqlCommand);
	}
}
