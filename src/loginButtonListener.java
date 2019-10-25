/*
 * One of the many ButtonListener classes, checks the user's input information with
 * the account information stored in the CustomerList, and proceeds if the information is correct 
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class loginButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	private CustomerInfo cInfo;
	private JTextField tf1;
	private JTextField tf2;
	private ArrayList<CustomerInfo> customerList;
	
	loginButtonListener(CoreInfo coreInfo, String messageToShow){
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
		this.tf1 = (JTextField) coreInfo.getPanel().getComponent(1);
		this.tf2 = (JTextField) coreInfo.getPanel().getComponent(3);
		this.customerList = coreInfo.getCustomerList();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for (CustomerInfo customer : this.customerList) {
			if (customer.getCustomerID().equals(this.tf1.getText())) {
				this.cInfo = new CustomerInfo(customer.getCustomerID(), customer.getPassword());
			}	
		}
		if (this.cInfo == null) {
			ErrorWindow eWindow = new ErrorWindow("No such Customer in bank");
			eWindow.show();
		} else {
			if(!this.cInfo.getPassword().equals(this.tf2.getText())) {
				ErrorWindow eWindow = new ErrorWindow("Password is invalid");
				eWindow.show();
			} else {
				MyPanels mp = new MyPanels();
				mp.setCustomerMenuPanel(this.coreInfo, cInfo, messageToShow);
			}
		}
	}

}
