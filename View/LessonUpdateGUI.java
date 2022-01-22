package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Lesson;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class LessonUpdateGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_update_lesson;
	private static Lesson lesson;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LessonUpdateGUI frame = new LessonUpdateGUI(lesson);
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
	public LessonUpdateGUI(Lesson lesson) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ders Ad");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(32, 26, 95, 22);
		contentPane.add(lblNewLabel_1);
		
		txt_update_lesson = new JTextField();
		txt_update_lesson.setText(lesson.getLessonName());
		txt_update_lesson.setColumns(10);
		txt_update_lesson.setBounds(30, 59, 220, 30);
		contentPane.add(txt_update_lesson);
		
		JButton btn_update_lesson = new JButton("Duzenle");
		btn_update_lesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int updateLessonId = lesson.getLessonId();
				String updateLessonName = txt_update_lesson.getText();
				
				if(Helper.showWarning("Dersin adýný deðiþtirmek istediðinizden emin misiniz?") == 0) {
					if(lesson.updateLesson(updateLessonId, updateLessonName)) {
						Helper.showMessage("Ýþlem baþarýlý!");
						dispose();
					}else {
						Helper.showMessage("Hata oluþtu!");
						dispose();
					}
						
				}
				
				
				
			}
		});
		btn_update_lesson.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_update_lesson.setBackground(new Color(245, 245, 245));
		btn_update_lesson.setBounds(32, 109, 218, 40);
		contentPane.add(btn_update_lesson);
	}
}
