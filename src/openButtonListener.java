/*
 * One of the many ButtonListener classes, handles the "Open Account" request from the customer,
 * records the user input that is needed to open a new account in the bank
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class openButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	private CustomerInfo cInfo;
	
	openButtonListener(CoreInfo coreInfo, CustomerInfo cInfo, String messageToShow){
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
		this.cInfo = cInfo;
	}
	
	public void actionPerformed(ActionEvent e) {
		String[] accountTypes = {"Saving Account", "Checking Account"};
		coreInfo.getMessageArea().setText(this.messageToShow);
		coreInfo.getMessageArea().setBounds(100, 250, 200, 50);
		coreInfo.getPanel().removeAll();
		coreInfo.getPanel().setLayout(new GridLayout(5, 1, 20, 10));
		JLabel labelOne = new JLabel("What is the type of account you want to open?");
		coreInfo.getPanel().add(labelOne);
		final JComboBox selectBox = new JComboBox(accountTypes);
		coreInfo.getPanel().add(selectBox);
		JLabel labelTwo = new JLabel("Please give a name to this account");
		coreInfo.getPanel().add(labelTwo);
		JTextField tf1 = new JTextField();
		coreInfo.getPanel().add(tf1);
		JButton finishButton = new JButton("Finish");
		coreInfo.getPanel().add(finishButton);
		ofinishButtonListener fAction = new ofinishButtonListener(this.coreInfo, this.cInfo, "Hello, ");
		finishButton.addActionListener(fAction);
		coreInfo.getPanel().updateUI();
		coreInfo.getFrame().setLayout(new FlowLayout());
	}

}
