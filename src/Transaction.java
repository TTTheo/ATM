

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private Currency trans;
	private Date date;
	private String sendAccount;
	private String recieAccount;
	//private User senderAccount;
	//private User recieveAccount;
	private String transID;
	
	public Transaction(Currency trans, Date date,String sendAccount,String recieAccount,String transID){
	//public Transaction(Currency trans, Date date,String sendAccount,String recieAccount){
		this.trans=trans;
		this.date=date;
		//this.senderAccount=senderAccount;
		//this.recieveAccount=recieveAccount;
		this.sendAccount=sendAccount;
		this.recieAccount=recieAccount;
		this.transID=transID;
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
	
	/*public User getRecieveAccount(){
		return this.recieveAccount;
	}
	
	public User getSendAccount(){
		return this.senderAccount;
	}*/
	
	public String getRecieAccount(){
		return this.recieAccount;
	}
	
	public String getSenAccount(){
		return this.sendAccount;
	}
	
	public String getTransID(){
		return this.transID;
	}
	
	
	public String showTrans(){   //show single transaction
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		String dateStr=sdf.format(getDate());
		String showtrans="("+getSenAccount()+")--> "
				+"("+getRecieAccount()+")\r\nAmount:"
				+getTransaction().getMark()+":"+(getTransaction().getMoney()-5)+"\r\n"
				+"Date:"+dateStr+"\r\n";
		return showtrans;
	}
}
