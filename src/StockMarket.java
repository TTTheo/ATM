import java.util.ArrayList;


public class StockMarket {
	private ArrayList<Stock> stocks=new ArrayList<Stock>();
	private ArrayList<Customer> customers=new ArrayList<Customer>();
	
	public StockMarket(ArrayList<Stock> stocks){
		getStocks();
	}
	
	public ArrayList<Stock> getStocks(){
		return this.stocks;
	}
	
	public void addStock(Stock stock){
		stocks.add(stock);
	}
	
	public void deleteStock(Stock stock){
		for(int i=0;i<customers.size();i++){
			for(int j=0;j<customers.get(i).getInvest().getStock().size();j++){
				if(customers.get(i).getInvest().getStock().get(j).getCompany().equals(stock.getCompany())){
					Tool reminder=new Tool();
					reminder.reminder("Customer owe this stock, you can not delete the stock!");
				}else{
					//stocks.remove(stock);
					StockDao conn=new StockDao();
					conn.delete(stock.getCompany());
					Tool reminder=new Tool();
					reminder.reminder("Delete successfully!");
					
				}
			}
		}
	}
}
