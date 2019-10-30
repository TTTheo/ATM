public class CheckingAccount extends BankAccount {
    public CheckingAccount(int id) {
        accountNum = id;
        balance = new Money();
    }
    public void deposit(Money amt) {
        balance.add(amt);
    }
    public void withdraw(Money amt) {
        balance.subtract(amt);
    }
    public boolean hasMoney(Money amt) {
        if (balance.compareTo(amt) >= 0) {
            return true;
        }
        return false;
    }
}
