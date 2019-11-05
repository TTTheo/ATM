import java.util.ArrayList;


public class Investment extends CheckandSave{
	private ArrayList<CustomerStock> stocks=new ArrayList<CustomerStock>();
	//private Customer customer;
	//private String accountID;
	private Tool reminder=new Tool();
	private StockDao conn=new StockDao();
	
	public Investment(String accountNumber,String moneypassword,ArrayList<CustomerStock> stocks){
		super(accountNumber, moneypassword);
		setStock(stocks);
	}
	
	public void setStock(ArrayList<CustomerStock> stocks){
		this.stocks=stocks;
	}
	
	public ArrayList<CustomerStock> getStock(){
		return this.stocks;
	}
	
	/*public User getUser(){
		return this.user;
	}
	
	public void setUser(User user){
		this.user=user;
	}
	
	public String getAccountID(){
		return this.accountID;
	}
	
	public void setAccountID(String accountID){
		this.accountID=accountID;
	}
	
	/*public Customer getCustomer(){
		return this.customer;
	}
	
	public void setCustomer(Customer user){
		this.customer=user;
	}*/
	
	public void addStock(CustomerStock stock){
		stocks.add(stock);
	}
	
	public void deleteStock(CustomerStock stock){
		//double stockmoney=stock.getPrice()*stock.getNumofStock()-5;
		getStock().remove(stock);
		//getCustomer().getChecking().getBalance().add(new Currency("Dollar",stockmoney));
	}
	
	public String printStock(){
		String print="";
		for(int i=0;i<getStock().size();i++){
			print+="Company: "+stocks.get(i).getCompany()+"\nPrice: "+stocks.get(i).getPrice()+
					" Dollars\nAmount: "+stocks.get(i).getNumofStock()+"\n-----------------------------";
		}
		return print;
	}

}
