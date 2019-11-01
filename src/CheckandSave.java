

public class CheckandSave {
	private Account user;
	private String moneypassword;
	private String accountNumber;
	private Balance balance;
	
	public CheckandSave(Account user,String accountNumber,String moneypassword,Balance balance){
		this.user=user;
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
	
	public void withdreaw(Currency currency){
		balance.substract(currency);
	}
	
	public String getAccountNumber(){
		return this.accountNumber;
	}
	
	public String getMoneypassword(){
		return this.moneypassword;
	}
	
	public Account getAccount(){
		return this.user;
	}
	
	public void setUser(Account user){
		this.user=user;
	}
	
}
