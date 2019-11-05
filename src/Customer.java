

import java.util.ArrayList;

public class Customer{
	private User user;
	private Checking checkingAccount;
	private Saving savingAccount;
	private ArrayList<Loan> loans=new ArrayList<Loan>();
	private ArrayList<Transaction> transactions=new ArrayList<Transaction>();
	private Investment invest;
	
	public Customer(String name, String username,String password,String phone){
		this.setUser(new User(name,username,password,phone));
	}
	
	public void setUser(User user){
		this.user=user;		
	}
	
	public User getUser(){
		return this.user;	
	}
	
	public void createChecking(Checking checking){
		//checkingAccount=new Checking();
		this.checkingAccount=checking;
	}
	
	public void createSaving(Saving saving){
		this.savingAccount=saving;
	}
	
	public Checking getChecking(){
		return this.checkingAccount;
	}
	
	public Saving getSaving(){
		return this.savingAccount;
	}
	
	public void addLoan(Loan loan){
		loans.add(loan);
	}
	
	public ArrayList<Loan> getLoans(){
		return this.loans;
	}
	
	public void addTransaction(Transaction transaction){
		transactions.add(transaction);
	}
	
	public ArrayList<Transaction> getTransaction(){
		return this.transactions;
	}
	
	public Investment getInvest(){
		return this.invest;
	}
	
	public void createInvest(Investment invest){
		this.invest=invest;
	}
	
	public String showCustomer(){   //show customer's total information
		String info=showCustomerInfo();
		info+=showCustomerCheck();
		if(getSaving()!=null){
			info+=showCustomerSave();
		}
		return info;
	}
	
	public String showCustomerInfo(){   //show basic information
		String info="======================\r\nName: "+getUser().getName()+"\r\nPhone: "+getUser().getPhone()
				+"\r\nUsername: "+getUser().getUsername()+"\r\n";
		return info;
	}
	
	public String showCustomerCheck(){   //show checking information
		String info="Chencking account: "+getChecking().getAccountNumber()
				+"\r\nChencking balance: \r\n"+getChecking().getBalance().showAll()
				+"\r\n";
		return info;
	}
	  
	public String showCustomerSave(){   //show saving information
		String info="Saving account: "+getSaving().getAccountNumber()
				+"\r\nSaving balance: \r\n"+getSaving().getBalance().showAll()
				+"\r\n";
		return info;
	}
	
	public String showLoan(){    //show customers loans
		String[] loan=new String[loans.size()];
		String showloan="";
		if(loans.size()==0){
			showloan="No loans.";
		}else{
			for(int i=0;i<loans.size();i++){
				loan[i]="Amount:"+loans.get(i).getLoan().getMark()+" "+loans.get(i).getLoan().getMoney()
						+"\r\nLength:"+loans.get(i).getLoanLength()+" months"
						+"\r\nInterest:"+loans.get(i).getIntesest()
						+"\r\nCollateral:"+loans.get(i).getCollateral()+"\r\n\r\n";
				showloan+=loan[i];
			}
		}
		return showloan;
	}
	
	public String showTrans(){   //show customers transactions
		String[] trans=new String[transactions.size()];
		String showtrans="";
		if(transactions.size()==0){
			showtrans="No transactions.";
		}else{
			for(int i=0;i<transactions.size();i++){
				trans[i]="------------------------\r\n"+transactions.get(i).showTrans();
				showtrans+=trans[i];
			}
		}
		return showtrans;
	}
}
