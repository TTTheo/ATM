

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BalanceFrame extends JFrame {

	private JPanel contentPane;
	private Customer customer=new Customer("","","","");
	private JLabel lblCheckingAccount;	
	private JLabel lblSavingAccount;	
	private JButton btnBack;
	private JLabel lblCheckingMoney;	
	private JLabel lblSavingMoney;
	private JLabel lblCheckingrmb;
	private JLabel lblCheckingeruo;
	private JLabel lblSavingrmb;
	private JLabel lblSavingeruo;
	/**
	 * Create the frame.
	 */
	public BalanceFrame(Customer customer) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		this.customer=customer;
		showBalance();
		addAction();
	}
	
	public void init(){
		lblCheckingAccount = new JLabel("Checking account:");
		lblCheckingAccount.setBounds(72, 58, 138, 15);
		contentPane.add(lblCheckingAccount);
		
		lblSavingAccount = new JLabel("");
		lblSavingAccount.setBounds(71, 139, 109, 15);
		contentPane.add(lblSavingAccount);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(308, 217, 93, 23);
		contentPane.add(btnBack);
		
		lblCheckingMoney = new JLabel("    ");
		lblCheckingMoney.setBounds(184, 58, 138, 15);
		contentPane.add(lblCheckingMoney);
		
		lblSavingMoney = new JLabel("");
		lblSavingMoney.setBounds(184, 139, 138, 15);
		contentPane.add(lblSavingMoney);
		
		lblCheckingrmb = new JLabel("checkingrmb");
		lblCheckingrmb.setBounds(184, 83, 163, 15);
		contentPane.add(lblCheckingrmb);
		
		lblCheckingeruo = new JLabel("checkingeruo");
		lblCheckingeruo.setBounds(184, 108, 138, 15);
		contentPane.add(lblCheckingeruo);
		
		lblSavingrmb = new JLabel("");
		lblSavingrmb.setBounds(184, 163, 138, 15);
		contentPane.add(lblSavingrmb);
		
		lblSavingeruo = new JLabel("");
		lblSavingeruo.setBounds(184, 188, 163, 15);
		contentPane.add(lblSavingeruo);
	}
	
	public void setCustomer(Customer customer){
		this.customer=customer;
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	public void showBalance(){  //set the balance in label
		lblCheckingMoney.setText(getCustomer().getChecking().getBalance().showDollar());		
		lblCheckingrmb.setText(getCustomer().getChecking().getBalance().showRMB());
		lblCheckingeruo.setText(getCustomer().getChecking().getBalance().showEuro());
		if(getCustomer().getSaving()!=null){     //if saving account do not exist, it will not show
			lblSavingAccount.setText("Saving account:");
			lblSavingMoney.setText(getCustomer().getSaving().getBalance().showDollar());
			lblSavingrmb.setText(getCustomer().getSaving().getBalance().showRMB());
			lblSavingeruo.setText(getCustomer().getSaving().getBalance().showEuro());
		}
	}
	
	public void addAction(){
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
