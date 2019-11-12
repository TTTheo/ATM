

public class Income {
	private Currency income;
	private String type;
	
	public Income(Currency income,String type){
		this.income=income;
		this.type=type;
	}
	
	public void setIncome(Currency income){
		this.income=income;
	}
	
	public Currency getIncome(){
		return this.income;
	}
	
	public void setType(String type){
		this.type=type;
	}
	
	public String getType(){
		return this.type;
	}
	
	public String showIncome(){   //show the income
		String show="Type:"+getType()+"\n"+"Amount:"+getIncome().getMoney()+" "+getIncome().getMark()+"\n";
		return show;
	}

}
