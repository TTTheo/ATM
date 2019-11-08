

public class CheckandSave {
	private String moneypassword;
	private String accountNumber;
	private Balance balance;
	
	public CheckandSave(String accountNumber,String moneypassword,Balance balance){
		this.moneypassword=moneypassword;
		this.accountNumber=accountNumber;
		this.balance=balance;
	}
	
	public void setBalance(Balance balance){
		this.balance=balance;
	}
	
	public Balance getBalance(){
		return balance;
	}
	
	public void deposit(Currency currency){
		balance.add(currency);
	}
	
	public void withdraw(Currency currency){
		balance.substract(currency);
	}
	
	public String getAccountNumber(){
		return this.accountNumber;
	}
	
	public String getMoneypassword(){
		return this.moneypassword;
	}
	
	
}
