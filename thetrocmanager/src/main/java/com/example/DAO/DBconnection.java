package com.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBconnection {
	private String user = "nomade";
	private String passwort= "nomadeguewou";
	private String url ="jdbc:mysql://localhost:3306/thetroc";
	private Connection connection;
	
	private DBconnection() {
		init();
	}

	
	private void  init() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, passwort);
			
		} catch (ClassNotFoundException e) {
		
		
		} catch (SQLException e) {
			
		}
	}
	
	public static DBconnection getInstance() {
		DBconnection dbconnection=null;
		if(dbconnection==null) {
			dbconnection = new DBconnection();
		}
		
		return dbconnection;
	}
	
	
	public PreparedStatement getPreparedStatement(String url) throws SQLException {
		
		return connection.prepareStatement(url);
	}
	
	public Statement getStatement() throws SQLException {
		return connection.createStatement();
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			
		}
	}
	
}
