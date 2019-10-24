import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExchangeWindow {

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
					ExchangeWindow window = new ExchangeWindow(parent, account, name);
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
	public ExchangeWindow(JFrame parent, Account account, String name) {
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
		
		JTextPane outputPane = new JTextPane();
		outputPane.setEditable(false);
		outputPane.setBounds(164, 189, 117, 16);
		frame.getContentPane().add(outputPane);
		
		JLabel lblExchangingUsTo = new JLabel("Exchanging " + name + " to other currencies");
		lblExchangingUsTo.setBounds(25, 24, 352, 16);
		frame.getContentPane().add(lblExchangingUsTo);
		
		
		String[] currencyArray = new String[account.getCurrencies().size()] ;
		for(int i = 0 ; i < currencyArray.length ; i++) {
			currencyArray[i] = account.getCurrencies().get(i).getName() ;
		}
		JComboBox toCurr = new JComboBox(currencyArray);
		toCurr.setBounds(158, 115, 178, 27);
		frame.getContentPane().add(toCurr);
		
		JLabel lblChangeTo = new JLabel("Change to:");
		lblChangeTo.setBounds(68, 119, 110, 16);
		frame.getContentPane().add(lblChangeTo);
		
		JLabel lblYourAvailableBalance = new JLabel("Your available balance is " + account.getByName(name).getAmount());
		lblYourAvailableBalance.setBounds(25, 52, 302, 16);
		frame.getContentPane().add(lblYourAvailableBalance);
		
		JLabel lblExchangeFeeIs = new JLabel("Exchange fee is " + account.getExchangeFee()/account.getByName(name).getRate());
		lblExchangeFeeIs.setBounds(25, 80, 338, 16);
		frame.getContentPane().add(lblExchangeFeeIs);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
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
				String to = toCurr.getSelectedItem().toString() ;
				double output = amount*account.getByName(name).getRate()/account.getByName(to).getRate() ;
				outputPane.setText(output + " " + toCurr.getSelectedItem().toString());
				
			}
		});
		btnCheck.setBounds(327, 152, 117, 29);
		frame.getContentPane().add(btnCheck);
		
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
				String to = toCurr.getSelectedItem().toString() ;
				if(!account.exchange(amount, name, to)) {
					JOptionPane.showMessageDialog(frame, "Your don't have enought money!\n If you need money, you could consider a loan.", "Input Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}else {
					JOptionPane.showMessageDialog(frame, "Exchange succeeded.\nNow you have changed" + amount + " "+ name + " to " + amount*account.getByName(name).getRate()/account.getByName(to).getRate() + " " + to, "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					parent.setEnabled(true);
				}
			}
		});
		btnConfirm.setBounds(68, 232, 117, 29);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				parent.setEnabled(true);
			}
		});
		btnCancel.setBounds(260, 232, 117, 29);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblExchangeAmount = new JLabel("Exchange Amount: ");
		lblExchangeAmount.setBounds(33, 161, 145, 16);
		frame.getContentPane().add(lblExchangeAmount);
		
		amountField = new JTextField();
		amountField.setBounds(159, 154, 168, 26);
		frame.getContentPane().add(amountField);
		amountField.setColumns(10);
		
		JLabel lblEqualsTo = new JLabel("Equals to: ");
		lblEqualsTo.setBounds(91, 189, 124, 16);
		frame.getContentPane().add(lblEqualsTo);
		
	}

}
