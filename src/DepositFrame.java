

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;
import javax.swing.JPasswordField;

public class DepositFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEnterTheDeposit;
	private JComboBox combCurrency;	
	private JButton btnSubmit;
	private JButton btnCancle;
	private JRadioButton rdbtnChecking;	
	private JRadioButton rdbtnSaving;
	private ButtonGroup btngroup;
	private Customer customer=new Customer("","","","");
	private JLabel lblEnterYourPin;
	private JPasswordField passwordField;
	private AccountDao con=new AccountDao();
	private Tool tool=new Tool();

	/**
	 * Create the frame.
	 */
	public DepositFrame(Customer customer) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customer=customer;
		init();
		addAction();
	}
	
	public void init(){
		lblEnterTheDeposit = new JLabel("Enter the deposit:");
		lblEnterTheDeposit.setBounds(79, 29, 143, 15);
		contentPane.add(lblEnterTheDeposit);
		
		textField = new JTextField();
		textField.setBounds(166, 56, 204, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		String[] currency={"Dollar","RMB","Euro"};
		combCurrency = new JComboBox(currency);
		combCurrency.setBounds(79, 56, 68, 21);
		contentPane.add(combCurrency);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(79, 194, 93, 23);
		contentPane.add(btnSubmit);
		
		btnCancle = new JButton("Cancle");
		btnCancle.setBounds(259, 194, 93, 23);
		contentPane.add(btnCancle);
		
		rdbtnChecking = new JRadioButton("Checking");
		rdbtnChecking.setBounds(81, 90, 121, 23);
		contentPane.add(rdbtnChecking);
		
		rdbtnSaving = new JRadioButton("Saving");
		rdbtnSaving.setBounds(249, 90, 121, 23);
		contentPane.add(rdbtnSaving);
		
		btngroup=new ButtonGroup();
		btngroup.add(rdbtnChecking);
		btngroup.add(rdbtnSaving);
		
		lblEnterYourPin = new JLabel("Enter your PIN:");
		lblEnterYourPin.setBounds(78, 119, 111, 15);
		contentPane.add(lblEnterYourPin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(79, 144, 291, 21);
		contentPane.add(passwordField);
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	
	public void addAction(){
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currency=(String)combCurrency.getSelectedItem();
				String deposit=textField.getText();
				String PINnumber=String.valueOf(passwordField.getPassword());
				if(deposit.equals("")||!tool.isNumeric(deposit)){    //check number of deposit
					tool.reminder("Please input the right deposit!");
				}else{
					if(Double.parseDouble(deposit)<=0){    //check number of deposit
						tool.reminder("Please input the positive number!");
					}else{
						if(!rdbtnChecking.isSelected()&&!rdbtnSaving.isSelected()){   //check if there is an account be chosen
							tool.reminder("Please choose one account!");
						}else{
							if(!PINnumber.equals(getCustomer().getChecking().getMoneypassword())||!tool.isNumeric(PINnumber)){//check the PIN number
								tool.reminder("Wrong PIN number!");
							}else{
								if(rdbtnChecking.isSelected()){		 //deposit in checking account						
									double depositnumber=Double.parseDouble(deposit);
									Currency curren=new Currency(currency,depositnumber);
									getCustomer().getChecking().getBalance().add(curren);
									con.update(getCustomer().getChecking());
									tool.reminder("Deposit successfully!");
									dispose();
								}
							
								if(rdbtnSaving.isSelected()){     //deposit in saving account	
									if(getCustomer().getSaving()!=null){
										double depositnumber=Double.parseDouble(deposit);
										Currency curren=new Currency(currency,depositnumber);
										getCustomer().getSaving().getBalance().add(curren);
										con.update(getCustomer().getSaving());
										tool.reminder("Deposit successfully!");
										dispose();
									}else{
										tool.reminder("You do not have saving account!");
									}
								}
								
							}
						}
					}
				}
			}			
		});
		
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
