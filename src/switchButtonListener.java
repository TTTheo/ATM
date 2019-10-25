/*
 * One of the many ButtonListener classes, handles the transitions from Customer log in panel
 * back to the main panel, so that the user can switch to Banker
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class switchButtonListener implements ActionListener{
	private CoreInfo coreInfo;
	
	switchButtonListener(CoreInfo coreInfo){
		this.coreInfo = coreInfo;
	}
	
	public void actionPerformed(ActionEvent e) {
		MyPanels mp = new MyPanels();
		mp.setMainMenuPanel(this.coreInfo);	
	}

}
