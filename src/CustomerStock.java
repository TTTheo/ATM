
public class CustomerStock extends Stock{
	private int numofStock;
	
	public CustomerStock(String company, double price,int numofStock){
		super(company,price);
		setNumofStock(numofStock);
	}
	
	public void setNumofStock(int numofStock){
		this.numofStock=numofStock;
	}
	
	public int getNumofStock(){
		return this.numofStock;
	}
}
