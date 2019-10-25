/*
 * One of the many ButtonListener classes, finalizing customer's request for account registration,
 * adds this customer account to the CustomerList, and then transfers the user back to the log in page
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class fregisterButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	private JTextField tf1;
	private JTextField tf2;

	fregisterButtonListener(CoreInfo coreInfo, String messageToShow){
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
		this.tf1 = (JTextField) coreInfo.getPanel().getComponent(1);
		this.tf2 = (JTextField) coreInfo.getPanel().getComponent(3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Take the value of new customer information
		CustomerInfo cInfo = new CustomerInfo(this.tf1.getText(), this.tf2.getText());
		coreInfo.getCustomerList().add(cInfo);
		String newLog = "New Customer " + tf1.getText() + " joined our bank!" + "\n";
		coreInfo.addLog(newLog);
		MyPanels mp = new MyPanels();
		mp.setCustomerLoginPanel(this.coreInfo, messageToShow);
	}

}
