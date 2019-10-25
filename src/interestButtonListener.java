/*
 * One of the many ButtonListener classes, handles the "Interest Calculation" request from the Banker,
 * which calculates the interest of both customers' deposit and loans, adding the interest to customer's
 * account after calculation
 */
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.*;


public class interestButtonListener implements ActionListener{
	private CoreInfo coreInfo;
	private String messageToShow;
	
	interestButtonListener(CoreInfo coreInfo, String messageToShow){
		this.coreInfo = coreInfo;
		this.messageToShow = messageToShow;
	}
	
	public void actionPerformed(ActionEvent e) {
		for (CustomerInfo aCustomer : this.coreInfo.getCustomerList()) {
			aCustomer.calculateDepoInterest(this.coreInfo);
			aCustomer.calculateLoanInterest(this.coreInfo);
		}
		coreInfo.getMessageArea().setText(coreInfo.getInterestLog());
		coreInfo.getMessageArea().setBounds(100, 50, 200, 50);
		coreInfo.getFrame().add(coreInfo.getMessageArea());
		coreInfo.getPanel().removeAll();
		coreInfo.getPanel().setLayout(new GridLayout(1, 1, 20, 10));
		JButton exitButton = new JButton("Done");
		coreInfo.getPanel().add(exitButton);
		exitButtonListener eAction = new exitButtonListener(this.coreInfo);
		exitButton.addActionListener(eAction);
	}
}
