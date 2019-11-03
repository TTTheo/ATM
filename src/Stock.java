
public class Stock {
	private double price ;
	private String company ;
	public Stock(String company, double price) {
		this.company = company ;
		this.price = price ;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
