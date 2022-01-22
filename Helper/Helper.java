package Helper;

import javax.swing.JOptionPane;

public class Helper {

	public static void showMessage(String msg) {
		
		JOptionPane.showMessageDialog(null, msg);
		
	}
	
	public static int showWarning(String msg) {
		return JOptionPane.showConfirmDialog(null, msg);
	}
	
}
