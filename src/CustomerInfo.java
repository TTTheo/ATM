/*
 * The class that stores all of the information of a single customer in the bank
 * Because a single customer is allowed to have multiple accounts, the information of all
 * those accounts are stored inside an ArrayList, since it is mutable 
 */
import java.util.ArrayList;

public class CustomerInfo {
	private String customerID;
	private String passWord;
	public ArrayList<AccountInfo> accountList;
	
	CustomerInfo(){
		//do nothing 
	}
	
	CustomerInfo(String customerID, String passWord){
		this.customerID = customerID;
		this.passWord = passWord;
		this.accountList = new ArrayList<AccountInfo>();
	}
	
	public String getCustomerID() {
		return this.customerID;
	}
	
	public String getPassword() {
		return this.passWord;
	}
	
	public AccountInfo getAccountByName(String accountName) {
		AccountInfo temp = new AccountInfo();
		for (AccountInfo account : this.accountList) {
			if (account.getAccountName().equals(accountName)) {
					temp = account;
			}
		}
		return temp;
	}
	/*
	 * Because the banker should be given a report about the interest paid and gained
	 * for a single customer, the methods that do the calculations are placed inside this
	 * CustomerInfo class
	 */
	public void calculateDepoInterest(CoreInfo coreInfo) {
		for (AccountInfo account : this.accountList) {
			for (DepositInfo aDepo : account.getAccountBalanceList()) {
				if (account.getAccountType().equals("Saving Account")) {
					aDepo.makeDeposit(aDepo.calculateInterest(0.05));
					String newLog = "Pay Customer " + this.customerID + " " + aDepo.calculateInterest(0.05) + " in " + aDepo.getCurrencyType() + "\n";
					coreInfo.addInterestLog(newLog);
				} else {
					aDepo.makeDeposit(aDepo.calculateInterest(0.02));
					String newLog = "Pay Customer " + this.customerID + " " + aDepo.calculateInterest(0.02) + " in " + aDepo.getCurrencyType() + "\n"; 
					coreInfo.addInterestLog(newLog);
				}
			}
		}
	}
	
	public void calculateLoanInterest(CoreInfo coreInfo) {
		for (AccountInfo account : this.accountList) {
			for (LoanInfo aLoan : account.getAccountLoanList()) {
				String newLog = "Earn Loan Interest from Customer " + this.customerID + " " + aLoan.calculateInterest() + " in Currency " + aLoan.getCurrencyType() + "\n";
				coreInfo.addInterestLog(newLog);
			}
		}
	}
}
