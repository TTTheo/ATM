

public class Checking extends CheckandSave{
	private Balance balance;

	public Checking(String accountNumber,String moneypassword,Balance balance){
		super(accountNumber, moneypassword);
		setBalance(balance);
	}
	
	public void setBalance(Balance balance){
		this.balance=balance;
	}
	
	public Balance getBalance(){
		return balance;
	}
	
}
