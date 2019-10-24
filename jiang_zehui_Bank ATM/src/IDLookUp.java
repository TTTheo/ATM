import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class IDLookUp {

	private JFrame frame;
	private JFrame parent ;
	private Bank bank ;
	private JTextField idField;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDLookUp window = new IDLookUp(parent, bank);
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
	public IDLookUp(JFrame parent, Bank bank) {
		this.parent = parent ;
		this.bank = bank ;
		initialize();
	}

	/**
	 * Helper functions.
	 */
	public String getInfo(String id) {
		StringBuilder sb = new StringBuilder() ;
		Account goal = null ;
		for(User user : bank.getUsers()) {
			Account temp = user.getByID(id) ;
			if(temp != null) {
				goal = temp ;
				break ;
			}
		}
		if(goal == null)
			return null ;
		sb.append(goal.toStirng() + "\n") ;
		sb.append("Interest Rate: " + goal.getInterest() + "\n") ;
		sb.append("Currencies:  \n") ;
		sb.append("--------------------------\n") ;
		for(Currency curr : goal.getCurrencies()){
			sb.append(curr.toString()) ;
			sb.append("--------------------------\n") ;
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
		frame.setLocationRelativeTo(parent);
		frame.getContentPane().setLayout(null);
		parent.setEnabled(false);
		
		JLabel lblPleaseEnterId = new JLabel("Please Enter ID: ");
		lblPleaseEnterId.setBounds(42, 26, 101, 16);
		frame.getContentPane().add(lblPleaseEnterId);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 59, 367, 183);
		frame.getContentPane().add(scrollPane);
		
		JTextPane outputPane = new JTextPane();
		scrollPane.setViewportView(outputPane);
		
		idField = new JTextField();
		idField.setBounds(155, 21, 130, 26);
		frame.getContentPane().add(idField);
		idField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = null;
				if(idField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame,"ID cannot be empty!", "Input Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}
				id = idField.getText() ;
				String info = getInfo(id) ;
				if(info == null) {
					JOptionPane.showMessageDialog(frame,"ID dosn't exist!", "Search Complete!", JOptionPane.INFORMATION_MESSAGE);
				}
				outputPane.setText(info);
				
			}
		});
		btnSearch.setBounds(297, 21, 117, 29);
		frame.getContentPane().add(btnSearch);
		
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				parent.setEnabled(true);
			}
		});
		btnBack.setBounds(167, 243, 117, 29);
		frame.getContentPane().add(btnBack);
		
	}
}
