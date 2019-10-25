/*
 * One of the many ButtonListener classes, finalizes the "Apply Loan" request from customer,
 * storing all the related information of this loan to the CustomerList
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

public class lfinishButtonListener implements ActionListener{
	private String messageToShow;
	private CustomerInfo cInfo;
	private JComboBox selectBox;
	private JTextField tf1;
	private JTextField tf2;

	private CoreInfo coreInfo;
	
	lfinishButtonListener (CoreInfo coreInfo, CustomerInfo cInfo, String messageToShow){
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
		this.cInfo = cInfo;
		this.selectBox = (JComboBox) coreInfo.getPanel().getComponent(1);
		this.tf1 = (JTextField) coreInfo.getPanel().getComponent(3);
		this.tf2 = (JTextField) coreInfo.getPanel().getComponent(5);
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			for (int i = 0; i < this.coreInfo.getCustomerList().size(); i++) {
				if (this.coreInfo.getCustomerList().get(i).getCustomerID().equals(this.cInfo.getCustomerID())) {
					LoanInfo newLoan = new LoanInfo(this.tf1.getText().toUpperCase(), Double.parseDouble(this.tf2.getText()));
					for (AccountInfo account : this.coreInfo.getCustomerList().get(i).accountList) {
						if (account.getAccountName().equals(this.selectBox.getItemAt(this.selectBox.getSelectedIndex()))) {
							account.addLoan(newLoan);
						}
					}
					String newLog = "Customer " + this.cInfo.getCustomerID() + " made a loan of amount " + this.tf2.getText() + " in " +  this.tf1.getText().toUpperCase() + "\n";
					coreInfo.addLog(newLog);
				}
			}
		} catch (NumberFormatException e1) {
			ErrorWindow eWindow = new ErrorWindow("Invalid input for Loan Amount!!");
			eWindow.show();
		}
		MyPanels mp = new MyPanels();
		mp.setCustomerMenuPanel(this.coreInfo, cInfo, messageToShow);
	}

}
