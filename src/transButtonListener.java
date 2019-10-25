/*
 * One of the many ButtonListener classes, handles the "Transaction" request from the customer.
 * Records the needed information required for a customer to make a transaction
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.*;
import java.util.ArrayList;

public class transButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	private CustomerInfo cInfo;
	
	transButtonListener (CoreInfo coreInfo, CustomerInfo cInfo, String messageToShow){
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
		this.cInfo = cInfo;
	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < this.coreInfo.getCustomerList().size(); i++) {
			if (this.coreInfo.getCustomerList().get(i).getCustomerID().equals(this.cInfo.getCustomerID())) {
				if (this.coreInfo.getCustomerList().get(i).accountList.size() != 0) {
					String[] accountList = new String[this.coreInfo.getCustomerList().get(i).accountList.size()];
					int index = 0;
					for (AccountInfo account : this.coreInfo.getCustomerList().get(i).accountList) {
						accountList[index] = account.getAccountName();
						index++;
					}
					//Construct the panel
					coreInfo.getMessageArea().setText(this.messageToShow);
					coreInfo.getMessageArea().setBounds(100, 250, 200, 50);
					coreInfo.getPanel().removeAll();
					coreInfo.getPanel().setLayout(new GridLayout(6, 1, 10, 5));
					JLabel labelOne = new JLabel("From Account?");
					coreInfo.getPanel().add(labelOne);
					final JComboBox selectBox = new JComboBox(accountList);
					coreInfo.getPanel().add(selectBox);
					JLabel labelTwo = new JLabel("In Currency?");
					coreInfo.getPanel().add(labelTwo);
					JTextField tf1 = new JTextField();
					coreInfo.getPanel().add(tf1);
					JLabel labelThree = new JLabel("Transfer Amount?");
					coreInfo.getPanel().add(labelThree);
					JTextField tf2 = new JTextField();
					coreInfo.getPanel().add(tf2);
					JButton finishButton = new JButton("Finish");
					coreInfo.getPanel().add(finishButton);
					tfinishButtonListener tfAction = new tfinishButtonListener(this.coreInfo, this.cInfo, "Hello, ");
					finishButton.addActionListener(tfAction);
					coreInfo.getPanel().updateUI();
					coreInfo.getFrame().setLayout(new FlowLayout());
				} else {
					ErrorWindow eWindow = new ErrorWindow("No valid account found!");
					eWindow.show();
					MyPanels mp = new MyPanels();
					mp.setCustomerMenuPanel(coreInfo, cInfo, messageToShow);
				}	
			}
		}
	}

}
