package com.informatics.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseconnection {
	public Connection getConnect() {
		Connection con = null;
	
	try {
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databasecredentials.getDbName(),databasecredentials.getUsername(),databasecredentials.getPassword());
		
		}
	 catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	return con;

}
}
