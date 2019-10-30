public abstract class BankAccount {
    Money balance;
    int accountNum;

    public abstract void deposit(Money amt);
    public abstract void withdraw(Money amt);
    public abstract boolean hasMoney(Money amt);

    public String toString() {
        return "Account number: " + accountNum + " with balance: " + balance;
    }

    public boolean equals(int num) {
        return accountNum == num;
    }

    public static boolean isValidAccountNumber(String s) {
        return s.matches("\\d+");
    }

    public Money getBalance() {
       return balance;
    }

    public int getAccountNum() {
        return accountNum;
    }
}
