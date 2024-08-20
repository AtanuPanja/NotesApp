package dbcon;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class DbConfig {
	
	private static String dbUrl;
	private static String dbName;
	private static String dbUsername;
	private static String dbPassword;
	private static String dbDriver;
	
	public static String getDbUrl() {
		return dbUrl;
	}

	public static String getDbName() {
		return dbName;
	}
	
	public static String getDbUsername() {
		return dbUsername;
	}

	public static String getDbPassword() {
		return dbPassword;
	}

	public static String getDbDriver() {
		return dbDriver;
	}

	static {
		
		try (InputStream input = DbConfig.class.getClassLoader().getResourceAsStream("config.properties"))
		{
			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
			}
			else {
				Properties properties = new Properties();
				properties.load(input);
				dbUrl = properties.getProperty("db.url");
				dbName = properties.getProperty("db.name");
				dbUsername = properties.getProperty("db.username");
				dbPassword = properties.getProperty("db.password");
				dbDriver = properties.getProperty("db.driver");
				
				dbUrl = dbUrl + dbName;
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
            throw new RuntimeException("Failed to load database configuration", e);
		}
	}
}
