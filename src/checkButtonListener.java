/*
 * One of the many ButtonListener classes, handles: 
 * (1) the reconstruction of after "Check Account" button is clicked on Customer Logged in menu panel; 
 * (2) records the choices the customers made about which account they want to check  
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.*;
import java.util.ArrayList;

public class checkButtonListener implements ActionListener{
	private String messageToShow;
	private CustomerInfo cInfo;
	private CoreInfo coreInfo;
	
	checkButtonListener (CoreInfo coreInfo, CustomerInfo cInfo, String messageToShow){
		this.messageToShow = messageToShow;
		this.cInfo = cInfo;
		this.coreInfo = coreInfo;
	}
	/*
	 * The panel that allows the customer to make choices about the account they want to check
	 * to avoid input exception, the choices are given inside JComboBox, whose options are determined
	 * by the information recorded inside the coreInfo 
	 */
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
					coreInfo.getPanel().setLayout(new GridLayout(3, 2, 20, 10));
					JLabel labelOne = new JLabel("Which account do you want to check?");
					coreInfo.getPanel().add(labelOne);
					final JComboBox selectBox = new JComboBox(accountList);
					coreInfo.getPanel().add(selectBox);
					JButton finishButton = new JButton("Check");
					coreInfo.getPanel().add(finishButton);
					cfinishButtonListener fAction = new cfinishButtonListener(this.coreInfo, this.cInfo, "Account Information");
					finishButton.addActionListener(fAction);
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
