import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class RegisterWindow {

	private JFrame frame;
	private JTextField nameField;
	private JFrame parent ;
	private Bank bank ;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterWindow window = new RegisterWindow(parent, bank);
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
	public RegisterWindow(JFrame parent, Bank bank) {
		this.bank = bank ;
		this.parent = parent ;
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
		
		nameField = new JTextField();
		nameField.setBounds(231, 84, 157, 26);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblUserName = new JLabel("Please Enter User Name:");
		lblUserName.setBounds(44, 89, 157, 16);
		frame.getContentPane().add(lblUserName);
		
		JComboBox typeBox = new JComboBox();
		typeBox.setModel(new DefaultComboBoxModel(new String[] {"Checking", "Saving"}));
		typeBox.setBounds(231, 137, 157, 27);
		frame.getContentPane().add(typeBox);
		
		JLabel lblPleaseChooseAccount = new JLabel("Please Choose Account Type:");
		lblPleaseChooseAccount.setBounds(35, 141, 184, 16);
		frame.getContentPane().add(lblPleaseChooseAccount);
		
		// OK button
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// verify input
				if(nameField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Name cannot be empty!", "Input Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				String name = nameField.getText().toString() ;
				User user = bank.getByName(name) ;
				if(user == null) {
					user = bank.createUser(name);
				}
				String id = bank.generateID() ;
				String type = typeBox.getSelectedItem().toString() ;
				// create account
				user.createAccount(bank,type, id) ;
				// popup message
				JOptionPane.showMessageDialog(frame, "Your " + type + " account is all set!\nYour user name is " + user.getName()+ ".\nYour account id is " + id + ".\nPlease keep note of your id and user name, they are your only access to your account" , "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				parent.setEnabled(true);
				
			}
		});
		btnOk.setBounds(74, 216, 117, 29);
		frame.getContentPane().add(btnOk);
		
		// cancel button
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setEnabled(true);
				frame.dispose();
			}
		});
		btnCancel.setBounds(245, 216, 117, 29);
		frame.getContentPane().add(btnCancel);
	}
}
