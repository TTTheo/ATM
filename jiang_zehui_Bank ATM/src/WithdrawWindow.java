import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WithdrawWindow {

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
					WithdrawWindow window = new WithdrawWindow(parent, account, name);
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
	public WithdrawWindow(JFrame parent, Account account, String name) {
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
		frame.setLocationRelativeTo(parent);
		parent.setEnabled(false);
		frame.getContentPane().setLayout(null);
		
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
				if(!account.withdraw(name, amount)) {
					JOptionPane.showMessageDialog(frame, "Your don't have enought money!\n If you need money, you could consider a loan.", "Input Error!", JOptionPane.WARNING_MESSAGE);
					amountField.setText("");
					return ;
				}else {
					JOptionPane.showMessageDialog(frame, "Withdrawal succeeded.\nNow you have " + account.getByName(name).getAmount() + " "+ name + " on your account" , "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					parent.setEnabled(true);
				}
				
				
			}
		});
		btnConfirm.setBounds(70, 201, 117, 29);
		frame.getContentPane().add(btnConfirm) ;
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				parent.setEnabled(true);
			}
		});
		btnCancel.setBounds(264, 201, 117, 29);
		frame.getContentPane().add(btnCancel);
		
		amountField = new JTextField();
		amountField.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		amountField.setBounds(158, 146, 130, 43);
		frame.getContentPane().add(amountField);
		amountField.setColumns(10);
		
		JLabel lblPleaseEnterWithdraw = new JLabel("Please Enter Withdraw Amount:");
		lblPleaseEnterWithdraw.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPleaseEnterWithdraw.setBounds(80, 97, 332, 37);
		frame.getContentPane().add(lblPleaseEnterWithdraw);
		
		JLabel lblWithdrawingUs = new JLabel("Withdrawing " + name);
		lblWithdrawingUs.setBounds(34, 26, 234, 16);
		frame.getContentPane().add(lblWithdrawingUs);
		
		JLabel lblYourAvailableBalance = new JLabel("Your available balance is: " + account.getByName(name).getAmount());
		lblYourAvailableBalance.setBounds(34, 55, 218, 16);
		frame.getContentPane().add(lblYourAvailableBalance);
		
		JLabel lblNewLabel = new JLabel("Withdraw fee is " + account.getWithdrawFee()/account.getByName(name).getRate());
		lblNewLabel.setBounds(34, 83, 268, 16);
		frame.getContentPane().add(lblNewLabel);
	}
}