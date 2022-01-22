package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class LessonStudent {

	private int id;
	private int studentId;
	private String studentFirstName;
	private String studentLastName;
	private int lessonId;
	private String lessonName;
	private Lesson lesson = new Lesson();

	static DBConnection connection = new DBConnection();
	static Connection conn = connection.connDb();
	static Statement state = null;
	static ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public LessonStudent() {}
	
	public LessonStudent(int id, int studentId, int lessonId) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.lessonId = lessonId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}
	
	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public ArrayList<LessonStudent> getAllLessonStudents(){
		
		
		ArrayList<LessonStudent> list = new ArrayList<LessonStudent>();
		LessonStudent obj;
		try {
			
			state = conn.createStatement();
			rs = state.executeQuery("SELECT * FROM lesson_student");
			
			while(rs.next()) {
				obj = new LessonStudent(rs.getInt("id"), rs.getInt("student_id"), rs.getInt("lesson_id"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	public boolean addLessonStudent(int lessonId, String studentFirstName, String studentLastName, int studentId) {
		
		String query = "INSERT INTO lesson_student (student_id,student_first_name,student_last_name,lesson_id,lesson_name) VALUES (?,?,?,?,?)";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, studentId);
			preparedStatement.setString(2, studentFirstName);
			preparedStatement.setString(3, studentLastName);
			preparedStatement.setInt(4, lessonId);
			preparedStatement.setString(5, lesson.getFetch(lessonId).getLessonName());

			if(preparedStatement.executeUpdate() == 0)      //Sorguyu çalýþtýrýr
				kontrol = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kontrol;
	}
	
	
	
	
}
