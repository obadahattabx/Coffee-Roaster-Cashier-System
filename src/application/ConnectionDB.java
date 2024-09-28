package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
 	public static  Connection connectDB()throws  ClassNotFoundException,SQLException {
		 String username="root";
		 String password ="1234";
		  String url="127.0.0.1";
		 String port ="3306";
		  String DBName="ALMKHTAR";
		  Connection con ;
			
			String dburl="jdbc:mysql://"+ url + ":"+ port +"/"+ DBName +
					"?user=root&password=1234";
			Properties P = new Properties();
			P.setProperty("user", "root");
			P.setProperty("password", "1234");
			P.setProperty("useSSL", "false");
			P.setProperty("autoReconnect", "true"); 
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection(dburl,P);
	return con;
		}
}
