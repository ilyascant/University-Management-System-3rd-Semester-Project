package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;

import Model.Student;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentGUI extends JFrame {

	static Student student = new Student();

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGUI frame = new StudentGUI(student);
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
	public StudentGUI(Student student) {
		setTitle("Ogrenci Paneli");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1020, 691);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(
				"Hos Geldiniz " + student.getFirst_name().toUpperCase() + " " + student.getLast_name().toUpperCase());
		lblNewLabel.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel.setBounds(36, 36, 345, 23);
		contentPane.add(lblNewLabel);

		JButton btn_manager_logout = new JButton("Cikis Yap");
		btn_manager_logout.setFont(new Font("Montserrat Medium", Font.PLAIN, 14));
		btn_manager_logout.setForeground(Color.BLACK);
		btn_manager_logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();

			}
		});
		btn_manager_logout.setBackground(new Color(255, 192, 203));
		btn_manager_logout.setBounds(860, 36, 110, 44);
		contentPane.add(btn_manager_logout);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 91, 479, 538);
		contentPane.add(scrollPane);

		studentModel = new DefaultTableModel();
		Object[] colStudent = new Object[2];
		colStudent[0] = "ID";
		colStudent[1]= "DERS ADI";
		studentModel.setColumnIdentifiers(colStudent);
		studentData = new Object[2];
		student.getStudentsLessons(student).forEach(e->{
			studentData[0] = e.getLessonId();
			studentData[1] = e.getLessonName();;
			studentModel.addRow(studentData);
		});

		table = new JTable(studentModel);
		setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("AD: "+ student.getFirst_name());
		lblNewLabel_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(585, 91, 385, 23);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("SOYAD: "+ student.getLast_name());
		lblNewLabel_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(585, 116, 385, 23);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("NUMARA: "+ student.getNumber());
		lblNewLabel_1_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(585, 141, 385, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("TC: "+ student.getIdentityNumber());
		lblNewLabel_1_1_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1.setBounds(585, 166, 385, 23);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("FAKULTE: "+ student.getFacultyName());
		lblNewLabel_1_1_1_1_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_1.setBounds(585, 191, 385, 23);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("BOLUM: "+ student.getDepartmentName());
		lblNewLabel_1_1_1_1_2.setFont(new Font("Montserrat Medium", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1_2.setBounds(585, 216, 385, 23);
		contentPane.add(lblNewLabel_1_1_1_1_2);
	}
}
