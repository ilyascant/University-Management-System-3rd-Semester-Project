package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.tools.DiagnosticCollector;

import com.toedter.calendar.JDateChooser;

import Helper.*;
import Model.Department;
import Model.Faculty;
import Model.Instructor;
import Model.Lesson;
import Model.LessonStudent;
import Model.Manager;
import Model.Student;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class ManagerGUI extends JFrame {

	private JPanel contentPane;
	static Instructor instructor2 = new Instructor();
	static Faculty faculty = new Faculty();
	static Student student = new Student();
	static Department department = new Department();
	static Lesson lesson = new Lesson();
	private JTextField txt_add_instructor_first_name;
	private JTextField txt_add_instructor_last_name;
	private JTextField txt_add_instructor_identity;
	private JTextField txt_add_instructor_degree;
	private JTextField txt_add_instructor_faculty;
	private JTextField txt_add_instructor_department;
	private JTextField txt_remove_instructor_id;
	private JTable table_instructor;
	private DefaultTableModel instructorModel = null;
	private DefaultComboBoxModel dm;
	private Object[] instructorData = null;
	private JTable table_faculty;
	private JTextField txt_faculty_name;
	private JTable table_department;
	private JScrollPane scroll_department;
	private JScrollPane scroll_lesson;
	private DefaultTableModel facultyModel = null;
	private Object[] facultyData = null;
	private JPopupMenu facultyPopup;
	private JPopupMenu departmentPopup;
	private JPopupMenu lessonPopup;
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;
	private DefaultTableModel departmentModel = null;
	private DefaultTableModel lessonModel = null;
	private DefaultTableModel lessonStudentModel = null;
	private Object[] departmentData = null;
	private Object[] lessonData = null;
	private Object[] lessonStudentData = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table_students;
	private JTextField txt_add_student_first_name;
	private JTextField txt_add_student_last_name;
	private JTextField txt_add_student_tc;
	private JTextField txt_add_student_faculty;
	private JTextField txt_add_student_department;
	private JTextField txt_remove_student_id;
	private JTextField txt_add_student_score;
	private JComboBox combo_department;
	JPanel panel_faculty;
	public String selectedFacultyTableId = "0";
	public String selectedLessonTableId = "0";
	public String selectedFacultyTableName = null;
	public String selectedLessonTableName = null;
	private Dictionary dictionary;
	private int comboKey = 0;
	private String comboValue;
	private int size;
	private JTextField txt_add_department;
	private JTextField txt_add_lesson;
	private JTextField txt_student_id;
	private JTable table_lesson;
	private JTable table_1;
	private JTable table_student;
	private JTable table_lesson_student;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerGUI frame = new ManagerGUI(instructor2);
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
	public ManagerGUI(Instructor instructor) {
		setResizable(false);

		// ********** INSTRUCTOR Model **********
		instructorModel = new DefaultTableModel();
		Object[] colInstructor = new Object[8];
		colInstructor[0] = "ID";
		colInstructor[1] = "Ad";
		colInstructor[2] = "SOYAD";
		colInstructor[3] = "UNVAN";
		colInstructor[4] = "TC NO";
		colInstructor[5] = "MAIL";
		colInstructor[6] = "FAKULTE";
		colInstructor[7] = "BOLUM";
		instructorModel.setColumnIdentifiers(colInstructor);
		instructorData = new Object[8];
		instructor.getAllInstructors().forEach(e -> {
			instructorData[0] = e.getId();
			instructorData[1] = e.getFirst_name();
			instructorData[2] = e.getLast_name();
			instructorData[3] = e.getDegree();
			instructorData[4] = e.getIdentityNumber();
			instructorData[5] = e.getMail();
			instructorData[6] = e.getFacultyName();
			instructorData[7] = e.getDepartmentName();
			instructorModel.addRow(instructorData);
		});

		// ********** FACULTY Model **********
		facultyModel = new DefaultTableModel();
		Object[] colFaculty = new Object[2];
		colFaculty[0] = "ID";
		colFaculty[1] = "FAKULTE AD";
		facultyModel.setColumnIdentifiers(colFaculty);
		facultyData = new Object[2];
		faculty.getAllFaculties().forEach(e -> {
			facultyData[0] = e.getFacultyId();
			facultyData[1] = e.getFacultyName();
			facultyModel.addRow(facultyData);
		});

		// ********** DEPARTMENT MODEL **********
		departmentModel = new DefaultTableModel();
		Object[] colDepartment = new Object[3];
		colDepartment[0] = "ID";
		colDepartment[1] = "BOLUM AD";
		colDepartment[2] = "FAKULTE AD";
		departmentModel.setColumnIdentifiers(colDepartment);
		departmentData = new Object[3];
		department.getAllDepartments().forEach(e -> {
			departmentData[0] = e.getId();
			departmentData[1] = e.getDepartmentName();
			departmentData[2] = e.getFaculty().getFacultyName();
			departmentModel.addRow(departmentData);
		});

		// ********** LESSON MODEL **********
		lessonModel = new DefaultTableModel();
		Object[] colLesson = new Object[2];
		colLesson[0] = "ID";
		colLesson[1] = "DERS AD";
		lessonModel.setColumnIdentifiers(colLesson);
		lessonData = new Object[2];
		lesson.getAllLessons().forEach(e -> {
			lessonData[0] = e.getLessonId();
			lessonData[1] = e.getLessonName();
			lessonModel.addRow(lessonData);
		});

		// ********** LESSON STUDENT MODEL **********
		lessonStudentModel = new DefaultTableModel();
		Object[] colLessonStudent = new Object[7];
		colLessonStudent[0] = "ID";
		colLessonStudent[1] = "AD";
		colLessonStudent[2] = "SOYAD";
		colLessonStudent[3] = "OKUL NO";
		colLessonStudent[4] = "MAIL";
		colLessonStudent[5] = "FAKULTE";
		colLessonStudent[6] = "BOLUM";
		lessonStudentModel.setColumnIdentifiers(colLessonStudent);
		lessonStudentData = new Object[7];
		student.getAllStudents().forEach(e -> {
			lessonStudentData[0] = e.getId();
			lessonStudentData[1] = e.getFirst_name();
			lessonStudentData[2] = e.getLast_name();
			lessonStudentData[3] = e.getSchoolNumber();
			lessonStudentData[4] = e.getMail();
			lessonStudentData[5] = e.getFacultyName();
			lessonStudentData[6] = e.getDepartmentName();
			lessonStudentModel.addRow(lessonStudentData);
		});

		// ********** STUDENT MODEL **********

		studentModel = new DefaultTableModel();
		Object[] colStudent = new Object[10];
		colStudent[0] = "ID";
		colStudent[1] = "Ad";
		colStudent[2] = "SOYAD";
		colStudent[3] = "SKOR";
		colStudent[4] = "NUMARA";
		colStudent[5] = "TC NO";
		colStudent[6] = "MAIL";
		colStudent[7] = "FAKULTE";
		colStudent[8] = "BOLUM";
		colStudent[9] = "DOGUM TARIHI";
		studentModel.setColumnIdentifiers(colStudent);
		studentData = new Object[10];
		student.getAllStudents().forEach(e -> {
			studentData[0] = e.getId();
			studentData[1] = e.getFirst_name();
			studentData[2] = e.getLast_name();
			studentData[3] = e.getScore();
			studentData[4] = e.getNumber();
			studentData[5] = e.getIdentityNumber();
			studentData[6] = e.getMail();
			studentData[7] = e.getFacultyName();
			studentData[8] = e.getDepartmentName();
			studentData[9] = e.getBirth();
			studentModel.addRow(studentData);
		});

		setTitle("Yönetici Paneli");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1504, 933);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hoþ Geldiniz, " + instructor.getFirst_name().toUpperCase() + " "
				+ instructor.getLast_name().toUpperCase());
		lblNewLabel.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel.setBounds(23, 32, 403, 25);
		contentPane.add(lblNewLabel);

		JButton btn_manager_logout = new JButton("Çýkýþ Yap");
		btn_manager_logout.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_manager_logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();

			}
		});
		btn_manager_logout.setBackground(new Color(255, 192, 203));
		btn_manager_logout.setBounds(1348, 33, 111, 40);
		contentPane.add(btn_manager_logout);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 98, 1468, 785);
		contentPane.add(tabbedPane);

		// Eðitmen Yönetimi
		JPanel panel_instructor = new JPanel();
		panel_instructor.setBackground(Color.WHITE);
		tabbedPane.addTab("Eðitmen Yönetimi", null, panel_instructor, null);
		panel_instructor.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ad");
		lblNewLabel_1.setBounds(1119, 51, 46, 14);
		lblNewLabel_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_instructor.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Soyad");
		lblNewLabel_1_1.setBounds(1293, 47, 55, 23);
		lblNewLabel_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_instructor.add(lblNewLabel_1_1);

		txt_add_instructor_first_name = new JTextField();
		txt_add_instructor_first_name.setBounds(1119, 72, 144, 27);
		panel_instructor.add(txt_add_instructor_first_name);
		txt_add_instructor_first_name.setColumns(10);

		txt_add_instructor_last_name = new JTextField();
		txt_add_instructor_last_name.setBounds(1293, 72, 144, 27);
		txt_add_instructor_last_name.setColumns(10);
		panel_instructor.add(txt_add_instructor_last_name);

		JLabel lblNewLabel_1_2 = new JLabel("TC NO");
		lblNewLabel_1_2.setBounds(1119, 139, 59, 23);
		lblNewLabel_1_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_instructor.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("Unvan");
		lblNewLabel_1_2_1.setBounds(1293, 139, 59, 23);
		lblNewLabel_1_2_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_instructor.add(lblNewLabel_1_2_1);

		txt_add_instructor_identity = new JTextField();
		txt_add_instructor_identity.setBounds(1119, 163, 144, 27);
		txt_add_instructor_identity.setColumns(10);
		panel_instructor.add(txt_add_instructor_identity);

		txt_add_instructor_degree = new JTextField();
		txt_add_instructor_degree.setBounds(1293, 163, 144, 27);
		txt_add_instructor_degree.setColumns(10);
		panel_instructor.add(txt_add_instructor_degree);

		JLabel lblNewLabel_1_2_2 = new JLabel("Fak\u00FClte");
		lblNewLabel_1_2_2.setBounds(1119, 233, 68, 23);
		lblNewLabel_1_2_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_instructor.add(lblNewLabel_1_2_2);

		JLabel lblNewLabel_1_2_3 = new JLabel("B\u00F6l\u00FCm");
		lblNewLabel_1_2_3.setBounds(1293, 233, 61, 23);
		lblNewLabel_1_2_3.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_instructor.add(lblNewLabel_1_2_3);

		txt_add_instructor_faculty = new JTextField();
		txt_add_instructor_faculty.setBounds(1119, 258, 144, 27);
		txt_add_instructor_faculty.setColumns(10);
		panel_instructor.add(txt_add_instructor_faculty);

		txt_add_instructor_department = new JTextField();
		txt_add_instructor_department.setBounds(1293, 258, 144, 27);
		txt_add_instructor_department.setColumns(10);
		panel_instructor.add(txt_add_instructor_department);

		JButton btn_add_instructor = new JButton("Ekle");
		btn_add_instructor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txt_add_instructor_first_name.getText().length() == 0
						|| txt_add_instructor_last_name.getText().length() == 0
						|| txt_add_instructor_identity.getText().length() == 0
						|| txt_add_instructor_identity.getText().length() < 10
						|| txt_add_instructor_degree.getText().length() == 0
						|| txt_add_instructor_faculty.getText().length() == 0
						|| txt_add_instructor_department.getText().length() == 0) {
					Helper.showMessage("Lütfen tüm alanlarý doldurunuz.");
				} else {
					instructor.setFirst_name(txt_add_instructor_first_name.getText());
					instructor.setLast_name(txt_add_instructor_last_name.getText());
					instructor.setIdentityNumber(txt_add_instructor_identity.getText());
					instructor.setDegree(txt_add_instructor_degree.getText());
					instructor.setFacultyName(txt_add_instructor_faculty.getText());
					instructor.setDepartmentName(txt_add_instructor_department.getText());
					if (instructor.addInstructor(instructor)) {
						updateInstructorModel();
						Helper.showMessage("Eðitmen sisteme eklendi.");
					} else
						Helper.showMessage("Eðitmen sisteme eklenirken hata oluþtu!");

					txt_add_instructor_first_name.setText("");
					txt_add_instructor_last_name.setText("");
					txt_add_instructor_identity.setText("");
					txt_add_instructor_degree.setText("");
					txt_add_instructor_faculty.setText("");
					txt_add_instructor_department.setText("");
				}

			}
		});
		btn_add_instructor.setBounds(1217, 350, 122, 40);
		btn_add_instructor.setBackground(new Color(245, 245, 245));
		btn_add_instructor.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_instructor.add(btn_add_instructor);

		JLabel lblNewLabel_2 = new JLabel("E\u011Fitmen \u0130d");
		lblNewLabel_2.setBounds(1119, 526, 101, 23);
		lblNewLabel_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_instructor.add(lblNewLabel_2);

		txt_remove_instructor_id = new JTextField();
		txt_remove_instructor_id.setBounds(1119, 559, 318, 27);
		txt_remove_instructor_id.setColumns(10);
		panel_instructor.add(txt_remove_instructor_id);

		JButton btn_remove_instructor = new JButton("Sil");
		btn_remove_instructor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txt_remove_instructor_id.getText().length() == 0)
					Helper.showMessage("Lütfen silmek istediðiniz eðitmenin tablodaki id numarasýný giriniz.");
				else {
					if (Helper.showWarning("Eðitmeni silmek istediðinizden emin misiniz?") == 0) {
						if (instructor.removeInstructor(Integer.parseInt(txt_remove_instructor_id.getText()))) {
							updateInstructorModel();
							Helper.showMessage("Eðitmen silindi.");
						} else
							Helper.showMessage("böyle bir eðitmen bulunmuyor!");

						txt_remove_instructor_id.setText("");
					}
				}

			}
		});
		btn_remove_instructor.setBounds(1217, 638, 122, 40);
		btn_remove_instructor.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		btn_remove_instructor.setBackground(new Color(245, 245, 245));
		panel_instructor.add(btn_remove_instructor);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 51, 983, 627);
		panel_instructor.add(scrollPane);

		table_instructor = new JTable(instructorModel);
		table_instructor.setBackground(Color.WHITE);
		scrollPane.setViewportView(table_instructor);

		TableRowSorter studentSorter = new TableRowSorter(studentModel);
		table_student = new JTable(studentModel);
		table_student.setRowSorter(studentSorter);
		table_student.setBackground(Color.WHITE);

		// Tabloda seçilen satýrý textbox'a iþler
		table_instructor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					txt_remove_instructor_id
							.setText(table_instructor.getValueAt(table_instructor.getSelectedRow(), 0).toString());

				} catch (Exception ex) {

				}

			}
		});

		// UPDATE INSTRUCTOR TABLE'S VALUES
		table_instructor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer
							.parseInt(table_instructor.getValueAt(table_instructor.getSelectedRow(), 0).toString());
					String selectName = table_instructor.getValueAt(table_instructor.getSelectedRow(), 1).toString();
					String selectSurname = table_instructor.getValueAt(table_instructor.getSelectedRow(), 2).toString();
					String selectDegree = table_instructor.getValueAt(table_instructor.getSelectedRow(), 3).toString();
					String selectTcNo = table_instructor.getValueAt(table_instructor.getSelectedRow(), 4).toString();
					String selectMail = table_instructor.getValueAt(table_instructor.getSelectedRow(), 5).toString();
					String selectFaculty = table_instructor.getValueAt(table_instructor.getSelectedRow(), 6).toString();
					String selectDepartment = table_instructor.getValueAt(table_instructor.getSelectedRow(), 7)
							.toString();

					if (instructor.updateInstructor(selectID, selectName, selectSurname, selectDegree, selectTcNo,
							selectMail, selectFaculty, selectDepartment)) {
						Helper.showMessage("Eðitmen bilgileri güncellendi.");
					}

				}

			}
		});

		// ********** Fakülte Yönetimi **********\\
		panel_faculty = new JPanel();
		panel_faculty.setBackground(Color.WHITE);
		tabbedPane.addTab("Fakülte Yönetimi", null, panel_faculty, null);
		panel_faculty.setLayout(null);

		JScrollPane scroll_faculty = new JScrollPane();
		scroll_faculty.setBounds(40, 50, 513, 647);
		panel_faculty.add(scroll_faculty);

		// POPUP MENU
		facultyPopup = new JPopupMenu();
		JMenuItem updateFaculty = new JMenuItem("Güncelle");
		JMenuItem removeFaculty = new JMenuItem("Sil");
		facultyPopup.add(updateFaculty);
		facultyPopup.add(removeFaculty);

		updateFaculty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer
						.parseInt(table_faculty.getValueAt(table_faculty.getSelectedRow(), 0).toString());
				Faculty selectedFaculty = faculty.getFetch(selectedID);
				FacultyUpdateGUI uGUI = new FacultyUpdateGUI(selectedFaculty);
				uGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				uGUI.setVisible(true);
				uGUI.addWindowListener(new WindowAdapter() { // Açýlan pencereyi dinliyoruz

					@Override
					public void windowClosed(WindowEvent e) { // Açýlan pencere kapatýldýðýnda db'de veri güncellendiði
																// için tablomuzu da güncelliyoruz.
						updateDepartmentTable();
						updateFacultyModel();

					}

				});

			}
		});

		removeFaculty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer
						.parseInt(table_faculty.getValueAt(table_faculty.getSelectedRow(), 0).toString());
				if (Helper.showWarning("Fakülteyi silmek istediðinizden emin misiniz?") == 0) {
					if (faculty.removFaculty(selectedID)) {
						Helper.showMessage("Ýþlem baþarýlý!");

						updateDepartmentTable();
						updateFacultyModel();
					} else
						Helper.showMessage("Hata oluþtu!");
				}

			}
		});

		table_faculty = new JTable(facultyModel);
		table_faculty.setComponentPopupMenu(facultyPopup); // Yukardaki popup menüyü tabloya atadýk.
		table_faculty.addMouseListener(new MouseAdapter() { // sað týkladýðýmýzda hangi satýrda olduðumuzu bulduk

			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_faculty.rowAtPoint(point);
				table_faculty.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		scroll_faculty.setViewportView(table_faculty);

		JLabel lblNewLabel_1_3 = new JLabel("Fakülte Ad");
		lblNewLabel_1_3.setBounds(604, 50, 98, 23);
		lblNewLabel_1_3.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_faculty.add(lblNewLabel_1_3);

		txt_faculty_name = new JTextField();
		txt_faculty_name.setBounds(604, 80, 261, 27);
		txt_faculty_name.setColumns(10);
		panel_faculty.add(txt_faculty_name);

		JButton btn_add_faculty = new JButton("Ekle");
		btn_add_faculty.setBounds(604, 124, 71, 31);
		btn_add_faculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_faculty_name.getText().length() == 0) {
					Helper.showMessage("Lütfen tüm alanlarý doldurunuz!");
				} else {
					if (faculty.addFaculty(txt_faculty_name.getText())) {
						updateFacultyModel();
						updateDepartmentTable();
						Helper.showMessage("Fakülte sisteme eklendi.");
					} else
						Helper.showMessage("Fakülte sisteme eklenemedi, tekrar deneyiniz.");

					txt_faculty_name.setText("");
				}

			}
		});
		btn_add_faculty.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_add_faculty.setBackground(new Color(245, 245, 245));
		panel_faculty.add(btn_add_faculty);

		// DEPARTMENT POPUP
		departmentPopup = new JPopupMenu();
		JMenuItem updateDepartment = new JMenuItem("Güncelle");
		JMenuItem removeDepartment = new JMenuItem("Sil");
		departmentPopup.add(updateDepartment);
		departmentPopup.add(removeDepartment);

		updateDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer
						.parseInt(table_department.getValueAt(table_department.getSelectedRow(), 0).toString());
				Department selectedDepartment = department.getFetch(selectedID);
				DepartmentUpdateGUI dGUI = new DepartmentUpdateGUI(selectedDepartment);
				dGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dGUI.setVisible(true);
				dGUI.addWindowListener(new WindowAdapter() { // Açýlan pencereyi dinliyoruz

					@Override
					public void windowClosed(WindowEvent e) { // Açýlan pencere kapatýldýðýnda db'de veri güncellendiði
																// için tablomuzu da güncelliyoruz.
						updateDepartmentTable();
						updateFacultyModel();

					}

				});

			}
		});

		removeDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer
						.parseInt(table_department.getValueAt(table_department.getSelectedRow(), 0).toString());
				if (Helper.showWarning("Departmani silmek istediginizden emin misiniz?") == 0) {
					if (department.removeDepartment(selectedID)) {
						Helper.showMessage("Islem basarili!");

						updateDepartmentTable();
						updateFacultyModel();
					} else
						Helper.showMessage("Hata olustu!");
				}

			}
		});

		scroll_department = new JScrollPane();
		scroll_department.setBounds(915, 50, 513, 647);
		panel_faculty.add(scroll_department);

		// sag tablo bolum listesi
		table_department = new JTable(departmentModel);
		table_department.setComponentPopupMenu(departmentPopup); // Yukardaki popup menüyü tabloya atadýk.
		table_department.addMouseListener(new MouseAdapter() { // sag tikladigimizda hangi satýrda oldugumuzu bulduk

			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_department.rowAtPoint(point);
				table_department.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		scroll_department.setViewportView(table_department);

		table_faculty.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {

					selectedFacultyTableId = (table_faculty.getValueAt(table_faculty.getSelectedRow(), 0).toString()); // Secilen
																														// fakultenin
																														// idsini
																														// bulur
					selectedFacultyTableName = table_faculty.getValueAt(table_faculty.getSelectedRow(), 1).toString(); // Secilen
																														// fakultenin
																														// fakulte
																														// adini
																														// bulur
					// System.out.println(selectedFacultyTableId);
					updateDepartmentTable();

				} catch (Exception ex) {

				}

			}
		});

		// Combo menu
		combo_department = new JComboBox<>();
		combo_department.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		combo_department.setBounds(604, 491, 261, 40);
		department.getAllDepartments().forEach(e -> {
			Department dep = e;
			if (dep.getFaculty().getFacultyName() == null) // bolume fakülte atanmadýysa combo menude bolum gozukur.
				combo_department.addItem(new Dictionary(dep.getId(), dep.getDepartmentName()));
		});

		combo_department.addActionListener(e -> { // kombo menuden secim yapildiginda gerceklesecek eylem

			Department dep = new Department();
			JComboBox c = (JComboBox) e.getSource();
			dictionary = (Dictionary) c.getSelectedItem();
			System.out.println(dictionary.getKey() + " " + dictionary.getValue());
			comboKey = dictionary.getKey();
			comboValue = dictionary.getValue();

		});
		panel_faculty.add(combo_department);

		// Seçilen fakülteye seçilen departman atanýyor
		JButton btn_set_department_to_faculty = new JButton("Fakulteye Ata");
		btn_set_department_to_faculty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selectedFacultyTableName == null || comboKey == 0) {
					Helper.showMessage("Fakülte ve bölüm seçmeniz gerekli!");
				} else {
					Department dp = new Department();
					Faculty fc = new Faculty();
					dp = dp.getFetch(comboKey);
					dp.setFaculty(fc.getFetch(selectedFacultyTableName));
					if (dp.updateDepartment(dp)) {
						Helper.showMessage("Bilgiler güncellendi!");
						// combo_department.remove(combo_department.getSelectedIndex());

					} else
						Helper.showMessage("Hata oluþtu!");
					updateDepartmentTable();
					departmentPopup();
				}

			}
		});
		btn_set_department_to_faculty.setBounds(604, 555, 157, 31);
		btn_set_department_to_faculty.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_set_department_to_faculty.setBackground(new Color(245, 245, 245));
		panel_faculty.add(btn_set_department_to_faculty);

		JButton btn_list_departments = new JButton("Bolumleri Listele");
		btn_list_departments.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedFacultyTableId = "0";
				updateDepartmentTable();
				departmentPopup();
			}
		});

		btn_list_departments.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_list_departments.setBackground(new Color(245, 245, 245));
		btn_list_departments.setBounds(604, 657, 185, 31);
		panel_faculty.add(btn_list_departments);

		JLabel lblNewLabel_1_3_1 = new JLabel("Atanabilir Bolumler");
		lblNewLabel_1_3_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(604, 458, 177, 23);
		panel_faculty.add(lblNewLabel_1_3_1);

		JLabel lblNewLabel_1_3_2 = new JLabel("Bolum Ad");
		lblNewLabel_1_3_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_3_2.setBounds(604, 293, 91, 23);
		panel_faculty.add(lblNewLabel_1_3_2);

		txt_add_department = new JTextField();
		txt_add_department.setColumns(10);
		txt_add_department.setBounds(604, 323, 261, 27);
		panel_faculty.add(txt_add_department);

		JButton btn_add_department = new JButton("Ekle");
		btn_add_department.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txt_add_department.getText().length() == 0) {
					Helper.showMessage("Lütfen eklemek istediðiniz bolumun adini giriniz!");
				} else {
					Department dep = new Department();
					boolean kontrol = dep.addDepartment(txt_add_department.getText());
					if (kontrol) {
						dep = dep.getAllDepartments().get(dep.getAllDepartments().size() - 1);
						Helper.showMessage("Bolum sisteme eklendi!");
						txt_add_department.setText("");
						combo_department.addItem(new Dictionary(dep.getId(), dep.getDepartmentName()));
						updateDepartmentTable();
					} else {
						Helper.showMessage("Bolum sisteme eklenemedi!");
					}

				}
				departmentPopup();
			}
		});
		btn_add_department.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_add_department.setBackground(new Color(245, 245, 245));
		btn_add_department.setBounds(604, 367, 71, 31);
		panel_faculty.add(btn_add_department);

		// *********** Ders Yönetimi ***********\\
		JPanel panel_lesson = new JPanel();
		panel_lesson.setBackground(Color.WHITE);
		tabbedPane.addTab("Ders Yonetimi", null, panel_lesson, null);
		panel_lesson.setLayout(null);

		scroll_lesson = new JScrollPane();
		scroll_lesson.setBounds(35, 45, 513, 647);
		panel_lesson.add(scroll_lesson);

		JLabel lblNewLabel_1_3_3 = new JLabel("Ders Ad");
		lblNewLabel_1_3_3.setBounds(599, 45, 95, 22);
		lblNewLabel_1_3_3.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_lesson.add(lblNewLabel_1_3_3);

		txt_add_lesson = new JTextField();
		txt_add_lesson.setBounds(599, 75, 261, 27);
		txt_add_lesson.setColumns(10);
		panel_lesson.add(txt_add_lesson);

		JButton btn_add_lesson = new JButton("Ekle");
		btn_add_lesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txt_add_lesson.getText().length() == 0) {
					Helper.showMessage("Ders eklemek icin dersin adini girmelisiniz!");
				} else {
					if (lesson.addLesson(txt_add_lesson.getText())) {
						Helper.showMessage("Ders sisteme eklendi!");
						updateLessonTable();
					} else {
						Helper.showMessage("Hata oluþtu!");
					}
					txt_add_lesson.setText("");

				}

			}
		});
		btn_add_lesson.setBounds(599, 119, 261, 40);
		btn_add_lesson.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_add_lesson.setBackground(new Color(245, 245, 245));
		panel_lesson.add(btn_add_lesson);

		// LESSON POPUP
		lessonPopup = new JPopupMenu();
		JMenuItem updateLesson = new JMenuItem("Güncelle");
		JMenuItem removeLesson = new JMenuItem("Sil");
		lessonPopup.add(updateLesson);
		lessonPopup.add(removeLesson);

		updateLesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer.parseInt(table_lesson.getValueAt(table_lesson.getSelectedRow(), 0).toString());
				Lesson selectedLesson = lesson.getFetch(selectedID);
				LessonUpdateGUI dGUI = new LessonUpdateGUI(selectedLesson);
				dGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dGUI.setVisible(true);
				dGUI.addWindowListener(new WindowAdapter() { // Açýlan pencereyi dinliyoruz

					@Override
					public void windowClosed(WindowEvent e) { // Açýlan pencere kapatýldýðýnda db'de veri güncellendiði
																// için tablomuzu da güncelliyoruz.
						updateLessonTable();

					}

				});

			}
		});

		removeLesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer.parseInt(table_lesson.getValueAt(table_lesson.getSelectedRow(), 0).toString());
				if (Helper.showWarning("Dersi silmek istediginizden emin misiniz?") == 0) {
					if (lesson.removeLesson(selectedID)) {
						Helper.showMessage("Islem basarili!");

						updateLessonTable();
					} else
						Helper.showMessage("Hata olustu!");
				}

			}
		});

		table_lesson = new JTable(lessonModel);
		table_lesson.setComponentPopupMenu(lessonPopup); // Yukardaki popup menüyü tabloya atadýk.
		table_lesson.addMouseListener(new MouseAdapter() { // sag tikladigimizda hangi satýrda oldugumuzu bulduk

			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_lesson.rowAtPoint(point);
				table_lesson.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		scroll_lesson.setViewportView(table_lesson);

		table_lesson.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {

					selectedLessonTableId = (table_lesson.getValueAt(table_lesson.getSelectedRow(), 0).toString()); // Secilen
																													// dersin
																													// idsini
																													// bulur
					selectedLessonTableName = table_lesson.getValueAt(table_lesson.getSelectedRow(), 1).toString(); // Secilen
																													// dersin
																													// fakulte
																													// adini
																													// bulur
					// System.out.println(selectedFacultyTableId);
					updateDepartmentTable();

				} catch (Exception ex) {

				}

			}
		});

		JButton btn_set_student_to_lesson = new JButton("Derse Ata");
		btn_set_student_to_lesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (selectedLessonTableName == null || txt_student_id.getText().length() == 0) {
					Helper.showMessage(
							"Lutfen bir ders seciniz ve ardýndan atamak istediginiz ogrencinin id numarasini giriniz!");
				} else {
					LessonStudent ls = new LessonStudent();
					if (ls.addLessonStudent(Integer.parseInt(selectedLessonTableId),
							Student.getFetch(Integer.parseInt(txt_student_id.getText())).getFirst_name(),
							Student.getFetch(Integer.parseInt(txt_student_id.getText())).getLast_name(),
							Integer.parseInt(txt_student_id.getText()))) {
						System.out.println(Student.getFetch(Integer.parseInt(txt_student_id.getText())).getLast_name());
						Helper.showMessage("Ogrenci Derse atandi.");
						updateLessonStudentTable();
					} else {
						Helper.showMessage("Hata olustu!");
					}
				}

			}
		});
		btn_set_student_to_lesson.setBounds(599, 550, 261, 40);
		btn_set_student_to_lesson.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_set_student_to_lesson.setBackground(new Color(245, 245, 245));
		panel_lesson.add(btn_set_student_to_lesson);

		JButton btn_list_students_lesson_pane = new JButton("Ogrencileri Listele");
		btn_list_students_lesson_pane.setBounds(599, 652, 261, 40);
		btn_list_students_lesson_pane.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_list_students_lesson_pane.setBackground(new Color(245, 245, 245));
		panel_lesson.add(btn_list_students_lesson_pane);

		JLabel lblNewLabel_1_3_1_1 = new JLabel("Ogrenci ID");
		lblNewLabel_1_3_1_1.setBounds(599, 453, 173, 22);
		lblNewLabel_1_3_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		panel_lesson.add(lblNewLabel_1_3_1_1);

		JScrollPane scroll_lesson_student = new JScrollPane();
		scroll_lesson_student.setBounds(913, 45, 513, 647);
		panel_lesson.add(scroll_lesson_student);

		table_lesson_student = new JTable(lessonStudentModel);
		scroll_lesson_student.setViewportView(table_lesson_student);

		// Tabloda seçilen satýrý textbox'a iþler
		table_lesson_student.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					txt_student_id.setText(
							table_lesson_student.getValueAt(table_lesson_student.getSelectedRow(), 0).toString());

				} catch (Exception ex) {

				}

			}
		});

		txt_student_id = new JTextField();
		txt_student_id.setBounds(599, 486, 261, 27);
		txt_student_id.setColumns(10);
		panel_lesson.add(txt_student_id);

		// *********** Ogrenci Yonetimi ***********\\
		JPanel panel_student = new JPanel();
		panel_student.setLayout(null);
		panel_student.setBackground(Color.WHITE);
		tabbedPane.addTab("Ogrenci Yonetimi", null, panel_student, null);

		JLabel lblNewLabel_1_4 = new JLabel("Ad");
		lblNewLabel_1_4.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(1119, 51, 46, 14);
		panel_student.add(lblNewLabel_1_4);

		JLabel lblNewLabel_1_1_1 = new JLabel("Soyad");
		lblNewLabel_1_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(1293, 47, 55, 23);
		panel_student.add(lblNewLabel_1_1_1);

		txt_add_student_first_name = new JTextField();
		txt_add_student_first_name.setColumns(10);
		txt_add_student_first_name.setBounds(1119, 72, 144, 27);
		panel_student.add(txt_add_student_first_name);

		txt_add_student_last_name = new JTextField();
		txt_add_student_last_name.setColumns(10);
		txt_add_student_last_name.setBounds(1293, 72, 144, 27);
		panel_student.add(txt_add_student_last_name);

		JLabel lblNewLabel_1_2_4 = new JLabel("TC NO");
		lblNewLabel_1_2_4.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_4.setBounds(1119, 139, 59, 23);
		panel_student.add(lblNewLabel_1_2_4);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Dogum Tarihi");
		lblNewLabel_1_2_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_1_1.setBounds(1293, 139, 125, 23);
		panel_student.add(lblNewLabel_1_2_1_1);

		txt_add_student_tc = new JTextField();
		txt_add_student_tc.setColumns(10);
		txt_add_student_tc.setBounds(1119, 163, 144, 27);
		panel_student.add(txt_add_student_tc);

		JDateChooser txt_add_student_birth = new JDateChooser();
		txt_add_student_birth.setBounds(1293, 163, 144, 27);
		panel_student.add(txt_add_student_birth);

		JLabel lblNewLabel_1_2_2_1 = new JLabel("Fakulte");
		lblNewLabel_1_2_2_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_2_1.setBounds(1119, 233, 68, 23);
		panel_student.add(lblNewLabel_1_2_2_1);

		JLabel lblNewLabel_1_2_3_1 = new JLabel("Bolum");
		lblNewLabel_1_2_3_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_3_1.setBounds(1293, 233, 61, 23);
		panel_student.add(lblNewLabel_1_2_3_1);

		txt_add_student_faculty = new JTextField();
		txt_add_student_faculty.setColumns(10);
		txt_add_student_faculty.setBounds(1119, 258, 144, 27);
		panel_student.add(txt_add_student_faculty);

		txt_add_student_department = new JTextField();
		txt_add_student_department.setColumns(10);
		txt_add_student_department.setBounds(1293, 258, 144, 27);
		panel_student.add(txt_add_student_department);

		JButton btn_add_student = new JButton("Ekle");

		btn_add_student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (txt_add_student_first_name.getText().length() == 0
						|| txt_add_student_last_name.getText().length() == 0
						|| txt_add_student_tc.getText().length() == 0
						|| txt_add_student_birth.getDate().toString().length() == 0
						|| txt_add_student_faculty.getText().length() == 0
						|| txt_add_student_department.getText().length() == 0
						|| txt_add_student_score.getText().length() == 0) {
					Helper.showMessage("Lutfen tum alanlari doldurunuz.");
				} else {

					student.setFirst_name(txt_add_student_first_name.getText());
					student.setLast_name(txt_add_student_last_name.getText());
					student.setIdentityNumber(txt_add_student_tc.getText());
					student.setBirth(txt_add_student_birth.getDate().toString());
					student.setFacultyName(txt_add_student_faculty.getText());
					student.setDepartmentName(txt_add_student_department.getText());
					student.setScore(Double.parseDouble(txt_add_student_score.getText()));
					student.setNumber(String.format("%2d%02d%02d%02d", LocalDate.now().getYear() % 100,
							faculty.getFetch(txt_add_student_faculty.getText()).getFacultyId(),
							department.getFetch(txt_add_student_department.getText()).getId(),
							student.getAllStudents().get(student.getAllStudents().size() - 1).getId() + 1));
					if (student.addStudent(student)) {
						updateLessonStudentTable();
						updateStudentModel();
						Helper.showMessage("Ogrenci sisteme eklendi.");
					} else
						Helper.showMessage("Ogrenci sisteme eklenirken hata olustu!");

					txt_add_student_first_name.setText("");
					txt_add_student_last_name.setText("");
					txt_add_student_tc.setText("");
					txt_add_student_birth.setDate(null);
					txt_add_student_faculty.setText("");
					txt_add_student_department.setText("");
					txt_add_student_score.setText("");
				}

			}
		});

		btn_add_student.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_add_student.setBackground(new Color(245, 245, 245));
		btn_add_student.setBounds(1217, 415, 122, 40);
		panel_student.add(btn_add_student);

		JLabel lblNewLabel_2_1 = new JLabel("Ogrenci Id");
		lblNewLabel_2_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(1119, 526, 99, 22);
		panel_student.add(lblNewLabel_2_1);

		txt_remove_student_id = new JTextField();
		txt_remove_student_id.setColumns(10);
		txt_remove_student_id.setBounds(1119, 559, 318, 27);
		panel_student.add(txt_remove_student_id);

		JButton btn_remove_student = new JButton("Sil");
		btn_remove_student.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (txt_remove_student_id.getText().length() == 0)
					Helper.showMessage("Lutfen silmek istediginiz Ogrencinin tablodaki id numarasini giriniz.");
				else {
					if (Helper.showWarning("Ogrenciyi silmek istediginizden emin misiniz?") == 0) {
						if (student.removeStudent(Integer.parseInt(txt_remove_student_id.getText()))) {
							updateStudentModel();
							updateLessonStudentTable();
							Helper.showMessage("Ogrenci silindi.");
						} else
							Helper.showMessage("bole bir Ogrenci bulunmuyor!");

						txt_remove_student_id.setText("");
					}
				}

			}
		});
		btn_remove_student.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_remove_student.setBackground(new Color(245, 245, 245));
		btn_remove_student.setBounds(1217, 638, 122, 40);
		panel_student.add(btn_remove_student);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(44, 51, 983, 627);
		panel_student.add(scrollPane_1);

		scrollPane_1.setViewportView(table_1);

		scrollPane_1.setViewportView(table_student);

		JLabel lblNewLabel_1_2_2_1_1 = new JLabel("Puan");
		lblNewLabel_1_2_2_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_2_2_1_1.setBounds(1119, 323, 67, 22);
		panel_student.add(lblNewLabel_1_2_2_1_1);

		txt_add_student_score = new JTextField();
		txt_add_student_score.setColumns(10);
		txt_add_student_score.setBounds(1119, 348, 144, 27);
		panel_student.add(txt_add_student_score);

		table_student.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				try {
					txt_remove_student_id
							.setText(table_student.getValueAt(table_student.getSelectedRow(), 0).toString());

				} catch (Exception ex) {

				}

			}
		});

		table_student.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {

				if (e.getType() == TableModelEvent.UPDATE) {

					int selectID = Integer
							.parseInt(table_student.getValueAt(table_student.getSelectedRow(), 0).toString());
					String selectName = table_student.getValueAt(table_student.getSelectedRow(), 1).toString();
					String selectSurname = table_student.getValueAt(table_student.getSelectedRow(), 2).toString();
					Double selectScore = Double
							.parseDouble(table_student.getValueAt(table_student.getSelectedRow(), 3).toString());
					String selectNumber;
					try {

						selectNumber = table_student.getValueAt(table_student.getSelectedRow(), 4).toString();
					} catch (Exception e2) {
						selectNumber = "NULL";
					}
					String selectTcNo = table_student.getValueAt(table_student.getSelectedRow(), 5).toString();
					String selectMail = table_student.getValueAt(table_student.getSelectedRow(), 6).toString();
					String selectFaculty = table_student.getValueAt(table_student.getSelectedRow(), 7).toString();
					String selectDepartment = table_student.getValueAt(table_student.getSelectedRow(), 8).toString();
					String selectBirth = table_student.getValueAt(table_student.getSelectedRow(), 9).toString();

					if (student.updateStudent(selectID, selectName, selectSurname, selectScore, selectNumber,
							selectTcNo, selectMail, selectFaculty, selectDepartment, selectBirth)) {
						Helper.showMessage("Egitmen bilgileri guncellendi.");
					}

				}

			}
		});

	}

	// fakülteye týklandýðýnda bölümleri listelenir
	public void updateDepartmentTable() {
		DefaultTableModel clearModel = (DefaultTableModel) table_department.getModel();
		clearModel.setRowCount(0);
		department.getAllDepartments().forEach(e -> {
			if (Integer.parseInt(selectedFacultyTableId) != 0) {
				if (e.getFaculty().getFacultyId() == Integer.parseInt(selectedFacultyTableId)) {
					departmentData[0] = e.getId();
					departmentData[1] = e.getDepartmentName();
					departmentData[2] = e.getFacultyName();
					departmentModel.addRow(departmentData);
				}

			} else {
				departmentData[0] = e.getId();
				departmentData[1] = e.getDepartmentName();
				departmentData[2] = e.getFacultyName();
				departmentModel.addRow(departmentData);
			}
		});

		table_department = new JTable(departmentModel);
		scroll_department.setViewportView(table_department);
		departmentPopup();
	}

	// UPDATE TABLE
	public void updateInstructorModel() {
		DefaultTableModel clearModel = (DefaultTableModel) table_instructor.getModel();
		clearModel.setRowCount(0);

		instructor2.getAllInstructors().forEach(e -> {
			instructorData[0] = e.getId();
			instructorData[1] = e.getFirst_name();
			instructorData[2] = e.getLast_name();
			instructorData[3] = e.getDegree();
			instructorData[4] = e.getIdentityNumber();
			instructorData[5] = e.getMail();
			instructorData[6] = e.getFacultyName();
			instructorData[7] = e.getDepartmentName();
			instructorModel.addRow(instructorData);
		});

	}

	public void updateFacultyModel() {
		DefaultTableModel clearModel = (DefaultTableModel) table_faculty.getModel();
		clearModel.setRowCount(0);

		faculty.getAllFaculties().forEach(e -> {
			facultyData[0] = e.getFacultyId();
			facultyData[1] = e.getFacultyName();
			facultyModel.addRow(facultyData);
		});

	}

	public void updateLessonTable() {
		DefaultTableModel clearModel = (DefaultTableModel) table_lesson.getModel();
		clearModel.setRowCount(0);

		lesson.getAllLessons().forEach(e -> {
			lessonData[0] = e.getLessonId();
			lessonData[1] = e.getLessonName();
			lessonModel.addRow(lessonData);
		});

	}

	public void updateLessonStudentTable() {
		DefaultTableModel clearModel = (DefaultTableModel) table_lesson_student.getModel();
		clearModel.setRowCount(0);

		student.getAllStudents().forEach(e -> {
			lessonStudentData[0] = e.getId();
			lessonStudentData[1] = e.getFirst_name();
			lessonStudentData[2] = e.getLast_name();
			lessonStudentData[3] = e.getSchoolNumber();
			lessonStudentData[4] = e.getMail();
			lessonStudentData[5] = e.getFacultyName();
			lessonStudentData[6] = e.getDepartmentName();
			lessonStudentModel.addRow(lessonStudentData);
		});

	}

	public void updateStudentModel() {
		DefaultTableModel clearModel = (DefaultTableModel) table_student.getModel();
		clearModel.setRowCount(0);
		student.getAllStudents().forEach(e -> {
			studentData[0] = e.getId();
			studentData[1] = e.getFirst_name();
			studentData[2] = e.getLast_name();
			studentData[3] = e.getScore();
			studentData[4] = e.getNumber();
			studentData[5] = e.getIdentityNumber();
			studentData[6] = e.getMail();
			studentData[7] = e.getFacultyName();
			studentData[8] = e.getDepartmentName();
			studentData[9] = e.getBirth();
			studentModel.addRow(studentData);
		});

	}

	public void departmentPopup() {
		departmentPopup = new JPopupMenu();
		JMenuItem updateDepartment = new JMenuItem("Güncelle");
		JMenuItem removeDepartment = new JMenuItem("Sil");
		departmentPopup.add(updateDepartment);
		departmentPopup.add(removeDepartment);

		updateDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer
						.parseInt(table_department.getValueAt(table_department.getSelectedRow(), 0).toString());
				Department selectedDepartment = department.getFetch(selectedID);
				DepartmentUpdateGUI dGUI = new DepartmentUpdateGUI(selectedDepartment);
				dGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				dGUI.setVisible(true);
				dGUI.addWindowListener(new WindowAdapter() { // Açýlan pencereyi dinliyoruz

					@Override
					public void windowClosed(WindowEvent e) { // Açýlan pencere kapatýldýðýnda db'de veri güncellendiði
																// için tablomuzu da güncelliyoruz.
						updateDepartmentTable();
						updateFacultyModel();

					}

				});

			}
		});

		removeDepartment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer
						.parseInt(table_department.getValueAt(table_department.getSelectedRow(), 0).toString());
				if (Helper.showWarning("Departmani silmek istediginizden emin misiniz?") == 0) {
					if (department.removeDepartment(selectedID)) {
						Helper.showMessage("Islem basarili!");

						updateDepartmentTable();
						updateFacultyModel();
					} else
						Helper.showMessage("Hata olustu!");
				}

			}
		});

		table_department.setComponentPopupMenu(departmentPopup); // Yukardaki popup menüyü tabloya atadýk.
		table_department.addMouseListener(new MouseAdapter() { // sag tikladigimizda hangi satýrda oldugumuzu bulduk

			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_department.rowAtPoint(point);
				table_department.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		scroll_department.setViewportView(table_department);
	}

	public void lessonPopup() {
		lessonPopup = new JPopupMenu();
		JMenuItem updateLesson = new JMenuItem("Güncelle");
		JMenuItem removeLesson = new JMenuItem("Sil");
		departmentPopup.add(updateLesson);
		departmentPopup.add(removeLesson);

		updateLesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer.parseInt(table_lesson.getValueAt(table_lesson.getSelectedRow(), 0).toString());
				Lesson selectedLesson = lesson.getFetch(selectedID);
				LessonUpdateGUI lGUI = new LessonUpdateGUI(selectedLesson);
				lGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				lGUI.setVisible(true);
				lGUI.addWindowListener(new WindowAdapter() { // Açýlan pencereyi dinliyoruz

					@Override
					public void windowClosed(WindowEvent e) { // Açýlan pencere kapatýldýðýnda db'de veri güncellendiði
																// için tablomuzu da güncelliyoruz.
						updateLessonTable();

					}

				});

			}
		});

		removeLesson.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedID = Integer.parseInt(table_lesson.getValueAt(table_lesson.getSelectedRow(), 0).toString());
				if (Helper.showWarning("Dersi silmek istediginizden emin misiniz?") == 0) {
					if (lesson.removeLesson(selectedID)) {
						Helper.showMessage("Islem basarili!");

						updateLessonTable();
					} else
						Helper.showMessage("Hata olustu!");
				}

			}
		});

		table_lesson.setComponentPopupMenu(lessonPopup); // Yukardaki popup menüyü tabloya atadýk.
		table_lesson.addMouseListener(new MouseAdapter() { // sag tikladigimizda hangi satýrda oldugumuzu bulduk

			@Override
			public void mousePressed(MouseEvent e) {

				Point point = e.getPoint();
				int selectedRow = table_lesson.rowAtPoint(point);
				table_lesson.setRowSelectionInterval(selectedRow, selectedRow);

			}

		});
		scroll_lesson.setViewportView(table_lesson);
	}
}
