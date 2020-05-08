package sodabase.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {

	//DO NOT EDIT THIS STRING, YOU WILL RECEIVE NO CREDIT FOR THIS TASK IF THIS STRING IS EDITED
	private final String URL = "jdbc:sqlserver://golem.csse.rose-hulman.edu;databaseName=FoodSelector;user=food19;password={123P}";

	private Connection connection = null;

	private String databaseName;
	private String serverName;

	public DatabaseConnectionService(String serverName, String databaseName) {
		// DO NOT CHANGE THIS METHOD
		this.serverName = serverName;
		this.databaseName = databaseName;
	}

	public boolean connect() {
		try (Connection connection2 = DriverManager.getConnection(URL);) {
			this.connection = DriverManager.getConnection(URL);
			return true;
		}
		// Handle any errors that may have occurred.
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	

	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
