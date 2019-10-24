import java.util.*;

public class User {
	private String name;
	private List<Account> accounts;
	
	
	// constructor
	public User(String name) {
		this.name = name;
		this.accounts = new ArrayList<>();
	}
	// create new account for this user
	public void createAccount(Bank bank, String type, String id) {
		switch (type) {
			case ("Saving"):
				Account sa = new SavingAccount(bank, this.name, id);
				accounts.add(sa);
				break;
			case ("Checking"):
				Account ca = new CheckingAccount(bank, this.name, id);
				accounts.add(ca);
				break;
		}
	}
	
	// get Account by id
	public Account getByID(String id) {
		for(Account account : accounts) {
			if(account.getId().equals(id))
				return account ;
		}
		return null ;
	}

	// setters and getters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
