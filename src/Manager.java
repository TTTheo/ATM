

public class Manager{
	private Account user;
	
	public Manager(String name, String username,String password,String phone){
		this.setUser(new Account(name,username,password,phone));
	}
	
	public void setUser(Account user){
		this.user=user;		
	}
	
	public Account getUser(){
		return this.user;	
	}
	
}
