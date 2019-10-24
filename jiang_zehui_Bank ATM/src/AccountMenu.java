import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AccountMenu {

	private JFrame frame;
	private JFrame parent ;
	private Account account ;
	private Bank bank ;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountMenu window = new AccountMenu(parent, bank, account);
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
	public AccountMenu(JFrame parent, Bank bank, Account account) {
		this.bank = bank ;
		this.parent = parent ;
		this.account = account ;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(parent);
		frame.getContentPane().setLayout(null);
		parent.setEnabled(false) ;
		// text pane
		JTextPane accountOutput = new JTextPane();
		accountOutput.setEditable(false);
		accountOutput.setBounds(31, 80, 259, 152);
		frame.getContentPane().add(accountOutput);
		
		JLabel lblCheckingAccount = new JLabel(account.getType() + " Account " + account.getId());
		lblCheckingAccount.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblCheckingAccount.setBounds(70, 12, 220, 18);
		frame.getContentPane().add(lblCheckingAccount);
		
		// currency jumbo box
		String[] currencyArray = new String[account.getCurrencies().size()] ;
		for(int i = 0 ; i < currencyArray.length ; i++) {
			currencyArray[i] = account.getCurrencies().get(i).getName() ;
		}
		JComboBox currencyBox = new JComboBox();
		currencyBox.setModel(new DefaultComboBoxModel(currencyArray));
		currencyBox.setBounds(28, 52, 155, 27);
		frame.getContentPane().add(currencyBox);
		
		
		// currency label
		JLabel lblSelectCurrency = new JLabel("Select Currency");
		lblSelectCurrency.setBounds(34, 36, 105, 16);
		frame.getContentPane().add(lblSelectCurrency);
		
		// select button
		JButton btnCheckBalance = new JButton("Check Balance");
		btnCheckBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Currency curr = account.getByName(currencyBox.getSelectedItem().toString()) ;
				if(curr == null) {
					JOptionPane.showMessageDialog(frame, "Currency doesn't exist!", "Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				
				StringBuilder sb = new StringBuilder() ;
				sb.append("Account Balance: " + curr.getAmount() + "\n") ;
				if(account.getType().equals("Saving")) {
					sb.append("Balance Yearly Interest Rate: " + account.getInterest()+ "\n") ;
					sb.append("Balance Interest Time: " + account.getInterestTime()+ "\n") ;
					sb.append("Estimated Total Balance: " + curr.getAmount()*(1 + account.getInterest()*account.getInterestTime())+ "\n") ;
				}
				
					
				if(curr.getLoanAmount() > 0) {
					sb.append("Total Loan Repayment: " + curr.getLoanAmount()+ "\n") ;
					sb.append("Loan Time: " + curr.getLoanTime()+ "day(s)\n") ;
					sb.append("Loan Daily Interest Rate : " + account.getLoanInterest()+ "\n") ;
				}
				accountOutput.setText(sb.toString());
			}
		});
		btnCheckBalance.setBounds(179, 51, 117, 29);
		frame.getContentPane().add(btnCheckBalance);
		
		
		// deposit button/function
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = currencyBox.getSelectedItem().toString() ;
				Currency curr = account.getByName(name) ;
				if(curr == null) {
					JOptionPane.showMessageDialog(frame, "Currency doesn't exist!", "Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				DepositWindow dw = new DepositWindow(frame, account, name) ;
				dw.start();
				
			}
		});
		btnDeposit.setBounds(302, 80, 117, 29);
		frame.getContentPane().add(btnDeposit);
		
		// withdraw button
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = currencyBox.getSelectedItem().toString() ;
				Currency curr = account.getByName(name) ;
				if(curr == null) {
					JOptionPane.showMessageDialog(frame, "Currency doesn't exist!", "Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				WithdrawWindow ww = new WithdrawWindow(frame, account, name) ;
				ww.start();
				
			}
		});
		btnWithdraw.setBounds(302, 110, 117, 29);
		frame.getContentPane().add(btnWithdraw);
		
		// transfer button
		JButton btnTransfer = new JButton("Transfer");
		if(account.getType().equals("Saving"))
			btnTransfer.setEnabled(false);
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = currencyBox.getSelectedItem().toString() ; 
				Currency curr = account.getByName(name) ;
				if(curr == null) {
					JOptionPane.showMessageDialog(frame, "Currency doesn't exist!", "Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				TransferWindow tw = new TransferWindow(frame, bank, account, name) ;
				tw.start();
			}
		});
		btnTransfer.setBounds(302, 172, 117, 29);
		frame.getContentPane().add(btnTransfer);
		
		// loan button
		JButton btnLoan = new JButton("Loan");
		btnLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = currencyBox.getSelectedItem().toString() ; 
				Currency curr = account.getByName(name) ;
				if(curr == null) {
					JOptionPane.showMessageDialog(frame, "Currency doesn't exist!", "Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				LoanWindow lw = new LoanWindow(frame, account, name) ;
				lw.start();
			}
		});
		btnLoan.setBounds(302, 203, 117, 29);
		frame.getContentPane().add(btnLoan);
		
		// logout button
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose() ;
				parent.setEnabled(true);
			}
		});
		btnLogout.setBounds(31, 244, 117, 29);
		frame.getContentPane().add(btnLogout);
		
		// exit button
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(160, 244, 117, 29);
		frame.getContentPane().add(btnExit);
		
		JLabel lblClickCheckBalance = new JLabel("Click Check Balance to refresh");
		lblClickCheckBalance.setBounds(191, 36, 229, 16);
		frame.getContentPane().add(lblClickCheckBalance);
		
		JButton btnExchange = new JButton("Exchange");
		btnExchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = currencyBox.getSelectedItem().toString() ; 
				Currency curr = account.getByName(name) ;
				if(curr == null) {
					JOptionPane.showMessageDialog(frame, "Currency doesn't exist!", "Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				ExchangeWindow ew = new ExchangeWindow(frame, account, name) ;
				ew.start();
			}
		});
		btnExchange.setBounds(302, 140, 117, 29);
		frame.getContentPane().add(btnExchange);
		
		
		
		
	}
}
