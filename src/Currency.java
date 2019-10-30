public class Currency {
    String code;

    public static final String USD = "USD";
    public static final String GBP = "GBP";
    public static final String JPY = "JPY";

    // Conversion rates for 1 currency to USD
    public static final double JPY_TO_USD = 0.0092;
    public static final double GBP_TO_USD = 1.30;

    public Currency() {
        code = USD;
    }

    public Currency(String cur) {
        code = cur;
    }

    public boolean equals(Currency cur) {
        return cur.getCode().equals(code);
    }

    public boolean equals(String cur) {
        return code.equals(cur);
    }

    public Money convertToUSD(Money amt) {
        double converted = amt.getAmount();
        Currency cur = amt.getCurrency();
        if (cur.equals(GBP)) {
            converted *= GBP_TO_USD;
        } else if (cur.equals(JPY)) {
            converted *= JPY_TO_USD;
        }
        return new Money(converted, new Currency());
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return code;
    }
}
