public class Transaction {
    // Transactions include any movement of money, eg repaying loans, depositing money, and withdrawing money
    // Eg. withdrew $500 from account 3
    SSN userId;
    Money amount;
    String action;
    int bankAccountNum;

    public static final String DEPOSITED = "deposited";
    public static final String WITHDREW = "withdrew";
    public static final String REPAID = "repaid";
    public static final String BORROWED = "borrowed";
    public static final String CLOSED = "closed";

    public Transaction(SSN u, Money m, String a) {
        this(u, m, a, -1);
    }

    public Transaction(SSN u, Money m, String a, int b) {
        userId = u;
        amount = m;
        action = a;
        bankAccountNum = b;
    }

    public String toString() {
        if (bankAccountNum >= 0) {
            return "User " + userId.getLastFourDigits()
                    + " " +  action + " " + amount
                    + " on Account " + bankAccountNum;
        }
        return "User " + userId.getLastFourDigits()
                + " " + action + " " + amount;
    }
}
