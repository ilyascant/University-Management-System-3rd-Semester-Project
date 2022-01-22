package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.*;
import Model.Instructor;
import Model.Manager;
import Model.Student;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.List;
import javax.swing.border.LineBorder;
import javax.swing.Icon;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_instructor_mail;
	private JPasswordField txt_instructor_password;
	private JTextField txt_manager_mail;
	private JPasswordField txt_manager_password;
	private JTextField txt_student_number;
	private JPasswordField txt_student_password;
	private DBConnection connection = new DBConnection();
	static Student st = new Student();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setTitle("Universite Yonetim Sistemi");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1052, 444);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u00DCniversite Y\u00F6netim Sistemi Giri\u015F");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 24));
		lblNewLabel_2.setBackground(new Color(255, 255, 224));
		lblNewLabel_2.setBounds(54, 237, 392, 30);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel(new ImageIcon(getClass().getResource("baibu.png")));
		lblNewLabel_1_1.setBounds(168, 111, 144, 128);
		contentPane.add(lblNewLabel_1_1);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(508, 66, 506, 256);
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane);
		
		JPanel student_login = new JPanel();
		student_login.setBorder(BorderFactory.createEmptyBorder());
		student_login.setBackground(Color.WHITE);
		tabbedPane.addTab("Öðrenci Giriþi", null, student_login, null);
		tabbedPane.setForegroundAt(0, Color.BLACK);
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		student_login.setLayout(null);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Number:");
		lblNewLabel_1_2_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_1.setBounds(77, 46, 99, 22);
		student_login.add(lblNewLabel_1_2_1);
		
		txt_student_number = new JTextField();
		txt_student_number.setColumns(10);
		txt_student_number.setBounds(186, 40, 239, 35);
		student_login.add(txt_student_number);
		
		txt_student_password = new JPasswordField();
		txt_student_password.setBounds(186, 86, 239, 38);
		student_login.add(txt_student_password);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("\u015Eifre:");
		lblNewLabel_1_1_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1.setBounds(77, 95, 69, 20);
		student_login.add(lblNewLabel_1_1_1_1);
		
		JButton btn_student_login = new JButton("Giri\u015F Yap");
		btn_student_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(txt_student_number.getText().length() == 0 || txt_student_password.getText().length() == 0) {
					Helper.showMessage("Lütfen tüm alanlarý doldurunuz!");
				}else {
					
					try {
						
						List<Student> filteredStudent = st.getAllStudents().stream().filter(f->f.getNumber().equals(txt_student_number.getText()) && f.getIdentityNumber().equals(txt_student_password.getText())).collect(Collectors.toList());
						Connection con = connection.connDb();
						Statement st = con.createStatement();
						ResultSet myRs = st.executeQuery("SELECT * FROM students");
						boolean kontrol = false;
						while(myRs.next()) {
							if(txt_student_number.getText().equals(myRs.getString("school_number")) && txt_student_password.getText().equals(myRs.getString("tc_no"))) {
								kontrol = true;
								Student student = filteredStudent.get(0);
								System.out.println("Hosgeldiniz " + student.getFirst_name().toUpperCase());
								StudentGUI studentGUI = new StudentGUI(student);
								studentGUI.setVisible(true);
								dispose();
							}
						}
						if(kontrol == false) {
							Helper.showMessage("Girdiðiniz bilgiler yanlýþtýr!");
							txt_manager_mail.setText("");
							txt_manager_password.setText("");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_student_login.setBackground(Color.WHITE);
		btn_student_login.setBounds(77, 147, 348, 47);
		student_login.add(btn_student_login);
		
		JPanel manager_login = new JPanel();
		manager_login.setBackground(Color.WHITE);
		tabbedPane.addTab("Yönetici Giriþ", null, manager_login, null);
		manager_login.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("E-Posta:");
		lblNewLabel_1_3.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(73, 45, 76, 22);
		manager_login.add(lblNewLabel_1_3);
		
		txt_manager_mail = new JTextField();
		txt_manager_mail.setColumns(10);
		txt_manager_mail.setBounds(182, 39, 239, 35);
		manager_login.add(txt_manager_mail);
		
		txt_manager_password = new JPasswordField();
		txt_manager_password.setBounds(182, 85, 239, 38);
		manager_login.add(txt_manager_password);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("\u015Eifre:");
		lblNewLabel_1_1_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_1_2.setBounds(73, 94, 69, 20);
		manager_login.add(lblNewLabel_1_1_2);
		
		JButton btn_manager_login = new JButton("Giri\u015F Yap");
		btn_manager_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(txt_manager_mail.getText().length() == 0 || txt_manager_password.getText().length() == 0) {
					Helper.showMessage("Lütfen tüm alanlarý doldurunuz!");
				}else {
					
					try {
						Connection con = connection.connDb();
						Statement st = con.createStatement();
						ResultSet myRs = st.executeQuery("SELECT * FROM admins");
						boolean kontrol = false;
						while(myRs.next()) {
							if(txt_manager_mail.getText().equals(myRs.getString("admin_mail")) && txt_manager_password.getText().equals(myRs.getString("password"))) {
								kontrol = true;
								Instructor instructor = new Instructor();
								instructor.setId(myRs.getInt("id"));
								instructor.setFirst_name(myRs.getString("first_name"));
								instructor.setLast_name(myRs.getString("last_name"));
								instructor.setMail(instructor.getFirst_name() + "." + instructor.getLast_name() + "@edu.tr");
								instructor.setIdentityNumber(myRs.getString("tc_no"));
								instructor.setPassword(myRs.getString("password"));
								System.out.println("Hoþgeldiniz " + instructor.getFirst_name());
								ManagerGUI managerGUI = new ManagerGUI(instructor);
								managerGUI.setVisible(true);
								dispose();
							}
						}
						if(kontrol == false) {
							Helper.showMessage("Girdiðiniz bilgiler yanlýþtýr!");
							txt_manager_mail.setText("");
							txt_manager_password.setText("");
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_manager_login.setBackground(Color.WHITE);
		btn_manager_login.setBounds(73, 147, 348, 45);
		manager_login.add(btn_manager_login);
		
//		JPanel instructor_login = new JPanel();
//		instructor_login.setBackground(Color.WHITE);
//		tabbedPane.addTab("Eðitmen Giriþ", null, instructor_login, null);
//		tabbedPane.setEnabledAt(2, false);
//		instructor_login.setLayout(null);
//		
//		JLabel lblNewLabel_1_2 = new JLabel("E-Posta:");
//		lblNewLabel_1_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
//		lblNewLabel_1_2.setBounds(71, 43, 76, 22);
//		instructor_login.add(lblNewLabel_1_2);
//		
//		txt_instructor_mail = new JTextField();
//		txt_instructor_mail.setColumns(10);
//		txt_instructor_mail.setBounds(180, 37, 239, 35);
//		instructor_login.add(txt_instructor_mail);
//		
//		txt_instructor_password = new JPasswordField();
//		txt_instructor_password.setBounds(180, 83, 239, 38);
//		instructor_login.add(txt_instructor_password);
//		
//		JLabel lblNewLabel_1_1_1 = new JLabel("\u015Eifre:");
//		lblNewLabel_1_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
//		lblNewLabel_1_1_1.setBounds(71, 92, 69, 20);
//		instructor_login.add(lblNewLabel_1_1_1);
//		
//		JButton btn_instructor_login = new JButton("Giri\u015F Yap");
//		btn_instructor_login.setBackground(Color.WHITE);
//		btn_instructor_login.setBounds(71, 144, 348, 47);
//		instructor_login.add(btn_instructor_login);
	}
}
