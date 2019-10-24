import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class README {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					README window = new README();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public README() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 500, 545);
		frame.getContentPane().add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		textPane.setText("Online Fancy Bank ATM\n" + 
				"README\n" + 
				"IMPORTANT:\n" + 
				"Please note that the red cross buttons of windows are set to EXIT_ON_CLOSE. So, please don’t click on it if you want to quit the ATM. \n" + 
				"Also note that if you want to keep the program running, always click on Cancel/Logout to go back to previous window. Exit button always means System.exit().\n" + 
				"\n" + 
				"How to run:\n" + 
				"The main method is located in MainMenu.java. Run by command line:\n" + 
				"javac MainMenu.java\n" + 
				"java MainMenu\n" + 
				"\n" + 
				"Or run it in IDEs.\n" + 
				"\n" + 
				"Client Functions:\n" + 
				"1.	Register new checking/saving account and login to existing accounts. Client can login to your account by the combination of your User Name and a unique account id. Under the same user name there could be unlimited accounts. In each account there are three kinds of currencies: US Dollar, Pound and Euro, stored separately with default initial balance of 0. \n" + 
				"2.	A little modification on the rules that opening a new account will not be charged. It encourages users to have more accounts.\n" + 
				"3.	After logged in to your account, you can check your balance for each currency by select a currency and click Check Balance button.\n" + 
				"4.	By choosing a currency and click one of the action buttons on the right side, you will enter an action window corresponding to your choice of currency and action.\n" + 
				"For checking account:\n" + 
				"user can choose from one of the following actions: deposit balance, withdraw cash, transfer to another account, exchange between different currencies and make a loan/repay loan for all three currencies.\n" + 
				"•	Deposit: The one-time deposit maximum value is 5000 in all currencies. No fee is charged.\n" + 
				"•	Withdraw: For each withdrawal a withdraw fee equals to 20 US Dollar will be charged. (e.g. charged 18.18 Euro if you are withdrawing Euro) If your balance can’t cover the total of withdrawal amount and withdraw fee, you will not be allowed to withdraw.\n" + 
				"•	Exchange: For each currency exchange you are charged of an exchange fee equals to 20 US Dollar. To exchange from currency A to currency B, you can do it by choosing currency A, click exchange button to enter the exchange window, choose the currency B, enter exchange amount and click Confirm. If your balance of currency A can cover the total of exchange fee and exchange amount, your exchange will succeed.\n" + 
				"•	Transfer: For each transfer a transfer fee equals to 20 US Dollar will be charged. You can transfer to an existing account by entering the correct user name and account id. If your balance can’t cover the total of transfer amount and transfer fee, you will not be allowed to transfer. If the receiver account doesn’t exist, you are also not allowed to transfer.\n" + 
				"•	Loan:\n" + 
				"•	Make a loan: Choose a currency and click Loan button. The one-time maximum loan amount is 5000. After entering a desired loan amount, you can click Check Daily Interest to see how much you pay extra on a daily basis. Then click Confirm to make the loan. If you made a loan, your loan amount, loan interest rate and total repayment will be shown when you click Check Balance on account menu.\n" + 
				"•	Repayment: Choose the currency you want to repay, click Loan then click Repayment. Your total repayment will be shown on the new window. The repayment can only be one-time repayment and no mortgage is allowed. Click confirm to repay.  \n" + 
				"For saving account:\n" + 
				"	A saving account is similar to checking account except for the following features:\n" + 
				"•	If your saving account, all currencies included, contains more than the equal value of 5000 US Dollars, you will obtain a yearly interest rate of 3%. Once the balance falls lower than 5000 US Dollars, you will lose the yearly interest rate immediately and the accumulative interest time will be cleared to 0.\n" + 
				"•	Saving account is not allowed to make transfer but can receive transfer.\n" + 
				"•	Saving account has a higher withdraw fee equals to 30 US Dollar and a higher exchange fee equals to 30 US Dollar as well.\n" + 
				"When you are done with an account, you can click Logout to go back to Main menu (login menu) or click exit to close online ATM.\n" + 
				"\n" + 
				"Manager Functions:\n" + 
				"Manager is not allowed to interfere when a client is using the ATM. However, a manager can use her/his priority at the main menu by clicking Manager menu option at the menu bar. There are two main functions:\n" + 
				"•	Report: A up-to-date report on all the transactions made by all accounts and profit made in different currencies. You can check it whenever you are at the Main menu and all previous actions by clients are kept track of.\n" + 
				"•	Lookup: You can look up information of your customers by:\n" + 
				"o	Look up by unique account ID: Go to menu bar -> Manager -> Lookup, in the lookup wizard, choose By ID. After entering an existing account id and clicking Search button, you will see all information of the account including account user name, account type and the current balance of all currencies.\n" + 
				"o	Look up by user name: Go to menu bar -> Manager -> Lookup, in the lookup wizard, choose By User Name. After entering an existing user name and clicking Search button, you will see all accounts that belong to this user as well as all the account details.\n" + 
				"\n" + 
				"In menu bar -> Help, there are two options: README where you can read THIS document and Test Info where there are several test user name and ids I setup for your convenience.\n" + 
				"\n" + 
				"Thank you!\n" + 
				"Zehui\n" + 
				"");
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnClose.setBounds(179, 543, 117, 29);
		frame.getContentPane().add(btnClose);
	}

}
