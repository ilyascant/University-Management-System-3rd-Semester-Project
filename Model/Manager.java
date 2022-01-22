package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Manager extends Person{

	private String adminMail;
	
	
	public Manager() {
	}
	
	public String getAdminMail() {
		return adminMail;
	}



	public void setAdminMail(String adminMail) {
		this.adminMail = adminMail;
	}
	
	

	
	
	
	
	
}
