package jdbc;

import java.sql.*;

public class DBConnection {
	
	public static Connection getConnection()
	{
		String url = "";
		
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver loaded...");
			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/employee", "root", "");
			System.out.println("Database connected...");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}

}
