import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoanWindow {

	private JFrame frame;
	private JFrame parent ;
	private Account account ;
	private String name ;
	private JTextField amountField;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoanWindow window = new LoanWindow(parent, account, name);
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
	public LoanWindow(JFrame parent, Account account, String name) {
		this.parent = parent ;
		this.account = account ;
		this.name = name ;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(parent);
		parent.setEnabled(false);
		
		JLabel lblLoaningUs = new JLabel("Loaning " + name);
		lblLoaningUs.setBounds(35, 31, 162, 16);
		frame.getContentPane().add(lblLoaningUs);
		
		JTextPane dailyInterestPane = new JTextPane();
		dailyInterestPane.setEditable(false);
		dailyInterestPane.setBounds(224, 186, 130, 16);
		frame.getContentPane().add(dailyInterestPane);
		
		amountField = new JTextField();
		amountField.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		amountField.setBounds(155, 112, 130, 58);
		frame.getContentPane().add(amountField);
		amountField.setColumns(10);
		
		JLabel lblLoanAmount = new JLabel("Please Enter Loan Amount");
		lblLoanAmount.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblLoanAmount.setBounds(97, 69, 289, 42);
		frame.getContentPane().add(lblLoanAmount);
		
		JButton btnCheckDailyInterest = new JButton("Check Daily Interest");
		btnCheckDailyInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(amountField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Amount cannot be empty!", "Input Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				String amountStr = amountField.getText() ;
				double amount = 0 ;
				try {
					amount = Double.parseDouble(amountStr) ;
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(frame, "Amount must be a valid number!", "Input Error!", JOptionPane.WARNING_MESSAGE);
					amountField.setText("");
					return ;
				}
				if(amount <= 0 || amount > 5000) {
					JOptionPane.showMessageDialog(frame, "Amount must be between 0 and 5000!", "Input Error!", JOptionPane.WARNING_MESSAGE);
					amountField.setText("");
					return ;
				}
				double output = amount*account.getLoanInterest() ;
				dailyInterestPane.setText(output + "");
			}
		});
		btnCheckDailyInterest.setBounds(29, 182, 183, 29);
		frame.getContentPane().add(btnCheckDailyInterest);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(amountField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Amount cannot be empty!", "Input Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				String amountStr = amountField.getText() ;
				double amount = 0 ;
				try {
					amount = Double.parseDouble(amountStr) ;
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(frame, "Amount must be a valid number!", "Input Error!", JOptionPane.WARNING_MESSAGE);
					amountField.setText("");
					return ;
				}
				if(amount <= 0 || amount > 5000) {
					JOptionPane.showMessageDialog(frame, "Amount must be between 0 and 5000!", "Input Error!", JOptionPane.WARNING_MESSAGE);
					amountField.setText("");
					return ;
				}
				account.loan(amount, name);
				JOptionPane.showMessageDialog(frame, "Loan succeeded.\nNow you have " + account.getByName(name).getAmount() + " "+ name + " on your account" , "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				parent.setEnabled(true);
			}
		});
		btnConfirm.setBounds(39, 223, 117, 29);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				parent.setEnabled(true);
			}
		});
		btnCancel.setBounds(168, 223, 117, 29);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblPleaseNoticeThe = new JLabel("Please notice: the maximum one-time loan amount is 5000");
		lblPleaseNoticeThe.setBounds(35, 48, 409, 16);
		frame.getContentPane().add(lblPleaseNoticeThe);
		
		JButton btnRepayment = new JButton("Repayment");
		btnRepayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RepaymentWindow rw = new RepaymentWindow(frame, account, name) ;
				rw.start();
			}
		});
		btnRepayment.setBounds(297, 223, 117, 29);
		frame.getContentPane().add(btnRepayment);
	}

}
