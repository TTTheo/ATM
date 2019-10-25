/*
 * The class that stores all the information of a single loan the customer made
 * The reason for creating a new instance for each loan is for the better management of
 * customer's loan of different currencies
 */
import java.util.ArrayList;

public class LoanInfo {
	private String currencyType;
	private double loanAmount;
	final private double interestRate = 0.05;
	
	LoanInfo(String currencyType, double loanAmount){
		this.currencyType = currencyType;
		this.loanAmount = loanAmount;
	}
	
	public String getCurrencyType() {
		return this.currencyType;
	}
	
	public double getLoanAmount() {
		return this.loanAmount;
	}
	
	public double calculateInterest() {
		return this.loanAmount * this.interestRate;
	}
}
