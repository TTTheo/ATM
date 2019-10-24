import java.util.*;
public class Account {
	private Bank bank ;
	private String userName ;
	private String id ;
	private List<Currency> currencies ;
	private double withdrawFee  ;
	private double exchangeFee ;
	private double loanInterest = 0.01 ;
	private double transferFee = 20 ;
	private String type ;
	private HashMap<String, Double> currencyMap ;
	private double interest = 0 ;
	private int interestTime = 0;
	
	// constructor
	public Account(Bank bank, String userName, String id, String type,  double withdrawFee, double exchangeFee) {
		this.bank = bank ;
		this.userName = userName ;
		this.id = id ;
		this.type = type ;
		this.withdrawFee = withdrawFee ;
		this.exchangeFee = exchangeFee ;
		currencies = new ArrayList<>() ;
		currencyMap = new HashMap<>() ;
		currencyMap.put("US Dollar", 1.0) ;
		currencyMap.put("Euro", 1.11) ;
		currencyMap.put("Pound", 1.29) ;
		
		for(String s : currencyMap.keySet()) {
			currencies.add(new Currency(s, 0, currencyMap.get(s))) ;
		}
		
	}
	// all info of this account
	public String toStirng() {
		String info = userName + " " + type + " " + id ;
		return info;
		
	}
	// get Currency object by name
	public Currency getByName(String name) {
		for(Currency c : currencies) {
			if(c.getName().equals(name))
				return c ;
		}
		return null ;
	}
	// the if interest rate needs change
	public void checkInterestRate() {
		if(getType().equals("Saving")) {
			double sum = 0 ;
			for(Currency c : currencies) {
				sum += c.getAmount()*c.getRate() ;
			}
			if(sum >= 5000) {
				setInterest(0.05);
				setInterestTime(1);
			}
			else {
				setInterest(0);
				setInterestTime(0);
			}
		}
	}
	
// all kinds of transactions
	// deposit
	public void deposit(String name, double amount) {
		Currency curr = getByName(name) ;
		curr.setAmount(curr.getAmount() + amount);
		// add action to transaction history
		bank.getTransaction().add(this.toStirng() + " deposit " + amount + " " + name) ;
		checkInterestRate();
	}
	
	// withdraw
	public boolean withdraw(String name, double amount){
		Currency curr = getByName(name) ;
		double fee = this.withdrawFee/curr.getRate() ;
		if(curr.getAmount() >= (amount + fee)) {
			curr.setAmount(curr.getAmount() - fee - amount);
			// add action to transaction history
			HashMap<String, Double> map = bank.getProfitMap() ;
			map.put(name, map.get(name) + fee) ;
			bank.getTransaction().add(this.toStirng() + " withdraw " + amount + " " + name) ;
			checkInterestRate();
			return true ;
		}else return false ;
	}
	// transfer to another account
	public boolean transfer(double transferAmount, Account to, String name){
		Currency from_curr = getByName(name) ;
		Currency to_curr = to.getByName(name) ;
		double fee = this.transferFee/from_curr.getRate() ;
		if(from_curr.getAmount() >= transferAmount + fee) {
			from_curr.setAmount(from_curr.getAmount() - transferAmount - fee);
			to_curr.setAmount(to_curr.getAmount() + transferAmount);
			// add action to transaction history
			HashMap<String, Double> map = bank.getProfitMap() ;
			map.put(name, map.get(name) + fee) ;
			bank.getTransaction().add(this.toStirng() + " transfer " + transferAmount + " " + name + " to " + to.toStirng()) ;
			checkInterestRate();
			to.checkInterestRate();
			return true ;
		}else return false ;
	}
	// loan money
	public void loan(double loanAmount, String name) {
		Currency curr = getByName(name) ;
		curr.setLoanTime(1);
		curr.setLoanAmount(loanAmount + loanAmount*loanInterest*curr.getLoanTime());
		// add action to transaction history
		curr.setAmount(curr.getAmount() + loanAmount) ;
		HashMap<String, Double> map = bank.getProfitMap() ;
		map.put(name, map.get(name) + loanAmount*loanInterest*curr.getLoanTime()) ;
		bank.getTransaction().add(this.toStirng() + " loan " + loanAmount + " " + name) ;
		checkInterestRate();
	}
	// repay loan
	public boolean payLoan(String name) {
		Currency curr = getByName(name) ;
		double amount = curr.getLoanAmount() ;
		if(curr.getAmount() >= amount) {
			curr.setAmount(curr.getAmount() - amount);
			curr.setLoanAmount(0);
			// add action to transaction history
			bank.getTransaction().add(this.toStirng() + " repay " + amount + " " + name) ;
			checkInterestRate();
			return true ;
		}else return false ;
		
	}
	// currency exchange
	public boolean exchange(double exchangeAmount, String from, String to) {
		Currency cFrom = getByName(from) ;
		Currency cTo = getByName(to) ;
		double fee = this.exchangeFee/cFrom.getRate() ;
		if(cFrom.getAmount()>= fee + exchangeAmount) {
			cFrom.setAmount(cFrom.getAmount() - fee - exchangeAmount);
			cTo.setAmount(cTo.getAmount() + exchangeAmount*cFrom.getRate()/cTo.getRate());
			// add action to transaction history
			HashMap<String, Double> map = bank.getProfitMap() ;
			map.put(from, map.get(from) + fee) ;
			bank.getTransaction().add(this.toStirng() + " exchanged " + exchangeAmount + " " + from + " to " + exchangeAmount*cFrom.getRate()/cTo.getRate() + " " + to);
			checkInterestRate();
			return true ;
		}else return false ;
	}
	
	// setters and getters
	public String getUserName() {
		return userName;
	}

	public double getWithdrawFee() {
		return withdrawFee;
	}

	public void setWithdrawFee(double withdrawFee) {
		this.withdrawFee = withdrawFee;
	}


	public double getLoanInterest() {
		return loanInterest;
	}

	public void setLoanInterest(double loanInterest) {
		this.loanInterest = loanInterest;
	}

	public HashMap<String, Double> getCurrencyMap() {
		return currencyMap;
	}

	public void setCurrencyMap(HashMap<String, Double> currencyMap) {
		this.currencyMap = currencyMap;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getTransferFee() {
		return transferFee;
	}

	public void setTransferFee(double transferFee) {
		this.transferFee = transferFee;
	}
	public double getExchangeFee() {
		return exchangeFee;
	}
	public void setExchangeFee(double exchangeFee) {
		this.exchangeFee = exchangeFee;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public int getInterestTime() {
		return interestTime;
	}
	public void setInterestTime(int interestTime) {
		this.interestTime = interestTime;
	}
	
}
