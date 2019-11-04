

public class Manager{
	private User user;
	
	public Manager(String name, String username,String password,String phone){
		this.setUser(new User(name,username,password,phone));
	}
	
	public void setUser(User user){
		this.user=user;		
	}
	
	public User getUser(){
		return this.user;	
	}
	
}
