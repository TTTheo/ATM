
public class Stock {
	private String company;
	private double price;
	
	public Stock(String company, double price){
		setCompany(company);
		setPrice(price);
	}
	
	public String getCompany(){
		return this.company;
	}
	
	public void setCompany(String company){
		this.company=company;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice(double price){
		this.price=price;
	}
}
