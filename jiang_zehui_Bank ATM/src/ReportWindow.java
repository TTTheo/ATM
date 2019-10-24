import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

public class ReportWindow {

	private JFrame frame;
	private JFrame parent ;
	private Bank bank ;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportWindow window = new ReportWindow(parent, bank);
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
	public ReportWindow(JFrame parent, Bank bank) {
		this.parent = parent ;
		this.bank = bank ;
		initialize();
	}
	/**
	 * Helper functions.
	 */
	public String generateProfit() {
		HashMap<String, Double> map = bank.getProfitMap() ;
		StringBuilder sb = new StringBuilder() ;
		for(String name : map.keySet()) {
			sb.append(name + " profit is: \n" + map.get(name) + "\n\n") ; 
		}
		return sb.toString() ;
	}
	
	public DefaultListModel<String> generateListModel(){
		DefaultListModel<String> dlm = new DefaultListModel<>();	
		for(int i = 0 ; i < bank.getTransaction().size() ; i++) {
			dlm.add(i, bank.getTransaction().get(i));
		}
		
		return dlm ;
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
		
		JLabel lblWelcomeMyAllmighty = new JLabel("Welcome, my ALL-MIGHTY Manager!");
		lblWelcomeMyAllmighty.setBounds(6, 6, 286, 16);
		frame.getContentPane().add(lblWelcomeMyAllmighty);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(169, 62, 275, 202);
		frame.getContentPane().add(scrollPane);
		
		JList list = new JList();
		list.setModel(generateListModel());
		scrollPane.setViewportView(list);
		
		JLabel lblTransactionOfThe = new JLabel("Transaction of the day");
		lblTransactionOfThe.setBounds(229, 34, 168, 16);
		frame.getContentPane().add(lblTransactionOfThe);
		
		JLabel lblProfits = new JLabel("Profits: ");
		lblProfits.setBounds(57, 34, 100, 16);
		frame.getContentPane().add(lblProfits);
		
		JTextPane profitPane = new JTextPane();
		profitPane.setEditable(false);
		profitPane.setBounds(6, 62, 151, 202);
		frame.getContentPane().add(profitPane);
		profitPane.setText(generateProfit());
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose() ;
				parent.setEnabled(true);
			}
		});
		btnDone.setBounds(327, 1, 117, 29);
		frame.getContentPane().add(btnDone);
		
	}
}
