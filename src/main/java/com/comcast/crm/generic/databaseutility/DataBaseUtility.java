package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {

	Connection con;// bcz we close the DB in another method if local we declare we can not use to
	// another method

	public void getDbConnection(String url, String username, String password) throws Throwable {

		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			// if connection is not happen than we can handle it
		}
	}

	//we can hard code the url bcz entire application we have only one data base in such case we are not ask the url 
	public void getDbConnection() throws Throwable {

		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/madhu", "root", "root");

		} catch (Exception e) {
			// if connection is not happen than we can handle it
		}
	}

	public void closeDbConnection() throws Throwable {
		try {
			con.close();

		} catch (Exception e) {

		}
	}

	public ResultSet executeSelectQuery(String query) throws Throwable {

		ResultSet result = null;
		try {
			Statement stat = con.createStatement();
			result = stat.executeQuery(query);

		} catch (Exception e) {
		}

		return result;// retrn table

	}

	public int executeNonselectQuery(String query) {
		int result = 0;
		try {
			Statement stat = con.createStatement();
			result = stat.executeUpdate(query);

		} catch (Exception e) {
		}

		return result;// if it result 0 execution is failed if return +_1 execution is pass
	}

}
