/*
 * The class that holds all the frequently used panel build, so that duplicate codes are
 * reduced greatly from the application
 */
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyPanels {
	/*
	 * Build up Banker Menu Panel
	 */
	public void setBankerMenuPanel(CoreInfo coreInfo) {
		coreInfo.getMessageArea().setText("Welcome Come Back!");
		coreInfo.getMessageArea().setBounds(100, 50, 200, 50);
		coreInfo.getFrame().add(coreInfo.getMessageArea());
		coreInfo.getPanel().removeAll();
		coreInfo.getPanel().setLayout(new GridLayout(2, 2, 20, 10));
		JButton mButton = new JButton ("Manage Customer Account");
		coreInfo.getPanel().add(mButton);
		manageButtonListener mAction = new manageButtonListener(coreInfo, "Manage Accounts");
		mButton.addActionListener(mAction);
		JButton rButton = new JButton ("Review Daily Log");
		coreInfo.getPanel().add(rButton);
		reviewButtonListener rAction = new reviewButtonListener(coreInfo, "Bank Management");
		rButton.addActionListener(rAction);
		JButton iButton = new JButton ("Calculate Net Interest");
		coreInfo.getPanel().add(iButton);
		interestButtonListener iAction = new interestButtonListener(coreInfo, "Net Interest for Next Month");
		iButton.addActionListener(iAction);
		JButton switchButton = new JButton("Switch User");
		coreInfo.getPanel().add(switchButton);
		switchButtonListener sAction = new switchButtonListener(coreInfo);
		switchButton.addActionListener(sAction);
		coreInfo.getPanel().updateUI();
		coreInfo.getFrame().add(coreInfo.getPanel());
		coreInfo.getFrame().setLayout(new FlowLayout());
	}
	/*
	 * Build up Main Menu Panel
	 */
	public void setMainMenuPanel(CoreInfo coreInfo) {
		coreInfo.getMessageArea().setText("Welcome to MyBank");
		coreInfo.getMessageArea().setBounds(100, 50, 200, 50);
		coreInfo.getFrame().add(coreInfo.getMessageArea());
		coreInfo.getPanel().removeAll();
		coreInfo.getPanel().setLayout(new GridLayout(1, 2, 20, 10));
		JButton cButton = new JButton ("I'm a CUSTOMER");
		coreInfo.getPanel().add(cButton);
		customerButtonListener cAction = new customerButtonListener(coreInfo, "Customer Login");
		cButton.addActionListener(cAction);
		JButton bButton = new JButton ("I'm a BANKER");
		coreInfo.getPanel().add(bButton);
		bankerButtonListener bAction = new bankerButtonListener(coreInfo, "Bank Management");
		bButton.addActionListener(bAction);
		coreInfo.getPanel().updateUI();
		coreInfo.getFrame().add(coreInfo.getPanel());
		coreInfo.getFrame().setLayout(new FlowLayout());
	}
	/*
	 * Build up Customer Log-in Panel
	 */
	public void setCustomerLoginPanel(CoreInfo coreInfo, String messageToShow) {
		coreInfo.getMessageArea().setText("Customer Login");
		coreInfo.getMessageArea().setBounds(100, 250, 200, 50);
		coreInfo.getPanel().removeAll();
		coreInfo.getPanel().setLayout(new GridLayout(4, 2, 20, 10));
		JLabel labelOne = new JLabel("Customer ID");
		coreInfo.getPanel().add(labelOne);
		JTextField tf1 = new JTextField(10);
		tf1.setBounds(50, 0, 150, 50);
		coreInfo.getPanel().add(tf1);
		JLabel labelTwo = new JLabel("Password");
		coreInfo.getPanel().add(labelTwo);
	    JTextField tf2 = new JTextField(10);
	    coreInfo.getPanel().add(tf2);
		JButton loginButton = new JButton("Login");
		coreInfo.getPanel().add(loginButton);
		loginButtonListener lAction = new loginButtonListener(coreInfo, "Hello, ");
		loginButton.addActionListener(lAction);
		JButton registerButton = new JButton("Register");
		coreInfo.getPanel().add(registerButton);
		registerButtonListener rAction = new registerButtonListener(coreInfo, "Customer Registrition");
		registerButton.addActionListener(rAction);
		JButton switchButton = new JButton("Switch User");
		coreInfo.getPanel().add(switchButton);
		switchButtonListener sAction = new switchButtonListener(coreInfo);
		switchButton.addActionListener(sAction);
		coreInfo.getPanel().updateUI();
		coreInfo.getFrame().setLayout(new FlowLayout());
	}
	/*
	 * Build up Customer Menu Panel
	 */
	public void setCustomerMenuPanel(CoreInfo coreInfo,  CustomerInfo cInfo,String messageToShow) {
		coreInfo.getMessageArea().setText(messageToShow);
		coreInfo.getMessageArea().setBounds(100, 250, 200, 50);
		coreInfo.getPanel().removeAll();
		coreInfo.getPanel().setLayout(new GridLayout(3, 2, 20, 10));
		JButton openButton = new JButton("Open Account");
		coreInfo.getPanel().add(openButton);
		openButtonListener oAction = new openButtonListener(coreInfo, cInfo, "Opening New Account");
		openButton.addActionListener(oAction);
		JButton checkButton = new JButton("Check Status");
		coreInfo.getPanel().add(checkButton);
		checkButtonListener cAction = new checkButtonListener(coreInfo, cInfo, "Checking Account Balance");
		checkButton.addActionListener(cAction);
		JButton depositButton = new JButton("Deposit");
		coreInfo.getPanel().add(depositButton);
		depositButtonListener dAction = new depositButtonListener(coreInfo, cInfo, "Making Deposit");
		depositButton.addActionListener(dAction);
		JButton loanButton = new JButton("Get Loan");
		coreInfo.getPanel().add(loanButton);
		loanButtonListener lAction = new loanButtonListener(coreInfo, cInfo, "Applying Loan");
		loanButton.addActionListener(lAction);
		JButton transButton = new JButton("Make Transaction");
		coreInfo.getPanel().add(transButton);
		transButtonListener tAction = new transButtonListener(coreInfo, cInfo, "Making Trasaction");
		transButton.addActionListener(tAction);
		JButton logoutButton = new JButton("Log Out");
		coreInfo.getPanel().add(logoutButton);
		logoutButtonListener lgAction = new logoutButtonListener(coreInfo, "Customer Login");
		logoutButton.addActionListener(lgAction);
		coreInfo.getPanel().updateUI();
		coreInfo.getFrame().setLayout(new FlowLayout());
	}
}
