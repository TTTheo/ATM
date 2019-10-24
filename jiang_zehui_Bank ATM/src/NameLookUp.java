import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NameLookUp {

	private JFrame frame;
	private JFrame parent ;
	private Bank bank ;
	private JTextField nameField;
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NameLookUp window = new NameLookUp(parent, bank);
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
	public NameLookUp(JFrame parent, Bank bank) {
		this.parent = parent ;
		this.bank = bank ;
		initialize();
	}


	/**
	 * Helper functions.
	 */
	public String getInfo(String name) {
		StringBuilder sb = new StringBuilder() ;
		User goal = null ;
		for(User user : bank.getUsers()) {
			if(user.getName().equals(name)) {
				goal = user ;
				break ;
			}
		}
		if(goal == null)
			return null ;
		sb.append("User Name: " + name + "\n") ;
		sb.append(goal.getAccounts().size() + " accounts\n") ;
		sb.append("Here are the details: \n") ;
		
		for(Account account : goal.getAccounts()) {
			sb.append("--------------------------\n") ;
			sb.append("--------------------------\n") ;
			sb.append(account.toStirng() + "\n") ;
			sb.append("Interest Rate: " + account.getInterest() + "\n") ;
			sb.append("Currencies:  \n") ;
			sb.append("--------------------------\n") ;
			for(Currency curr : account.getCurrencies()){
				sb.append(curr.toString()) ;
				sb.append("--------------------------\n") ;
			}
		}
		
		return sb.toString() ;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(parent);
		parent.setEnabled(false);
		
		JLabel lblPleaseEnterUser = new JLabel("Please Enter User Name:");
		lblPleaseEnterUser.setBounds(18, 36, 162, 16);
		frame.getContentPane().add(lblPleaseEnterUser);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 64, 411, 173);
		frame.getContentPane().add(scrollPane);
		
		JTextPane outputPane = new JTextPane();
		scrollPane.setViewportView(outputPane);
		
		
		nameField = new JTextField();
		nameField.setBounds(180, 31, 130, 26);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = null;
				if(nameField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Name cannot be empty!", "Input Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				name = nameField.getText() ;
				String info = getInfo(name) ;
				if(info == null) {
					JOptionPane.showMessageDialog(frame,"User dosn't exist!", "Search Complete!", JOptionPane.INFORMATION_MESSAGE);
				}
				outputPane.setText(info);
				
			}
		});
		btnSearch.setBounds(322, 31, 117, 29);
		frame.getContentPane().add(btnSearch);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				parent.setEnabled(true);
			}
		});
		btnBack.setBounds(161, 243, 117, 29);
		frame.getContentPane().add(btnBack);
	}

}
