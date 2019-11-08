

public class Saving extends CheckandSave{

	public Saving(String accountNumber,String moneypassword,Balance balance){
		super(accountNumber, moneypassword, balance);

	}	
	
	public String showCustomerSave(){   //show saving information
		String info="Saving account: "+getAccountNumber()
				+"\r\nSaving balance: \r\n"+getBalance().showAll()
				+"\r\n";
		return info;
	}
}
