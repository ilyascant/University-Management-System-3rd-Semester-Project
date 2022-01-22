package Model;

import java.util.Date;

import Helper.DBConnection;

public class Person {

	private String first_name;
	private String last_name;
	private String identityNumber;
	private String password;
	private String birth;
	private int age;
	static DBConnection connection = new DBConnection();

	public Person() {
	}

	public Person(String first_name) {

		this.first_name = first_name;
	}

	private int id;

	public Person(int id, String first_name, String last_name, String identityNumber, String birth) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.identityNumber = identityNumber;
		this.password = identityNumber;
		this.birth = birth;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
