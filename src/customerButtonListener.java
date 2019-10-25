/*
 * One of the many ButtonListener classes, handles the transition from choosing application
 * user page to customer log in page
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class customerButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	
	customerButtonListener (CoreInfo coreInfo, String messageToShow) {
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
	}
	
	public void actionPerformed(ActionEvent e) {
		MyPanels mp = new MyPanels();
		mp.setCustomerLoginPanel(coreInfo, messageToShow);
	}

}
