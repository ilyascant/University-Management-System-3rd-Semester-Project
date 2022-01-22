package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Department;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class DepartmentUpdateGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_update_department;
	private static Department department;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentUpdateGUI frame = new DepartmentUpdateGUI(department);
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
	public DepartmentUpdateGUI(Department department) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 281, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bolum Ad");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(24, 22, 95, 22);
		contentPane.add(lblNewLabel_1);
		
		txt_update_department = new JTextField();
		txt_update_department.setText(department.getDepartmentName());
		txt_update_department.setColumns(10);
		txt_update_department.setBounds(22, 55, 220, 30);
		contentPane.add(txt_update_department);
		
		JButton btn_update_department = new JButton("Duzenle");
		btn_update_department.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int updateDepartmentId = department.getId();
				String updateDepartmentName = txt_update_department.getText();
				
				if(Helper.showWarning("Fakültenin adýný deðiþtirmek istediðinizden emin misiniz?") == 0) {
					if(department.updateDepartment(updateDepartmentId, updateDepartmentName)) {
						Helper.showMessage("Ýþlem baþarýlý!");
						dispose();
					}else {
						Helper.showMessage("Hata oluþtu!");
						dispose();
					}
						
				}
				
				
				
			}
		});
		btn_update_department.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_update_department.setBackground(new Color(245, 245, 245));
		btn_update_department.setBounds(24, 105, 218, 40);
		contentPane.add(btn_update_department);
	}
}
