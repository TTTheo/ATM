/*
 * The class that holds the information of a single bank account opened by customer
 * The account has two types: Saving account and Checking account
 */

import java.util.ArrayList;

public class AccountInfo {
	private String accountType;
	private String accountName;
	private ArrayList<DepositInfo> accountBalanceList;
	private ArrayList<LoanInfo> accountLoanList;
	
	AccountInfo(){
		//do nothing
	}
	
	/*
	 * Besides the fields that holds the information of the type and the name of this account,
	 * accountBalanceList is used to store the instances of DepositInfo, which holds the information of various
	 * deposit in various currency; accountLoanList functions almost the same, except it is for instances of 
	 * the LoanInfo class
	 */
	AccountInfo(String accountType, String accountName){
		this.accountType = accountType;
		this.accountName = accountName;
		this.accountBalanceList = new ArrayList<DepositInfo>();
		this.accountLoanList = new ArrayList<LoanInfo>();
	}
	
	/*
	 * Normal Setter and Getter methods for the fields in this class
	 */
	public String getAccountType() {
		return this.accountType;
	}
	
	public String getAccountName() {
		return this.accountName;
	}
	
	public ArrayList<DepositInfo> getAccountBalanceList() {
		return this.accountBalanceList;
	}
	
	public void addDeposit(DepositInfo deposit) {
		this.accountBalanceList.add(deposit);
	}
	
	public ArrayList<LoanInfo> getAccountLoanList() {
		return this.accountLoanList;
	}
	
	public void addLoan(LoanInfo loan) {
		this.accountLoanList.add(loan);
	}
}
