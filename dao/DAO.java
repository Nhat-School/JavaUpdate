package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	public static Connection con;

	public DAO() {
		if (con == null) {
			String dbUrl = "jdbc:mysql://localhost:3308/f1championship?autoReconnect=true&useSSL=false";
			try {
				con = DriverManager.getConnection(dbUrl, "root", "Cnpm@2020?");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
