import java.util.ArrayList;


public class Investment {
	private ArrayList<CustomerStock> stocks=new ArrayList<CustomerStock>();
	
	public Investment(ArrayList<CustomerStock> stocks){
		setStock(stocks);
	}
	
	public void setStock(ArrayList<CustomerStock> stocks){
		this.stocks=stocks;
	}
	
	public ArrayList<CustomerStock> getStock(){
		return this.stocks;
	}
	
	//add stock
	public void addStock(CustomerStock stock){
		boolean ifsame=false;
		int j=0;
		//get if customer have such stock, update if yes, add if no
		for(int i=0;i<getStock().size();i++){
			if(getStock().get(i).getCompany().equals(stock.getCompany())){
				ifsame=true;
				j=i;
				break;
			}
		}
		if(!ifsame){
			stocks.add(stock);
		}else{
			getStock().get(j).setNumofStock(getStock().get(j).getNumofStock()+stock.getNumofStock());
		}
		
	}
	
	public void deleteStock(CustomerStock stock){	
		int j=0;
		//get if customer have such stock
		for(int i=0;i<getStock().size();i++){
			if(getStock().get(i).getCompany().equals(stock.getCompany())){
				j=i;
				break;
			}
		}
		if(getStock().get(j).getNumofStock()-stock.getNumofStock()==0){
			//if the amount is 0, delete the stock from customer
			getStock().remove(getStock().get(j));
		}else{
			//else update
			getStock().get(j).setNumofStock(getStock().get(j).getNumofStock()-stock.getNumofStock());
		}
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
