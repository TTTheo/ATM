import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu {

	private JFrame frame;
	private JTextField idField;
	private JTextField nameField;
	private Bank bank ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		bank = new Bank() ;
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
		frame.setLocationRelativeTo(null);
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// generating error message
				StringBuilder sb = new StringBuilder() ;
				String name = null;
				String id = null;
				if(nameField.getText().isEmpty())
					sb.append("Name cannot be empty!\n") ;
				else name = nameField.getText() ;
				if(idField.getText().isEmpty())
					sb.append("ID cannot be empty!\n") ;
				else id = idField.getText() ;
				if(sb.length() > 0) {
					JOptionPane.showMessageDialog(frame, sb.toString(), "Input Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				
				// get user and account info
				User user = bank.getByName(name) ;
				if(user == null) {
					JOptionPane.showMessageDialog(frame, "User doesn't exist!", "Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				Account account = user.getByID(id) ;
				if(account == null) {
					JOptionPane.showMessageDialog(frame, "Account doesn't exist!", "Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				AccountMenu acc = new AccountMenu(frame, bank, account) ;
				
				// clear text
				nameField.setText("");
				idField.setText("");
				// start new window
				acc.start();
				
			}
		});
		loginButton.setBounds(30, 220, 117, 29);
		frame.getContentPane().add(loginButton);
		
		JButton registerButton = new JButton("Create Account");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterWindow reg = new RegisterWindow(frame, bank) ;
				reg.start();
				
			}
		});
		registerButton.setBounds(170, 220, 131, 29);
		frame.getContentPane().add(registerButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(313, 220, 117, 29);
		frame.getContentPane().add(exitButton);
		
		idField = new JTextField();
		idField.setBounds(146, 134, 238, 26);
		frame.getContentPane().add(idField);
		
		nameField = new JTextField();
		nameField.setBounds(146, 68, 238, 26);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(57, 68, 77, 26);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblAccountId = new JLabel("Account ID:");
		lblAccountId.setBounds(57, 135, 77, 26);
		frame.getContentPane().add(lblAccountId);
		
		JLabel lblWelcomeToOnline = new JLabel("Welcome to Online Fancy Bank");
		lblWelcomeToOnline.setBounds(130, 40, 207, 16);
		frame.getContentPane().add(lblWelcomeToOnline);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 22);
		frame.getContentPane().add(menuBar);
		JMenu man = new JMenu("Manager") ;
		menuBar.add(man) ;
		JMenu help = new JMenu("Help") ;
		menuBar.add(help) ;
		JMenuItem report = new JMenuItem("Report") ;
		report.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportWindow rw = new ReportWindow(frame, bank) ;
				rw.start();
				frame.setEnabled(false);
			}
		});
		man.add(report) ;
		JMenuItem lookup = new JMenuItem("Lookup") ;
		lookup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LookUpWindow lw = new LookUpWindow(frame, bank) ;
				lw.start();
			}
		});
		man.add(lookup) ;
		JMenuItem readMe = new JMenuItem("README") ;
		readMe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				README rm = new README() ;
				rm.start();
			}
		});
		help.add(readMe) ;
		JMenuItem test = new JMenuItem("Test Accounts") ;
		test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestAccounts ta = new TestAccounts() ;
				ta.start();
			}
		});
		help.add(test) ;
	}
}
