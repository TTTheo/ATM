import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RepaymentWindow {

	private JFrame frame;
	private JFrame parent ;
	private Account account ;
	private String name ;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RepaymentWindow window = new RepaymentWindow(parent, account, name);
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
	public RepaymentWindow(JFrame parent, Account account, String name) {
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
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsLoanRepayment = new JLabel("US Loan Repayment");
		lblUsLoanRepayment.setBounds(30, 30, 173, 16);
		frame.getContentPane().add(lblUsLoanRepayment);
		
		JLabel lblYourTotalLoan = new JLabel("Your total loan repayment is: ");
		lblYourTotalLoan.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblYourTotalLoan.setBounds(65, 65, 310, 38);
		frame.getContentPane().add(lblYourTotalLoan);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!account.payLoan(name)) {
					JOptionPane.showMessageDialog(frame, "Your don't have enought money!\n If you need money, you could consider another loan.", "Input Error!", JOptionPane.WARNING_MESSAGE);
					return ;
				}else {
					JOptionPane.showMessageDialog(frame, "Repayment succeeded.\nNow you have " + account.getByName(name).getAmount() + " "+ name + " on your account" , "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					parent.setEnabled(true);
				}
				
			}
		});
		btnConfirm.setBounds(65, 190, 117, 29);
		frame.getContentPane().add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				parent.setEnabled(true);
			}
		});
		btnCancel.setBounds(246, 190, 117, 29);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblWouldYouLike = new JLabel("Are you sure you want to repay it?");
		lblWouldYouLike.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblWouldYouLike.setBounds(65, 149, 297, 29);
		frame.getContentPane().add(lblWouldYouLike);
		
		JLabel lblAccountgetbynamenamegetloanamount = new JLabel(account.getByName(name).getLoanAmount() + "");
		lblAccountgetbynamenamegetloanamount.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblAccountgetbynamenamegetloanamount.setBounds(65, 102, 253, 22);
		frame.getContentPane().add(lblAccountgetbynamenamegetloanamount);
		parent.setEnabled(false);
	}

}
