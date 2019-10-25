/*
 * One of the many ButtonListener classes, finalize the "Open Account" request from the customer,
 * storing new account information to the CustomerList inside coreInfo
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class ofinishButtonListener implements ActionListener{
	private String messageToShow;
	private CustomerInfo cInfo;
	private JComboBox selectBox;
	private JTextField tf1;
	private CoreInfo coreInfo;
	
	ofinishButtonListener(CoreInfo coreInfo, CustomerInfo cInfo, String messageToShow){
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
		this.cInfo = cInfo;
		if(coreInfo.getPanel().getComponent(1) instanceof JComboBox) {
			this.selectBox = (JComboBox) coreInfo.getPanel().getComponent(1);
		} else {
			this.selectBox = null;
		}
		if(coreInfo.getPanel().getComponent(3) instanceof JTextField) {
			this.tf1 = (JTextField) coreInfo.getPanel().getComponent(3);
		} else {
			this.tf1 = null;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (this.tf1 != null) {
			AccountInfo newAccount = new AccountInfo((String) this.selectBox.getItemAt(this.selectBox.getSelectedIndex()), this.tf1.getText());
			for (int i = 0; i < this.coreInfo.getCustomerList().size(); i++) {
				if (this.coreInfo.getCustomerList().get(i).getCustomerID().equals(this.cInfo.getCustomerID())) {
					this.coreInfo.getCustomerList().get(i).accountList.add(newAccount);
				}
			}
			String newLog = "Customer " + this.cInfo.getCustomerID() + " has opened a " + (String) this.selectBox.getItemAt(this.selectBox.getSelectedIndex()) + " !\n";
			coreInfo.addLog(newLog);
		}
		MyPanels mp = new MyPanels();
		mp.setCustomerMenuPanel(this.coreInfo,  cInfo, messageToShow);
	}

}
