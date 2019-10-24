import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LookUpWindow {

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
					LookUpWindow window = new LookUpWindow(parent, bank);
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
	public LookUpWindow(JFrame parent, Bank bank) {
		this.bank = bank ;
		this.parent = parent ;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 320, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(parent);
		parent.setEnabled(false);
		
		JLabel lblLookupWizard = new JLabel("Lookup Wizard");
		lblLookupWizard.setBounds(109, 47, 140, 16);
		frame.getContentPane().add(lblLookupWizard);
		
		JButton btnById = new JButton("By ID");
		btnById.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IDLookUp ilu = new IDLookUp(frame, bank) ;
				ilu.start();
				
			}
		});
		btnById.setBounds(16, 74, 117, 29);
		frame.getContentPane().add(btnById);
		
		JButton btnByUserName = new JButton("By User Name");
		btnByUserName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NameLookUp nlu = new NameLookUp(frame, bank) ;
				nlu.start();
			}
		});
		btnByUserName.setBounds(177, 74, 117, 29);
		frame.getContentPane().add(btnByUserName);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				parent.setEnabled(true);
			}
		});
		btnCancel.setBounds(246, 6, 68, 29);
		frame.getContentPane().add(btnCancel);
	}

}
