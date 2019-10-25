/*
 * One of the many ButtonListener classes, handles the "Registration" request from the customer,
 * records the user input that is needed to open a new account in the application
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class registerButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	registerButtonListener(CoreInfo coreInfo, String messageToShow){
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
	}
	
	public void actionPerformed(ActionEvent e) {
		coreInfo.getMessageArea().setText(this.messageToShow);
		coreInfo.getMessageArea().setBounds(100, 250, 200, 50);
		coreInfo.getPanel().removeAll();
		coreInfo.getPanel().setLayout(new GridLayout(3, 2, 20, 10));
		JLabel labelOne = new JLabel("Customer ID");
		coreInfo.getPanel().add(labelOne);
		JTextField tf1 = new JTextField();
		coreInfo.getPanel().add(tf1);
		JLabel labelTwo = new JLabel("Password");
		coreInfo.getPanel().add(labelTwo);
	    JTextField tf2 = new JTextField();
	    coreInfo.getPanel().add(tf2);
		JButton fregisterButton = new JButton( "Register" );
		fregisterButtonListener frAction = new fregisterButtonListener(this.coreInfo, "Customer Registrition");
		fregisterButton.addActionListener(frAction);
		coreInfo.getPanel().add(fregisterButton);
		coreInfo.getPanel().updateUI();
		coreInfo.getFrame().setLayout(new FlowLayout());
	}
	
}
