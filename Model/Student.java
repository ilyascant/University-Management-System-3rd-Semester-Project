
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class Student extends Person {

	private String student_mail;
	private int facultyId;
	private String facultyName;
	private int departmentId;
	private String departmentName;
	private double score;
	private String number;
	
	Faculty fc = new Faculty();
	Department dp = new Department();

	static Connection conn = connection.connDb();
	static Statement state = null;
	static ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Student() {
	}
	
	public Student(String first_name) {
		
		super(first_name);
	}

	public Student(int id, String first_name, String last_name, String identityNumber, double score, String number,
			String birth, int facultyId, String facultyName, int departmentId, String departmentName) {
		super(id, first_name, last_name, identityNumber, birth);
		this.score = score;
		this.number = number;
		this.student_mail = number + "@ogrenci.edu.tr";
		this.facultyId = fc.getFetch(facultyName).getFacultyId();
		this.facultyName = facultyName;
		this.departmentId = dp.getFetch(departmentName).getId();
		this.departmentName = departmentName;
	}

	public String getSchoolNumber() {
		return number;
	}

	public void setSchoolNumber(String schoolNumber) {
		this.number = schoolNumber;
	}

	public String getMail() {
		return student_mail;
	}

	public void setMail(String mail) {
		this.student_mail = mail;
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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ArrayList<Student> getAllStudents() {

		ArrayList<Student> list = new ArrayList<Student>();
		Student obj;
		try {

			state = conn.createStatement();
			rs = state.executeQuery("SELECT * FROM students");

			while (rs.next()) {

				obj = new Student(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
						rs.getString("tc_no"), rs.getDouble("score"), rs.getString("school_number"),
						rs.getString("birth"), rs.getInt("faculty_id"), rs.getString("faculty_name"),
						rs.getInt("department_id"), rs.getString("department_name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	public boolean addStudent(Student student) {

		String query = "INSERT INTO students (first_name,last_name,score,school_number,tc_no,student_mail,faculty_name,faculty_id,department_name,department_id,birth) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, student.getFirst_name());
			preparedStatement.setString(2, student.getLast_name());
			preparedStatement.setString(3, String.valueOf(student.getScore()));
			preparedStatement.setString(4, student.getSchoolNumber());
			//student.setNumber(String.format("%2d%02d%02d%02d", LocalDate.now().getYear() % 100, faculty.getFetch(txt_add_student_faculty.getText()).getFacultyId(), student.getDepartmentId(), student.getId()));
			preparedStatement.setString(5, student.getIdentityNumber());
			preparedStatement.setString(6, student.getSchoolNumber() + "@ogrenci.edu.tr");
			preparedStatement.setString(7, student.getFacultyName());
			preparedStatement.setInt(8, fc.getFetch(student.getFacultyName()).getFacultyId());
			preparedStatement.setString(9, student.getDepartmentName());
			preparedStatement.setInt(10, dp.getFetch(student.getDepartmentName()).getId());
			preparedStatement.setString(11, String.valueOf(student.getBirth()));
			if (preparedStatement.executeUpdate() == 0)
				kontrol = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kontrol;
	}

	public boolean removeStudent(int id) {

		String query = "DELETE FROM students WHERE (`id` = '" + id + "')";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			if (state.executeUpdate(query) == 0) {
				kontrol = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kontrol;
	}

	public boolean updateStudent(int id, String name, String surname, Double score, String number, String tcNo,
			String mail, String faculty, String department, String birth) {

		String query = "UPDATE students SET first_name = ?, last_name = ?, score = ?, school_number = ?, tc_no = ?, student_mail = ?,"
				+ "faculty_name = ?, faculty_id = ?, department_name = ?, department_id = ?, birth = ? WHERE id = ?"; 
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, String.valueOf(score));
			preparedStatement.setString(4, number);
			preparedStatement.setString(5, tcNo);
			preparedStatement.setString(6, number + "@ogrenci.edu.tr");
			preparedStatement.setString(7, faculty);
			preparedStatement.setInt(8, fc.getFetch(faculty).getFacultyId());
			preparedStatement.setString(9, department);
			preparedStatement.setInt(10, dp.getFetch(department).getId());
			preparedStatement.setString(11, String.valueOf(birth));
			preparedStatement.setInt(12, id);
			if (preparedStatement.executeUpdate() == 0)
				kontrol = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kontrol;
	}
	
	
	public static Student getFetch(int studentId) {
		
		String query = "SELECT * FROM students WHERE id = " +"'" + studentId + "'";
		Student fc = new Student();
		try {
			state = conn.createStatement();
			rs = state.executeQuery(query);
			while(rs.next()) {
				fc.setId(rs.getInt("id"));
				fc.setFirst_name(rs.getString("first_name"));
				fc.setLast_name(rs.getString("last_name"));
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fc;
	}
	
	public ArrayList<Lesson> getStudentsLessons(Student student) {

		ArrayList<Lesson> list = new ArrayList<Lesson>();
		String query = "SELECT * FROM lesson_student WHERE student_id =" + student.getId();
		Lesson obj;
		try {

			state = conn.createStatement();
			rs = state.executeQuery(query);

			while (rs.next()) {
				obj = new Lesson(rs.getInt("lesson_id"), rs.getString("lesson_name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

}
