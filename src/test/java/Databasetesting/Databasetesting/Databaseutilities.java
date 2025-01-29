package Databasetesting.Databasetesting;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Databaseutilities {
	static Properties properties;
	static FileInputStream file;
	private static String dburl;
	private static String dbusername;
	private static String dbpassword;
	static {
		properties = new Properties();
		try {
			file = new FileInputStream("config.properties");
			properties.load(file);
			 dburl = properties.getProperty("dburl");
	         dbusername = properties.getProperty("dbusername");
	         dbpassword = properties.getProperty("dbpassword");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	protected static String getdatafromdb(String query) throws IOException {
		 String result="";
			try {
				Connection conn= DriverManager.getConnection(dburl,dbusername,dbpassword);
				java.sql.Statement statement = conn.createStatement();
				ResultSet resultSet= statement.executeQuery(query);
				if(resultSet.next()) {
				 result=resultSet.getString(1);
				}
				resultSet.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result; 
	}

}
