
public class Money implements Comparable<Money> {
    Double amount;
    Currency currency;

    public Money() {
        this(0.0, new Currency());
    }

    public Money(String s) {
        this(Double.parseDouble(s), new Currency());
    }

    public Money(Double amt, Currency cur) {
        amount = amt;
        currency = cur;
    }

    public Money(Double amt, String cur) {
        amount = amt;
        currency = new Currency(cur);
    }

    public String toString() {
        return currency + " " + amount;
    }

    public int compareTo(Money m) {
        return (int) (amount - m.getAmount());
    }

    // Checks if some string is correctly formatted money (xx.yy)
    public static boolean isValidAmount(String s) {
        return s.matches("\\d+(\\.\\d(\\d)?)?");
    }

    public Double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void subtract(Money amt) {
        amount -= amt.getAmount();
    }

    public void add(Money amt) {
        amount += amt.getAmount();
    }
}
