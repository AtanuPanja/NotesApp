package dbcon;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DbConnection {
	
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
