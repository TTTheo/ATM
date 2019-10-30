import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// "handle" methods in Bank directly interact with the GUI (ie. the frontend)
// "manage" methods in Bank are responsible for state changes (ie. the backend)

public class Bank {
    private ATM bankATM;  // The GUI

    private Money interestAmt;  // Amount needed to gain interest (Savings acc)
    private double interestRate;  // used to calculate loans and savings interest
    private Money fee;  // Fee incurred with opening accounts, etc.

    private User manager;  // the manager of this bank
    private User currentUser;  // the "logged in" user
    private BankAccount currentAccount;  // The account the current user is viewing

    // Transactions for managers to view. Resets when manager has viewed
    private ArrayList<Transaction> transactionsLog;
    private ArrayList<User> allUsers;
    private int numAccounts;  // Used to set unique account ids

    final String welcomeText = "Welcome to FancyBank\n"
            + "(1) Login\n"
            + "(2) Register\n"
            + "(3) Manager login\n";

    final String mainText = "User options\n"
            + "(1) Account Management\n"
            + "(2) Loan Management\n"
            + "(3) View Transactions\n"
            + "(4) Logout\n";

    final String accountText = "What would you like to do?\n"
            + "(1) Create Savings Account (includes fee)\n"
            + "(2) Create Checking Account (includes fee)\n"
            + "(3) Access Existing Account\n"
            + "(4) Back\n";

    final String loanText = "What would you like to do?\n"
            + "(1) Request Loan\n"
            + "(2) Repay Loan\n"
            + "(3) \n"
            + "(4) Back\n";

    final String accessText = "What would you like to do?\n"
            + "(1) Deposit money (includes fee for checking accounts)\n"
            + "(2) Withdraw money (includes fee)\n"
            + "(3) Close account (includes fee)\n"
            + "(4) Back\n";

    final String managerOptions = "What would you like to do?\n"
            + "(1) Lookup customer\n"
            + "(2) Check all customers\n"
            + "(3) Get transaction report\n"
            + "(4) Move forward 1 month (and pay/collect interest)\n";

    public Bank(ATM a) {
        bankATM = a;
        JFrame frame = new JFrame("ATM");
        frame.setContentPane(bankATM.ATM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);

        interestAmt = new Money("10000"); // interest for savings accounts with 10k USD
        interestRate = .10;
        fee = new Money("50"); // Every fee is USD 50
        allUsers = new ArrayList<User>();
        transactionsLog = new ArrayList<Transaction>();
        numAccounts = 0;
    }

    public boolean hasUser(String ssn) {
        for (User user: allUsers) {
            if (user.equals(ssn)) {
                return true;
            }
        }
        return false;
    }

    // Assumes a user with ssn exists, from call to hasUser prior
    public User getUser(String ssn) {
        for (User user: allUsers) {
            if (user.equals(ssn)) {
                return user;
            }
        }
        return null;
    }

    // Handles creation of new user or "logging in" of existing user
    // Action is either login or register
    public void manageUser(String action) {
        // validate user label
        String input = bankATM.getUserInput();
        System.out.println("User action is " + action);

        String error = "";
        if (SSN.isValidSSN(input)) {
            if (action.equals("login")) {
                if (hasUser(input)) {
                    currentUser = getUser(input);
                    System.out.println("Current user is " + currentUser);
                    handleMain();
                } else {
                    error = "No user found for " + input;
                }
            } else if (action.equals("register")) {
                if (hasUser(input)) {
                    error = "User with that ID already exists";
                } else {
                    User newUser = new User(input);
                    currentUser = newUser;
                    allUsers.add(newUser);
                    System.out.println("Current user is " + currentUser);
                    handleMain();
                }
            }
        } else {
            error = "Invalid input";
        }

        // Else, go to error screen
        if (!error.equals("")) {
            handleError(error);
        }
    }

    // Request or repay loans
    public void manageLoan(String action) {
        String input = bankATM.getUserInput();

        String error = "";
        if (Money.isValidAmount(input)) {
            Money amt = new Money(input);
            if (action.equals("request")) {
                if (currentUser.hasCollateral()) {
                    currentUser.getLoan().add(amt);
                    currentUser.getCash().add(amt);

                    Transaction t = new Transaction(
                            currentUser.getId(),
                            amt,
                            Transaction.BORROWED
                    );
                    currentUser.addTransaction(t);
                    transactionsLog.add(t);

                    handleLoan();
                } else {
                    error = "Unable to request, no collateral!";
                }
            } else if (action.equals("repay")) {
                if (currentUser.hasMoney(amt) && (currentUser.getLoan().compareTo(amt) >= 0)) {
                    currentUser.getCash().subtract(amt);
                    currentUser.getLoan().subtract(amt);
                    manager.getCash().add(amt);

                    Transaction t = new Transaction(
                            currentUser.getId(),
                            amt,
                            Transaction.REPAID
                    );
                    currentUser.addTransaction(t);
                    transactionsLog.add(t);

                    handleLoan();
                } else {
                    error = "Unable to repay, you're either trying to repay\n"
                            + "too much, or you need to withdraw more money!";
                }
            }
        } else {
            error = "Invalid Input";
        }
        // Else, go to error screen
        if (!error.equals("")) {
            handleError(error);
        }
    }

    // Create or access bank account of logged in user
    public void manageAccount(String action) {
        String error = "";
        if (action.equals("create savings") && currentUser.hasMoney(fee)) {
            currentUser.getCash().subtract(fee);
            manager.getCash().add(fee);

            BankAccount b = new SavingsAccount(numAccounts++);
            currentUser.addAccount(b);
            currentAccount = b;
            System.out.println("Current account is " + currentAccount);
            handleAccess();
        } else if (action.equals("create checking") && currentUser.hasMoney(fee)) {
            currentUser.getCash().subtract(fee);
            manager.getCash().add(fee);
            BankAccount b = new CheckingAccount(numAccounts++);
            currentUser.addAccount(b);
            currentAccount = b;
            System.out.println("Current account is " + currentAccount);
            handleAccess();
        }  else if (action.equals("access account")) {
            String input = bankATM.getUserInput();
            if (BankAccount.isValidAccountNumber(input)) {
                int accNum = Integer.parseInt(input);
                if (currentUser.hasAccount(accNum)) {
                    currentAccount = currentUser.getAccount(accNum);
                    handleAccess();
                } else {
                    error = "You don't have an account by that number";
                }
            } else {
                error = "Invalid input";
            }
        }
        // Else, go to error screen
        if (!error.equals("")) {
            handleError(error);
        }
    }

    // Access the current user's selected bank account
    // Allows a user to deposit, withdraw, or close an account (with fees)
    public void manageAccess(String action) {
        String input = bankATM.getUserInput();

        boolean isChecking = currentAccount instanceof CheckingAccount;
        String error = "";
        if (action.equals("close")) {
            if (currentUser.hasMoney(fee)) {
                Money total = currentAccount.getBalance();
                Transaction t = new Transaction(
                        currentUser.getId(),
                        new Money(total.getAmount(), total.getCurrency()),
                        Transaction.CLOSED,
                        currentAccount.getAccountNum()
                );
                currentUser.addTransaction(t);
                transactionsLog.add(t);

                currentUser.getCash().add(total);
                currentUser.getCash().subtract(fee);
                manager.getCash().add(fee);
                currentAccount.withdraw(total);
                currentUser.removeAccount(currentAccount);
                handleMain();
            } else {
                error = "Unable to " + action + ", make sure you can pay the fee\n"
                        + "Consider taking out a loan if you can't pay the fee.";
            }
        } else if (Money.isValidAmount(input)) {
            Money amt = new Money(input);
            if (action.equals("deposit")) {
                Money required = new Money(input);
                if (isChecking) {
                    required.add(fee);
                }
                if (currentUser.hasMoney(required)) {
                    currentUser.getCash().subtract(amt);
                    if (isChecking) {
                        currentUser.getCash().subtract(fee);
                        manager.getCash().add(fee);
                    }
                    currentAccount.deposit(amt);

                    Transaction t = new Transaction(
                            currentUser.getId(),
                            amt,
                            Transaction.DEPOSITED,
                            currentAccount.getAccountNum()
                    );
                    currentUser.addTransaction(t);
                    transactionsLog.add(t);

                    handleAccess();
                } else {
                    error = "Unable to " + action + ", make sure you can pay the fee\n"
                            + "Consider taking out a loan if you can't pay the fee.";
                }
            } else if (action.equals("withdraw")) {
                if (currentAccount.hasMoney(amt) && currentUser.hasMoney(fee)) {
                    currentAccount.withdraw(amt);
                    currentUser.getCash().add(amt);
                    currentUser.getCash().subtract(fee);
                    manager.getCash().add(fee);

                    Transaction t = new Transaction(
                            currentUser.getId(),
                            amt,
                            Transaction.WITHDREW,
                            currentAccount.getAccountNum()
                    );
                    currentUser.addTransaction(t);
                    transactionsLog.add(t);

                    handleAccess();
                } else {
                    error = "Unable to " + action + ", make sure you can pay the fee\n"
                            + "Consider taking out a loan if you can't pay the fee.";
                }
            }
        } else {
            error = "Invalid input";
        }
        // Else, go to error screen
        if (!error.equals("")) {
            handleError(error);
        }
    }

    // Create or access bank account of logged in user
    public void manager(String action) {
        String error = "";
        if (action.equals("search user")) {
            String input = bankATM.getUserInput();
            if (SSN.isValidSSN(input)) {
                if (hasUser(input)) {
                    ArrayList<User> usersToInspect = new ArrayList<>();
                    usersToInspect.add(getUser(input));
                    handleUserStatus(usersToInspect);
                } else {
                    error = "No user found for " + input;
                }
            } else {
                error = "Invalid input";
            }
        } else if (action.equals("all users")) {
            handleUserStatus(allUsers);
        } else if (action.equals("transactions")) {
            handleTransaction(transactionsLog, true);
            transactionsLog.clear();
        } else if (action.equals("interest")) {
            handleInterest();
        }
        // Else, go to error screen
        if (!error.equals("")) {
            handleError(error);
        }
    }


    // Shows the welcome screen, where users can login or register
    // or manager(s) can login
    public void handleWelcome() {
        bankATM.clearUserButtons();
        bankATM.setScreenText(welcomeText);

        String halfPrompt = "Enter your 10 digit SSN to ";
        ActionListener back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWelcome();
            }
        };
        ActionListener login = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "login";
                String prompt = halfPrompt + action;
                ActionListener callback = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageUser(action);
                    }
                };
                handleUserInput(prompt, callback, back);
            }
        };
        ActionListener register = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "register";
                String prompt = halfPrompt + action;
                ActionListener callback = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageUser(action);
                    }
                };
                handleUserInput(prompt, callback, back);
            }
        };

        ActionListener managerLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "manager login";
                String prompt = halfPrompt + action;
                ActionListener callback = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleManager();
                    }
                };
                handleUserInput(prompt, callback, back);
            }
        };
        bankATM.setAction(1, login);
        bankATM.setAction(2, register);
        bankATM.setAction(3, managerLogin);
    }

    // General handler for any user input
    // Requires a callback to call when user clicks "enter" button
    // Requires another callback to call if user clicks "cancel" button
    public void handleUserInput(String prompt, ActionListener callback, ActionListener back) {
        bankATM.clearUserButtons();
        bankATM.setScreenText(prompt + "\n");
        bankATM.setEnterButton(callback);
        bankATM.setCancelButton(back);
    }

    // The main portal, where users can create accounts, manage accounts,
    // make transactions, request loans, etc.
    // User must be logged in to access this
    public void handleMain() {
        bankATM.clearUserButtons();
        bankATM.setScreenText(mainText);

        ActionListener account = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAccount();
            }
        };
        ActionListener loan = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLoan();
            }
        };
        ActionListener transaction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTransaction(currentUser.getTransactions(), false);
            }
        };
        ActionListener back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWelcome();
            }
        };
        bankATM.setAction(1, account);
        bankATM.setAction(2, loan);
        bankATM.setAction(3, transaction);
        bankATM.setAction(4, back);
    }

    // Handles creation and accessing of checking and savings accounts
    public void handleAccount() {
        bankATM.clearUserButtons();
        String info = "Fee is " + fee + "\n"
                + "You have " + currentUser.getCash() + "\n\n"
                + accountText;
        bankATM.setScreenText(info);

        ActionListener back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMain();
            }
        };
        ActionListener createSavings = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "create savings";
                manageAccount(action);
            }
        };
        ActionListener createCheckings = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "create checking";
                manageAccount(action);
            }
        };
        ActionListener accessAcc = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "access account";
                String prompt = "Enter your Account Number to " + action;
                ActionListener callback = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageAccount(action);
                    }
                };
                handleUserInput(prompt, callback, back);
            }
        };
        bankATM.setAction(1, createSavings);
        bankATM.setAction(2, createCheckings);
        bankATM.setAction(3, accessAcc);
        bankATM.setAction(4, back);
    }

    // Handles the user's currentAccount actions
    public void handleAccess() {
        bankATM.clearUserButtons();
        // when accessing account, show acc number and balance (tostring)
        String info = "Fee is " + fee + "\n"
                + "Savings account with " + interestAmt + " get " + interestRate + "% interest\n\n"
                + currentAccount.toString() + "\n" + accessText;
        bankATM.setScreenText(info);

        String halfPrompt = "You have " + currentUser.getCash() + "\n"
                + "Your account has " + currentAccount.getBalance() + "\n"
                + "Enter amount of money to ";
        ActionListener back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMain();
            }
        };
        ActionListener deposit = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "deposit";
                String prompt = halfPrompt + action;
                ActionListener callback = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageAccess(action);
                    }
                };
                handleUserInput(prompt, callback, back);
            }
        };
        ActionListener withdraw = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "withdraw";
                String prompt = halfPrompt + action;
                ActionListener callback = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageAccess(action);
                    }
                };
                handleUserInput(prompt, callback, back);
            }
        };
        ActionListener close = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "close";
                manageAccess(action);
            }
        };
        bankATM.setAction(1, deposit);
        bankATM.setAction(2, withdraw);
        bankATM.setAction(3, close);
        bankATM.setAction(4, back);
    }

    // Displays user's debt and allows them to borrow more or repay loan
    public void handleLoan() {
        bankATM.clearUserButtons();
        String info = "Interest rate is " + (interestRate*100) + "%\n\n"
                + "You currently owe " + currentUser.getLoan() + "\n"
                + loanText;
        bankATM.setScreenText(info);

        String halfPrompt = "You have " + currentUser.getCash() + " and your debt is " + currentUser.getLoan() + "\n"
                + "Enter amount of money you want to ";
        ActionListener back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMain();
            }
        };
        ActionListener request = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "request";
                String prompt = halfPrompt + action;
                ActionListener callback = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageLoan(action);
                    }
                };
                handleUserInput(prompt, callback, back);
            }
        };
        ActionListener repay = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "repay";
                String prompt = halfPrompt + action;
                ActionListener callback = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manageLoan(action);
                    }
                };
                handleUserInput(prompt, callback, back);
            }
        };
        bankATM.setAction(1, request);
        bankATM.setAction(2, repay);
        bankATM.setAction(4, back);
    }

    // Reports the transactions of either everyone (if manager) or just the user
    public void handleTransaction(ArrayList<Transaction> transactions, boolean isManager) {
        bankATM.clearUserButtons();
        String transactionText = "Viewing Transactions\n";

        int i = 0;
        for (Transaction t: transactions) {
            transactionText += t + "\n";
            i++;
            if (i == 5) {
                break;
            }
        }
        transactionText += "(4) Back\n";

        bankATM.setScreenText(transactionText);
        ActionListener back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isManager) {
                    handleManager();
                } else {
                    handleMain();
                }
            }
        };
        bankATM.setAction(4, back);
    }

    // Gets the status of specified users
    public void handleUserStatus(ArrayList<User> users) {
        bankATM.clearUserButtons();
        String userText = "Viewing User(s)\n";

        int i = 0;
        for (User u: users) {
            userText += "User " + u.getId().getLastFourDigits()
                    + " has " + u.numAccounts()
                    + " accounts and owes " + u.getLoan() + "\n";
            i++;
            if (i == 5) {
                break;
            }
        }
        userText += "(4) Back\n";

        bankATM.setScreenText(userText);
        ActionListener back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleManager();
            }
        };
        bankATM.setAction(4, back);
    }

    // Handles the manager's options on the ATM GUI
    public void handleManager() {
        bankATM.clearUserButtons();
        bankATM.setScreenText("Hello manager, you have " + manager.getCash() + "\n" + managerOptions);

        ActionListener back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWelcome();
            }
        };
        ActionListener searchUser = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "search user";
                String prompt = "Enter user SSN to " + action;
                ActionListener callback = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manager(action);
                    }
                };
                handleUserInput(prompt, callback, back);
            }
        };
        ActionListener allUsers = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "all users";
                manager(action);
            }
        };
        ActionListener allTransactions = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "transactions";
                manager(action);
            }
        };
        ActionListener interest = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = "interest";
                manager(action);
            }
        };
        bankATM.setAction(1, searchUser);
        bankATM.setAction(2, allUsers);
        bankATM.setAction(3, allTransactions);
        bankATM.setAction(4, interest);
        bankATM.setCancelButton(back);
    }

    // Displays the manager paying/charging interest on the ATM
    public void handleInterest() {
        bankATM.clearUserButtons();
        String interestText = "Paid interest to all savings accounts\n"
                + "above " + interestAmt + " at " + interestRate + "% interest rate\n\n"
                + "Charged interest from all outstanding loans at "
                + (interestRate * 100) + "% interest\n";
        interestText += "(4) Back\n";
        bankATM.setScreenText(interestText);

        for (User u: allUsers) {
            for (BankAccount b: u.getAccounts()) {
                if (b instanceof SavingsAccount) {
                    double bal = b.getBalance().getAmount();
                    Money newAmt = new Money((bal * (interestRate/100)), new Currency());
                    b.deposit(newAmt);
                    manager.getCash().subtract(newAmt);
                }
            }
            double loan = u.getLoan().getAmount();
            Money newAmt = new Money(loan * interestRate, new Currency());
            u.getLoan().add(newAmt);
        }

        ActionListener back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleManager();
            }
        };
        bankATM.setAction(4, back);
    }

    // Displays an error in the ATM along with a message
    public void handleError(String err) {
        bankATM.clearUserButtons();
        bankATM.setScreenText("Error: " + err + "\n\n" + "(4) Go back to login screen");
        ActionListener back = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWelcome();
            }
        };
        bankATM.setAction(4, back);
    }

    public static void main(String[] args) {
        ATM fancyATM = new ATM();
        Bank fancyBank = new Bank(fancyATM);

        // Create manager with identifier 7777777777
        fancyBank.manager = new User("7777777777");

        // Create some test users
        fancyBank.allUsers.add(new User("1234567890"));
        fancyBank.allUsers.add(new User("1111111111"));

        fancyBank.handleWelcome();
    }
}
