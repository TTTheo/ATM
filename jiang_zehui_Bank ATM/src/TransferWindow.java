import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransferWindow {

	private JFrame frame;
	private JFrame parent ;
	private Bank bank ;
	private Account account ;
	private String name ;
	private JTextField nameField;
	private JTextField idField;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransferWindow window = new TransferWindow(parent, bank, account, name);
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
	public TransferWindow(JFrame parent, Bank bank, Account account, String name) {
		this.bank = bank ;
		this.account = account ;
		this.name = name ;
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
		frame.setLocationRelativeTo(parent);
		parent.setEnabled(false);
		frame.getContentPane().setLayout(null);
		
		nameField = new JTextField();
		nameField.setBounds(236, 101, 155, 26);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		idField = new JTextField();
		idField.setBounds(236, 160, 155, 26);
		frame.getContentPane().add(idField);
		idField.setColumns(10);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder() ;
				String userName = null;
				String id = null;
				if(nameField.getText().isEmpty())
					sb.append("Name cannot be empty!\n") ;
				else userName = nameField.getText() ;
				if(idField.getText().isEmpty())
					sb.append("ID cannot be empty!\n") ;
				else id = idField.getText() ;
				if(sb.length() > 0) {
					JOptionPane.showMessageDialog(frame, sb.toString(), "Input Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				
				// get user and account info
				User user = bank.getByName(userName) ;
				if(user == null) {
					JOptionPane.showMessageDialog(frame, "User doesn't exist!", "Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				Account to = user.getByID(id) ;
				if(to == null) {
					JOptionPane.showMessageDialog(frame, "Account doesn't exist!", "Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				TransferCurrencyWindow tcw = new TransferCurrencyWindow(parent, frame, account, to, name) ;
				tcw.start();
			}
		});
		btnContinue.setBounds(80, 210, 117, 29);
		frame.getContentPane().add(btnContinue);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				parent.setEnabled(true) ;
			}
		});
		btnCancel.setBounds(274, 210, 117, 29);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblTransferingUs = new JLabel("Transfering US");
		lblTransferingUs.setBounds(27, 49, 117, 16);
		frame.getContentPane().add(lblTransferingUs);
		
		JLabel lblReceiversUserName = new JLabel("Receiver's User Name:");
		lblReceiversUserName.setBounds(80, 106, 163, 16);
		frame.getContentPane().add(lblReceiversUserName);
		
		JLabel lblReceiversAccountId = new JLabel("Receiver's Account ID:");
		lblReceiversAccountId.setBounds(80, 165, 154, 16);
		frame.getContentPane().add(lblReceiversAccountId);
		parent.setEnabled(false);
	}

}
