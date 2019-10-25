/*
 * One of the many ButtonListener classes, finalizing the deposit a customer made by storing related infortion
 * in the right place inside the CustomerList, whose reference is held inside coreInfo
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class dfinishButtonListener implements ActionListener{
	private String messageToShow;
	private CustomerInfo cInfo;
	private JComboBox selectBox;
	private JTextField tf1;
	private JTextField tf2;
	private CoreInfo coreInfo;
	
	dfinishButtonListener (CoreInfo coreInfo, CustomerInfo cInfo, String messageToShow){
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
					ArrayList<DepositInfo> temp = new ArrayList<DepositInfo>();
					for (AccountInfo account : this.coreInfo.getCustomerList().get(i).accountList) {
						if (account.getAccountName().equals(this.selectBox.getItemAt(this.selectBox.getSelectedIndex()))) {
							temp = account.getAccountBalanceList();
							if (temp.size() >= 1) {
								for (DepositInfo aDepo : temp) {
									if (aDepo.getCurrencyType().equalsIgnoreCase(this.tf1.getText())) {
										aDepo.makeDeposit(Integer.parseInt(this.tf2.getText()));
									} else {
										account.addDeposit(new DepositInfo(this.tf1.getText().toUpperCase(), Integer.parseInt(this.tf2.getText())));
									}
								}
							} else {
								account.addDeposit(new DepositInfo(this.tf1.getText().toUpperCase(), Integer.parseInt(this.tf2.getText())));
							}
						}
					}
					String newLog = "Customer " + this.cInfo.getCustomerID() + " made a deposit of amount " + this.tf2.getText() + " in " +  this.tf1.getText().toUpperCase() + "\n";
					coreInfo.addLog(newLog);
				}
			}
		} catch (NumberFormatException e1) {
			ErrorWindow eWindow = new ErrorWindow("Invalid input for the Deposit Amount!!");
			eWindow.show();
		}
		MyPanels mp = new MyPanels();
		mp.setCustomerMenuPanel(this.coreInfo, cInfo, messageToShow);
	}
	
	

}
