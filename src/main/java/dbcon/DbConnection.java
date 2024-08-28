package dbcon;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	// private constructor - prevents creating instances
	private DbConnection() {}
	
	// method to create and return a database connection
	public static Connection getConnection() {
		
		Connection con = null;
		try {
			Class.forName(DbConfig.getDbDriver());
			String url = DbConfig.getDbUrl();
			String user = DbConfig.getDbUsername();
			String password = DbConfig.getDbPassword();
			con = DriverManager.getConnection(url, user, password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
