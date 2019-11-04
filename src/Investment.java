import java.util.ArrayList;


public class Investment {
	private ArrayList<CustomerStock> stocks=new ArrayList<CustomerStock>();
	//private User user;
	private Customer customer;
	private String accountID;
	
	public Investment(ArrayList<CustomerStock> stocks,Customer customer){
		setStock(stocks);
		setCustomer(customer);
	}
	
	public void setStock(ArrayList<CustomerStock> stocks){
		this.stocks=stocks;
	}
	
	public ArrayList<CustomerStock> getStock(){
		return this.stocks;
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	public void setCustomer(Customer user){
		this.customer=user;
	}
	
	public void addStock(CustomerStock stock){
		double checking=getCustomer().getChecking().getBalance().getDollar().getMoney();
		double stockmoney=stock.getPrice()*stock.getNumofStock()+5;
		if(checking<stockmoney){
			Tool reminder=new Tool();
			reminder.reminder("You do not have enough money!");
		}else{
			//add stock to customer
			//substract balance
		}
	}
	
	public void deleteStock(CustomerStock stock){
		double stockmoney=stock.getPrice()*stock.getNumofStock()-5;
		getStock().remove(stock);
		getCustomer().getChecking().getBalance().add(new Currency("Dollar",stockmoney));
	}

}
