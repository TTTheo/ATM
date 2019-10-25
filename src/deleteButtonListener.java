/*
 * One of the many ButtonListener classes, handles the "Account Management" request of the banker, giving
 * the banker the ability to remove one customer from the bank each time
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class deleteButtonListener implements ActionListener{
	private JComboBox selectbox;
	private CoreInfo coreInfo;

	deleteButtonListener (CoreInfo coreInfo) {
		this.coreInfo = coreInfo;
		this.selectbox = (JComboBox) coreInfo.getPanel().getComponent(1);
	}
	/*
	 * To avoid the possible input exception, all the customers are shown to the banker inside this
	 * JComboBox, so that the banker doesn't need to know the customer, but removing them from the bank
	 * directly
	 */
	public void actionPerformed(ActionEvent e) {
		CustomerInfo temp = new CustomerInfo();
		for (CustomerInfo customer : coreInfo.getCustomerList()) {
			if (customer.getCustomerID().equals(this.selectbox.getItemAt(this.selectbox.getSelectedIndex()))) {
				temp = customer;
			}
		}
		coreInfo.getCustomerList().remove(temp);
		MyPanels mp = new MyPanels();
		mp.setBankerMenuPanel(this.coreInfo);
	}

}
