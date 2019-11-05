

public class Balance {
	private Currency dollar;
	private Currency rmb;
	private Currency Euro;
	
	public Balance(){
		dollar=new Currency("Dollar",0);
		rmb=new Currency("RMB",0);
		Euro=new Currency("Euro",0);
	}
	
	public Balance(Currency dollar,Currency rmb,Currency Euro){
		setDollar(dollar);
		 setRMB(rmb);
		 setEuro(Euro);
	}
		
	public void setDollar(Currency dollar){
		this.dollar=dollar;
	}
	
	public Currency getDollar(){
		return this.dollar;
	}
	
	public void setRMB(Currency rmb){
		this.rmb=rmb;
	}
	
	public Currency getRMB(){
		return this.rmb;
	}
	
	public void setEuro(Currency Euro){
		this.Euro=Euro;
	}
	
	public Currency getEuro(){
		return this.Euro;
	}
	
	public void add(Currency currency){     //add money in balance
		if(currency.getMark().equals("Dollar")){
			double money=getDollar().getMoney();
			getDollar().setMoney(money+currency.getMoney());
		}else if(currency.getMark().equals("RMB")){
			double money=getRMB().getMoney();
			getRMB().setMoney(money+currency.getMoney());
		}else if(currency.getMark().equals("Euro")){
			double money=getEuro().getMoney();
			getEuro().setMoney(money+currency.getMoney());
		}
	}
	
	public void substract(Currency currency){     //substract money in balance
		if(currency.getMark().equals("Dollar")){
			double money=getDollar().getMoney();
			getDollar().setMoney(money-currency.getMoney());
		}else if(currency.getMark().equals("RMB")){
			double money=getRMB().getMoney();
			getRMB().setMoney(money-currency.getMoney());
		}else if(currency.getMark().equals("Euro")){
			double money=getEuro().getMoney();
			getEuro().setMoney(money-currency.getMoney());
		}
	}
	
	public String showDollar(){
		String dollarbalance=getDollar().getMark()+":"+getDollar().getMoney();	
		return dollarbalance;
	}
	public String showRMB(){
		String rmbbalance=getRMB().getMark()+":"+getRMB().getMoney();	
		return rmbbalance;
	}
	public String showEuro(){
		String eurobalance=getEuro().getMark()+":"+getEuro().getMoney();	
		return eurobalance;
	}
	public String showAll(){
		String money=showDollar()+" "+showRMB()+" "+showEuro();
		return money;
	}
	
	
}
