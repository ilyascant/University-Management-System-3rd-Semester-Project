package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Faculty;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FacultyUpdateGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_update_faculty_name;
	private static Faculty faculty;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FacultyUpdateGUI frame = new FacultyUpdateGUI(faculty);
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
	public FacultyUpdateGUI(Faculty faculty) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 287, 216);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Fakülte Ad");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(25, 29, 95, 22);
		contentPane.add(lblNewLabel_1);
		
		txt_update_faculty_name = new JTextField();
		txt_update_faculty_name.setText(faculty.getFacultyName());
		txt_update_faculty_name.setColumns(10);
		txt_update_faculty_name.setBounds(23, 62, 220, 30);
		contentPane.add(txt_update_faculty_name);
		
		JButton btn_update_faculty = new JButton("Düzenle");
		btn_update_faculty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int updateFacultyId = faculty.getFacultyId();
				String updateFacultyName = txt_update_faculty_name.getText();
				
				if(Helper.showWarning("Fakültenin adýný deðiþtirmek istediðinizden emin misiniz?") == 0) {
					if(faculty.updateFaculty(updateFacultyId, updateFacultyName)) {
						Helper.showMessage("Ýþlem baþarýlý!");
						dispose();
					}else
						Helper.showMessage("Hata oluþtu!");
				}
				
				
				
			}
		});
		btn_update_faculty.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_update_faculty.setBackground(new Color(245, 245, 245));
		btn_update_faculty.setBounds(25, 112, 218, 40);
		contentPane.add(btn_update_faculty);
	}

}
