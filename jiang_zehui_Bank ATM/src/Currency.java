public class Currency {
	private String name ;
	private double amount ;
	private double loanAmount ; 
	private int loanTime ;
	private double rate ;
	
	// constructor
	public Currency(String name, double amount, double rate) {
		this.name = name ;
		this.amount = amount ;
		this.rate = rate ;
		this.amount = 0 ;
		this.loanAmount = 0 ;
	}
	// gathers currency information
	public String toString() {
		String result = name + "\n" + "Amount: " + amount + "\n" + "Loan: " + loanAmount + "\n" + "Loan Time: " + loanTime + " day(s)" + "\n" ;
		return result ;
	}
	
	// setters and getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public int getLoanTime() {
		return loanTime;
	}
	public void setLoanTime(int loanTime) {
		this.loanTime = loanTime;
	}
	
}
