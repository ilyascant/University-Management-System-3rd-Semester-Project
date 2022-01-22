package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Instructor extends Person{

	private String instructor_mail;
	private String degree;
	private int facultyId;
	private String facultyName;
	private int departmentId;
	private String departmentName;
	Faculty fc = new Faculty();
	Department dp = new Department();
	
	Connection conn = connection.connDb();
	Statement state = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Instructor() {
	}
	
	public Instructor(int id, String first_name, String last_name, String identityNumber, String birth, int age,
			String degree, String facultyName, String departmentName) {
		super(id, first_name, last_name, identityNumber, birth);
		instructor_mail = first_name + "." + last_name + "@edu.tr";
		this.degree = degree;
		this.facultyId = fc.getFetch(facultyName).getFacultyId();
		this.facultyName = facultyName;
		this.departmentName = departmentName;
		this.departmentId = dp.getFetch(departmentName).getId();
	}
	
	public String getMail() {
		return instructor_mail;
	}


	public void setMail(String mail) {
		this.instructor_mail = mail;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
	
	public ArrayList<Instructor> getAllInstructors(){
		
		
		ArrayList<Instructor> list = new ArrayList<Instructor>();
		Instructor obj;
		try {
			
			state = conn.createStatement();
			rs = state.executeQuery("SELECT * FROM instructors");
			
			while(rs.next()) {
				obj = new Instructor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("tc_no"), rs.getString("birth"), rs.getInt("age"),
						rs.getString("degree"), rs.getString("faculty_name"), rs.getString("department_name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	public boolean addInstructor(String instructorFirstName, String instructorLastName, String identityNumber, String degree, String facultyName, String departmentName) {
		
		String query = "INSERT INTO instructors (first_name,last_name,degree,tc_no,faculty_name,department_name,department_id) VALUES (?,?,?,?,?,?,?)";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, instructorFirstName);
			preparedStatement.setString(2, instructorLastName);
			preparedStatement.setString(3, degree);
			preparedStatement.setString(4, identityNumber);
			preparedStatement.setString(5, facultyName);
			preparedStatement.setString(6, departmentName);
			preparedStatement.setInt(7, dp.getFetch(departmentName).getId());
			if(preparedStatement.executeUpdate() == 0)      //Sorguyu çalýþtýrýr
				kontrol = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kontrol;
	}
	
	public boolean addInstructor(Instructor instructor) {
		
		String query = "INSERT INTO instructors (first_name,last_name,degree,tc_no,mail,faculty_name,faculty_id,department_name,department_id,password) VALUES (?,?,?,?,?,?,?,?,?,?)";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, instructor.getFirst_name());
			preparedStatement.setString(2, instructor.getLast_name());
			preparedStatement.setString(3, instructor.getDegree());
			preparedStatement.setString(4, instructor.getIdentityNumber());
			preparedStatement.setString(5, instructor.getFirst_name()+"."+instructor.getLast_name()+"@edu.tr");
			preparedStatement.setString(6, instructor.getFacultyName());
			preparedStatement.setInt(7, fc.getFetch(instructor.getFacultyName()).getFacultyId());
			preparedStatement.setString(8, instructor.getDepartmentName());
			preparedStatement.setInt(9, dp.getFetch(departmentName).getId());
			preparedStatement.setString(10, instructor.getIdentityNumber());
			if(preparedStatement.executeUpdate() == 0)      //Sorguyu çalýþtýrýr
				kontrol = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kontrol;
	}
	
	public boolean removeInstructor(int id) {
		
		String query = "DELETE FROM instructors WHERE (`id` = '" + id +"')";
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
	
	public boolean updateInstructor(int id, String name, String surname, String degree, String tcNo, String mail, String faculty, String department) {
		
		String query = "UPDATE instructors SET first_name = ?, last_name = ?, degree = ?, tc_no = ?, mail = ?, faculty_name = ?, department_name = ? WHERE id = ?";  //name deðiþiyor db'deki id deðiþmiyor unutma!
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, degree);
			preparedStatement.setString(4, tcNo);
			preparedStatement.setString(5, name+"."+surname+"@edu.tr");
			preparedStatement.setString(6, faculty);
			preparedStatement.setString(7, department);
			preparedStatement.setInt(8, id);
			if(preparedStatement.executeUpdate() == 0)
				kontrol = false;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return kontrol;
	}
	
	
	
	
	
	
}
