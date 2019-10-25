/*
 * One of the many ButtonListener classes, finalize the "Transaction" request from the customer
 * Has several check before the transaction is proceeded: (1) Check whether the customer has any
 * deposit inside the chosen account; (2) Check whether the customer has deposit in the chosen currency;
 * (3) Type check for the user input of transaction amount
 * 
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class tfinishButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	private CustomerInfo cInfo;
	private JComboBox selectBox;
	private JTextField tf1;
	private JTextField tf2;
	
	tfinishButtonListener (CoreInfo coreInfo, CustomerInfo cInfo, String messageToShow){
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
		this.cInfo = cInfo;
		this.selectBox = (JComboBox) coreInfo.getPanel().getComponent(1);
		this.tf1 = (JTextField) coreInfo.getPanel().getComponent(3);
		this.tf2 = (JTextField) coreInfo.getPanel().getComponent(5);
	}
	
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < this.coreInfo.getCustomerList().size(); i++) {
			if (this.coreInfo.getCustomerList().get(i).getCustomerID().equals(this.cInfo.getCustomerID())) {
				ArrayList<DepositInfo> temp = new ArrayList<DepositInfo>();
				for (AccountInfo account : this.coreInfo.getCustomerList().get(i).accountList) {
					if (account.getAccountName().equals(this.selectBox.getItemAt(this.selectBox.getSelectedIndex()))) {
						temp = account.getAccountBalanceList();
					}
				}
				if (temp.size() >= 1) {
					for (DepositInfo aDepo : temp) {
						if (aDepo.getCurrencyType().equalsIgnoreCase((this.tf1.getText()))) {
							try {
								aDepo.makeTransaction(Double.parseDouble(this.tf2.getText()));
								String newLog = "Customer " + this.cInfo.getCustomerID() + " made a transfer of amount " + this.tf2.getText() + " in " +  this.tf1.getText().toUpperCase() + "\n";
								coreInfo.addLog(newLog);
							} catch (NumberFormatException e1) {
								ErrorWindow eWindow = new ErrorWindow("Invalid input for Transfer Amount!");
								eWindow.show();
							}
						} else {
							ErrorWindow eWindow = new ErrorWindow("No sufficient funds in chosen currency!");
							eWindow.show();
						}
					}
				} else {
					ErrorWindow eWindow = new ErrorWindow("No Deposit Balance Found!");
					eWindow.show();
				}
				MyPanels mp = new MyPanels();
				mp.setCustomerMenuPanel(this.coreInfo, cInfo, messageToShow);
			}
		}
	}
}
