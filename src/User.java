import java.util.ArrayList;
public class User {
    private SSN id;
    private ArrayList<BankAccount> accounts;
    private ArrayList<Transaction> transactions;
    private Money loan;
    private Money cash;  // How much money a user has in total
    private boolean collateral;

    // Creates user with default $1000 and collateral
    public User(String s) {
        this(new SSN(s), new Money("20000"), true);
    }

    public User(SSN s, Money m, boolean c) {
        id = s;
        cash = m;
        collateral = c;
        accounts = new ArrayList<BankAccount>();
        transactions = new ArrayList<Transaction>();
        loan = new Money();
    }

    public String toString() {
        return "User " + id.getLastFourDigits() + " has " + cash;
    }

    public SSN getId() {
        return id;
    }

    public int numAccounts() {
        return accounts.size();
    }


    public void addAccount(BankAccount a) {
        accounts.add(a);
    }

    public void removeAccount(BankAccount a) {
        accounts.remove(a);
    }

    public boolean hasAccount(int accNum) {
        for (BankAccount b: accounts) {
            if (b.equals(accNum)) {
                return true;
            }
        }
        return false;
    }

    // Assumes an account with accNum exists, from call to hasAccount prior
    public BankAccount getAccount(int accNum) {
        for (BankAccount b: accounts) {
            if (b.equals(accNum)) {
                return b;
            }
        }
        return null;
    }

    public boolean hasMoney(Money amt) {
        if (cash.compareTo(amt) >= 0) {
            return true;
        }
        return false;
    }

    public Money getCash() {
        return cash;
    }

    public Money getLoan() {
        return loan;
    }

    public boolean hasCollateral() {
        return collateral;
    }

    public boolean equals(String s) {
        return id.equals(s);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }
}

