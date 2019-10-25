/*
 * One of the many ButtonListener classes, handles the transitions from Customer Logged in panel
 * back to the log in panel
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class logoutButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	
	logoutButtonListener (CoreInfo coreInfo, String messageToShow) {
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
	}
	
	public void actionPerformed(ActionEvent e) {
		MyPanels mp = new MyPanels();
		mp.setCustomerLoginPanel(this.coreInfo, messageToShow);
	}

}
