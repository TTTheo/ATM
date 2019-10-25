/*
 * The class that stores all the information of a single deposit the customer made
 * The reason for creating a new instance for each deposit is for the better management of
 * customer's deposit of different currencies
 */
public class DepositInfo {
	private String currencyType;
	private double depositAmount;
	
	DepositInfo(String currencyType, double depositAmount){
		this.currencyType = currencyType;
		this.depositAmount = depositAmount;
	}
	
	public String getCurrencyType() {
		return this.currencyType;
	}
	
	public double getDepositAmount() {
		return this.depositAmount;
	}
	
	public void makeTransaction (double transAmount) {
		this.depositAmount -= transAmount;
	}
	
	public void makeDeposit (double depositAmount) {
		this.depositAmount += depositAmount;
	}
	
	public double calculateInterest (double rate) {
		return this.depositAmount * rate;
	}
}
