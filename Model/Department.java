package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Department extends Faculty{

	private int id;
	private String departmentName;
	private Faculty faculty;
	Faculty fc = new Faculty();
	
	static Connection conn = connection.connDb();
	static Statement state = null;
	static ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	
	public Department() {}
	
	public Department(Faculty faculty, int id, String departmentName, String facultyName) {
		this.faculty = fc.getFetch(facultyName);
		this.id = id;
		this.departmentName = departmentName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public ArrayList<Department> getAllDepartments(){
		
		
		ArrayList<Department> list = new ArrayList<Department>();
		Department obj;
		try {
			
			state = conn.createStatement();
			rs = state.executeQuery("SELECT * FROM departments");
			
			while(rs.next()) {
				obj = new Department(fc.getFetch(rs.getString("faculty_name")) , rs.getInt("id"), rs.getString("department_name"), rs.getString("faculty_name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	public Department getFetch(int id) {
		
		String query = "SELECT * FROM departments WHERE id = " + id;
		Department fc = new Department();
		try {
			state = conn.createStatement();
			rs = state.executeQuery(query);
			while(rs.next()) {
				fc.setId(rs.getInt("id"));
				fc.setDepartmentName(rs.getString("department_name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fc;
	}
	
	public Department getFetch(String name) {
		
		String query = "SELECT * FROM departments WHERE department_name = " + "'" + name + "'";
		Department fc = new Department();
		try {
			state = conn.createStatement();
			rs = state.executeQuery(query);
			while(rs.next()) {
				fc.setId(rs.getInt("id"));
				fc.setDepartmentName(rs.getString("department_name"));
				fc.setFacultyName(rs.getString("faculty_name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fc;
	}
	
	public boolean addDepartment(String departmentName) {
		
		String query = "INSERT INTO departments (department_name) VALUES (?)";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, departmentName);

			if(preparedStatement.executeUpdate() == 0)      //Sorguyu çalýþtýrýr
				kontrol = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kontrol;
	}
	
	public boolean updateDepartment(Department department) {
		
		String query = "UPDATE departments SET department_name = ?, faculty_name = ? WHERE id = ?";  
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, department.getDepartmentName());
			preparedStatement.setString(2, department.getFaculty().getFacultyName());
			preparedStatement.setInt(3, department.getId());
			if(preparedStatement.executeUpdate() == 0)
				kontrol = false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return kontrol;
	}
	
	public boolean updateDepartment(int id, String name) {
		
		String query = "UPDATE departments SET department_name = ? WHERE id = ?";  
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, id);
			if(preparedStatement.executeUpdate() == 0)
				kontrol = false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return kontrol;
	}
	
	public boolean removeDepartment(int id) {
		
		String query = "DELETE FROM departments WHERE (`id` = '" + id +"')";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			if(state.executeUpdate(query) == 0) {    //Sorguyu çalýþtýrýr
				kontrol = false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return kontrol;
	}
	
}
