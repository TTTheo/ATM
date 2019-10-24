import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestAccounts {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestAccounts window = new TestAccounts();
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
	public TestAccounts() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 165);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTestAccounts = new JLabel("Test Accounts");
		lblTestAccounts.setBounds(0, 0, 450, 16);
		frame.getContentPane().add(lblTestAccounts);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(10, 28, 584, 80);
		frame.getContentPane().add(textPane);
		String info = "User name: Morty Account id: 1  Account type: Checking  All currencies set to 0\n" + 
				"User name: Morty Account id: 2  Account type: Saving      All currencies set to 0\n" + 
				"User name: Rick   Account id: 11 Account type: Checking All currencies set to 5000\n" + 
				"User name: Rick   Account id: 22 Account type: Saving     All currencies set to 5000\n";
		textPane.setText(info);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnClose.setBounds(236, 110, 117, 29);
		frame.getContentPane().add(btnClose);
	}

}
