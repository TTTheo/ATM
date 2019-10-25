/*
 * One of the many ButtonListener classes, handles the "Review Daily Report" request from 
 * the banker, show the Log of all the operations happened in the application in the messageArea,
 * and then transfer the banker back to the Banker Menu Panel
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class reviewButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	
	reviewButtonListener (CoreInfo coreInfo, String messageToShow) {
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
	}

	public void actionPerformed(ActionEvent e) {
		coreInfo.getMessageArea().setText(coreInfo.getLog());
		coreInfo.getMessageArea().setBounds(100, 50, 200, 50);
		coreInfo.getFrame().add(coreInfo.getMessageArea());
		coreInfo.getPanel().removeAll();
		coreInfo.getPanel().setLayout(new GridLayout(1, 1, 20, 10));
		JButton exitButton = new JButton("Done");
		coreInfo.getPanel().add(exitButton);
		exitButtonListener eAction = new exitButtonListener(this.coreInfo);
		exitButton.addActionListener(eAction);
	}
}
