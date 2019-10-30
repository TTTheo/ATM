public class SSN {
    private String ssn;

    public SSN(String s) {
        ssn = s;
    }

    public String getLastFourDigits() {
        return ssn.substring(6);
    }

    public boolean equals(SSN s) {
        return ssn.equals(s.ssn);
    }

    public boolean equals(String s) {
        return ssn.equals(s);
    }

    public static boolean isValidSSN(String s) {
        return s.length() == 10 && s.matches("\\d+");
    }
}
