

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private Currency trans;
	private Date date;
	private String sendAccount;
	private String receiAccount;
	private User senderAccount;
	private User recieveAccount;
	private String transID ;
	private int idCount = 0 ;
	
	public Transaction(Currency trans, Date date,User senderAccount,User recieveAccount,String sendAccount,String recieAccount){
		this.trans=trans;
		this.date=date;
		this.senderAccount=senderAccount;
		this.recieveAccount=recieveAccount;
		this.sendAccount=sendAccount;
		this.receiAccount=recieAccount;
		this.transID = idCount++ + "" ;
	}
	
	public void setTransaction(Currency trans){
		this.trans=trans;
	}
	
	public Currency getTransaction(){
		return this.trans;
	}
	
	public void setDate(Date date){
		this.date=date;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public User getRecieveAccount(){
		return this.recieveAccount;
	}
	
	public User getSendAccount(){
		return this.senderAccount;
	}
	
	public String getRecieAccount(){
		return this.receiAccount;
	}
	
	public String getSenAccount(){
		return this.sendAccount;
	}
	
	public String getTransID() {
		return transID;
	}

	public void setTransID(String transID) {
		this.transID = transID;
	}

	public String showTrans(){   //show single transaction
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String dateStr=sdf.format(getDate());
		String showtrans=getSendAccount().getName()+"("+getSenAccount()+")--> "
				+getRecieveAccount().getName()+"("+getRecieAccount()+")\r\nAmount:"
				+getTransaction().getMark()+":"+(getTransaction().getMoney()-5)+"\r\n"
				+"Date:"+dateStr+"\r\n";
		return showtrans;
	}
}
