

public class Currency {
	private String mark;
	private double money;
	
	public Currency(String mark, double money){
		this.mark=mark;
		this.money=money;
	}
	
	public void getMark(String mark){
		this.mark=mark;
	}
	
	public String getMark(){
		return this.mark;
	}
	
	public void setMoney(double money){
		this.money=money;
	}
	
	public double getMoney(){
		return this.money;
	}
}
