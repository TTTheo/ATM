import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DepositWindow {

	private JFrame frame;
	private JFrame parent ;
	private Account account;
	private String name ;
	private JTextField amountField;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepositWindow window = new DepositWindow(parent, account, name);
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
	public DepositWindow(JFrame parent, Account account, String name) {
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
		
		// confirm button
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verify input
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
				// deposit
				account.deposit(name, amount);
				// show message
				JOptionPane.showMessageDialog(frame, "Deposit succeeded.\nNow you have " + account.getByName(name).getAmount() + " "+ name + " on your account" , "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				parent.setEnabled(true);
					
			}
		});
		btnConfirm.setBounds(70, 201, 117, 29);
		frame.getContentPane().add(btnConfirm);
		
		// cancel button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				parent.setEnabled(true);
			}
		});
		btnCancel.setBounds(261, 201, 117, 29);
		frame.getContentPane().add(btnCancel);
		
		amountField = new JTextField();
		amountField.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		amountField.setBounds(151, 134, 130, 43);
		frame.getContentPane().add(amountField);
		amountField.setColumns(10);
		
		JLabel lblPleaseEnterDeposit = new JLabel("Please Enter Deposit Amount:");
		lblPleaseEnterDeposit.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblPleaseEnterDeposit.setBounds(85, 85, 283, 50);
		frame.getContentPane().add(lblPleaseEnterDeposit);
		
		JLabel lblDepositingUsDollar = new JLabel("Depositing " + name);
		lblDepositingUsDollar.setBounds(33, 31, 154, 16);
		frame.getContentPane().add(lblDepositingUsDollar);
		
		JLabel lblNewLabel = new JLabel("Please notice: the maximum one-time deposit amount is 5000");
		lblNewLabel.setBounds(33, 44, 411, 29);
		frame.getContentPane().add(lblNewLabel);
	}

}
