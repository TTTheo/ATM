import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransferCurrencyWindow {

	private JFrame frame;
	private JFrame parent ;
	private JFrame gparent ;
	private Account from ;
	private Account to ;
	private String name ;
	private JTextField amountField;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferCurrencyWindow window = new TransferCurrencyWindow(gparent, parent, from, to, name);
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
	public TransferCurrencyWindow(JFrame gparent, JFrame parent, Account from, Account to, String name) {
		this.gparent = gparent ;
		this.parent = parent ;
		this.from = from ;
		this.to = to ;
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
		
		JLabel lblTransferUsTo = new JLabel("Transfer " + name + " to " + to.getUserName() + " " + to.getType() + " " + to.getId());
		lblTransferUsTo.setBounds(31, 29, 396, 16);
		frame.getContentPane().add(lblTransferUsTo);
		
		JLabel lblYourBalanceIs = new JLabel("Your balance is " + from.getByName(name).getAmount());
		lblYourBalanceIs.setBounds(31, 56, 160, 16);
		frame.getContentPane().add(lblYourBalanceIs);
		
		JLabel lblTransferFeeIs = new JLabel("Transfer fee is " + from.getTransferFee()/from.getByName(name).getRate());
		lblTransferFeeIs.setBounds(31, 84, 268, 16);
		frame.getContentPane().add(lblTransferFeeIs);
		
		amountField = new JTextField();
		amountField.setBounds(159, 153, 130, 54);
		frame.getContentPane().add(amountField);
		amountField.setColumns(10);
		
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
				if(!from.transfer(amount, to, name)) {
					JOptionPane.showMessageDialog(frame, "Your don't have enought money!\n If you need money, you could consider a loan.", "Input Error!", JOptionPane.WARNING_MESSAGE);
					amountField.setText("");
					return ;
				}else {
					JOptionPane.showMessageDialog(frame, "Transfer succeeded.\nYou transfered " + amount + " " + name + " to\n" + to.getUserName() + " " + to.getType() + " " + to.getId()+"\nNow you have " + from.getByName(name).getAmount() + " "+ name + " on your account" , "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					parent.dispose() ;
					gparent.setEnabled(true);
				}
				
			}
		});
		btnConfirm.setBounds(47, 219, 117, 29);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose() ;
				parent.setEnabled(true);
			}
		});
		btnCancel.setBounds(295, 219, 117, 29);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblTransferAmount = new JLabel("Please Enter Transfer Amount");
		lblTransferAmount.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblTransferAmount.setBounds(88, 104, 291, 59);
		frame.getContentPane().add(lblTransferAmount);
	}

}
