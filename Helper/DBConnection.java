package Helper;
import java.sql.*;

public class DBConnection {

	Connection c = null;
	
	public DBConnection() {
		
	}
	
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ums", "root", "0306193080msk");
			return c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
}
