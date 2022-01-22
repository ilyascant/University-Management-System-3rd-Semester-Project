package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.*;

public class Lesson {

	private int lessonId;
	private String lessonName;
	
	static DBConnection connection = new DBConnection();
	static Connection conn = connection.connDb();
	static Statement state = null;
	static ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Lesson() {}
	
	public Lesson(int lessonId, String lessonName) {
		super();
		this.lessonId = lessonId;
		this.lessonName = lessonName;
	}

	public int getLessonId() {
		return lessonId;
	}

	public void setLessonId(int lessonId) {
		this.lessonId = lessonId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
	public ArrayList<Lesson> getAllLessons(){
		
		
		ArrayList<Lesson> list = new ArrayList<Lesson>();
		Lesson obj;
		try {
			
			state = conn.createStatement();
			rs = state.executeQuery("SELECT * FROM lessons");
			
			while(rs.next()) {
				obj = new Lesson(rs.getInt("id"), rs.getString("lesson_name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	
	public Lesson getFetch(int id) {
		
		String query = "SELECT * FROM lessons WHERE id = " + id;
		Lesson fc = new Lesson();
		try {
			state = conn.createStatement();
			rs = state.executeQuery(query);
			while(rs.next()) {
				fc.setLessonId(rs.getInt("id"));
				fc.setLessonName(rs.getString("lesson_name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fc;
	}
	
	public Lesson getFetch(String name) {
		
		String query = "SELECT * FROM lessons WHERE lesson_name = " + name;
		Lesson fc = new Lesson();
		try {
			state = conn.createStatement();
			rs = state.executeQuery(query);
			while(rs.next()) {
				fc.setLessonId(rs.getInt("id"));
				fc.setLessonName(rs.getString("lesson_name"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fc;
	}
	
	public boolean addLesson(String lessonName) {
		
		String query = "INSERT INTO lessons (lesson_name) VALUES (?)";
		boolean kontrol = true;
		try {
			state = conn.createStatement();
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, lessonName);

			if(preparedStatement.executeUpdate() == 0)      //Sorguyu çalýþtýrýr
				kontrol = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return kontrol;
	}
	
	public boolean updateLesson(int id, String name) {
		
		String query = "UPDATE lessons SET lesson_name = ? WHERE id = ?";  
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
	
	public boolean removeLesson(int id) {
		
		String query = "DELETE FROM lessons WHERE (`id` = '" + id +"')";
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
