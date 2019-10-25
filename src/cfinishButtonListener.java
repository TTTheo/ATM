/*
 * One of the many ButtonListener classes, generating the report of the status of the chosen account;
 * Allows the customers to go back to the Menu panel after they finish reviewing the report. 
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class cfinishButtonListener implements ActionListener{
	private String messageToShow;
	private CustomerInfo cInfo;
	private JComboBox selectBox;
	private CoreInfo coreInfo;
	
	cfinishButtonListener(CoreInfo coreInfo, CustomerInfo cInfo, String messageToShow){
		this.messageToShow = messageToShow;
		this.cInfo = cInfo;
		this.selectBox = (JComboBox) coreInfo.getPanel().getComponent(1);
		this.coreInfo = coreInfo;
	}
	
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < this.coreInfo.getCustomerList().size(); i++) {
			if (this.coreInfo.getCustomerList().get(i).getCustomerID().equals(this.cInfo.getCustomerID())) {
				AccountInfo theAccount = this.coreInfo.getCustomerList().get(i).getAccountByName((String) this.selectBox.getItemAt(this.selectBox.getSelectedIndex()));
				ArrayList<DepositInfo> depositList = theAccount.getAccountBalanceList();
				ArrayList<LoanInfo> loanList = theAccount.getAccountLoanList();
				//Construct the panel
				coreInfo.getMessageArea().setText(this.messageToShow);
				coreInfo.getMessageArea().setBounds(100, 250, 200, 50);
				coreInfo.getPanel().removeAll();
				coreInfo.getPanel().setLayout(new GridLayout(depositList.size() + loanList.size() + 4, 2));
				JLabel labelOne = new JLabel("Account Type:");
				coreInfo.getPanel().add(labelOne);
				JLabel labelTwo = new JLabel(theAccount.getAccountType());
				coreInfo.getPanel().add(labelTwo);
				//
				JLabel labelThree = new JLabel("Balance: ");
				coreInfo.getPanel().add(labelThree);
				JLabel labelFour = new JLabel("(in different currecy)");
				coreInfo.getPanel().add(labelFour);
				for (DepositInfo aDeposit : depositList) {
					JLabel currencyLabel = new JLabel(aDeposit.getCurrencyType());
					coreInfo.getPanel().add(currencyLabel);
					JLabel balanceLabel = new JLabel(Double.toString(aDeposit.getDepositAmount()));
					coreInfo.getPanel().add(balanceLabel);
				}
				//
				JLabel labelFive = new JLabel("Loan: ");
				coreInfo.getPanel().add(labelFive);
				JLabel labelSix = new JLabel("(in different currecy with interest)");
				coreInfo.getPanel().add(labelSix);
				for (LoanInfo aLoan : loanList) {
					JLabel currencyLabel = new JLabel(aLoan.getCurrencyType());
					coreInfo.getPanel().add(currencyLabel);
					JLabel balanceLabel = new JLabel(Double.toString(aLoan.getLoanAmount()) + " + " + aLoan.calculateInterest());
					coreInfo.getPanel().add(balanceLabel);
				}
				//
				JButton exitButton = new JButton("Back to Menu");
				coreInfo.getPanel().add(exitButton);
				ofinishButtonListener fAction = new ofinishButtonListener(this.coreInfo, this.cInfo, "Hello, ");
				exitButton.addActionListener(fAction);
				coreInfo.getPanel().updateUI();
				coreInfo.getFrame().setLayout(new FlowLayout());
			}
		}
	}

}
