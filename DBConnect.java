package shiftmanager;

import java.sql.*;

public class DBConnect {

    private static final String URL = "jdbc:mysql://localhost:3306/shiftdb";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("Database Connection Failed");
            e.printStackTrace();
            return null;
        }
    }

	public static Connection connect() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Connection connect1() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Connection connect1() {
		// TODO Auto-generated method stub
		return null;
	}
}
