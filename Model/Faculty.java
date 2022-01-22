package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Faculty {

	private int faculty_id;
	private String facultyName;

	static DBConnection connection = new DBConnection();
	static Connection conn = connection.connDb();
	static Statement state = null;
	static ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Faculty() {
	}

	public Faculty(int id, String facultyName) {
		super();
		this.faculty_id = id;
		this.facultyName = facultyName;
	}

	public ArrayList<Faculty> getAllFaculties() {

		ArrayList<Faculty> list = new ArrayList<Faculty>();
		Faculty obj;
		try {

			state = conn.createStatement();
			rs = state.executeQuery("SELECT * FROM faculties");

			while (rs.next()) {
				obj = new Faculty(rs.getInt("id"), rs.getString("faculty_name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public boolean addFaculty(String facultyName) {

		String query = "INSERT INTO faculties (faculty_name) VALUES (?)";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, facultyName);

			if (preparedStatement.executeUpdate() == 0) // Sorguyu çalýþtýrýr
				kontrol = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kontrol;
	}

	public boolean removFaculty(int id) {

		String query = "DELETE FROM faculties WHERE (`id` = '" + id + "')";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			if (state.executeUpdate(query) == 0) { // Sorguyu çalýþtýrýr
				kontrol = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kontrol;
	}

	public boolean updateFaculty(int id, String name) {

		String query = "UPDATE faculties SET faculty_name = ? WHERE id = ?";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			if (preparedStatement.executeUpdate() == 0)
				kontrol = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kontrol;
	}

	public Faculty getFetch(int id) {

		String query = "SELECT * FROM faculties WHERE id = " + id;
		Faculty fc = new Faculty();
		try {
			state = conn.createStatement();
			rs = state.executeQuery(query);
			while (rs.next()) {
				fc.setFacultyId(rs.getInt("id"));
				fc.setFacultyName(rs.getString("faculty_name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fc;
	}

	public Faculty getFetch(String facultyName) {

		String query = "SELECT * FROM faculties WHERE faculty_name = " + "'" + facultyName + "'";
		Faculty fc = new Faculty();
		try {
			state = conn.createStatement();
			rs = state.executeQuery(query);
			while (rs.next()) {
				fc.setFacultyId(rs.getInt("id"));
				fc.setFacultyName(rs.getString("faculty_name"));
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fc;
	}

	public int getFacultyId() {
		return faculty_id;
	}

	public void setFacultyId(int id) {
		this.faculty_id = id;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

}
