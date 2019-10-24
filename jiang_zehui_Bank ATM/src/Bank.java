import java.util.*;
public class Bank {
	private List<User> users ;
	private List<String> ids ;
	private HashMap<String, Double> profitMap = new HashMap<>() ;;
	private List<String> transaction ;
	// constructor
	public Bank() {
		users = new ArrayList<>() ;
		ids = new ArrayList<>() ;
		transaction = new ArrayList<>() ;
		profitMap.put("US Dollar", 0.0) ;
		profitMap.put("Pound", 0.0) ;
		profitMap.put("Euro", 0.0) ;
		testSetup();
	}
	
	// generate unique account id
	public String generateID() {
		String id = "" ;
		Random ran = new Random() ;
		while(true) {
			id = ran.nextInt(Integer.MAX_VALUE) + "" ;
			if(!ids.contains(id))
				break ;
		}
		ids.add(id) ;
		return id ;
	}
	
	// set up test accounts
	public void testSetup() {
		User morty = createUser("Morty") ;
		morty.createAccount(this, "Checking", "1");
		morty.createAccount(this, "Saving", "2");
		User rick = createUser("Rick") ;
		rick.createAccount(this, "Checking", "11");
		rick.createAccount(this, "Saving", "22");
		for(Account account : rick.getAccounts()) {
			account.deposit("US Dollar", 5000);
			account.deposit("Pound", 5000);
			account.deposit("Euro", 5000);
		}
	}
	// create new user
	public User createUser(String name) {
		User user = new User(name) ;
		users.add(user) ;
		return user ;
	}
	// get User object by the name
	public User getByName(String name) {
		for(User user :users) {
			if(user.getName().equals(name))
				return user ;
		}
		return null ;
	}
	
	// setters and getters
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public List<String> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<String> transaction) {
		this.transaction = transaction;
	}
	public HashMap<String, Double> getProfitMap() {
		return profitMap;
	}
	public void setProfitMap(HashMap<String, Double> profitMap) {
		this.profitMap = profitMap;
	}
	
}
