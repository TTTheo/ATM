

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
	private ArrayList<Income> incomes=new ArrayList<Income>();
	private JLabel lblEnterYourPin;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public DepositFrame(Customer customer,ArrayList<Income> incomes) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customer=customer;
		this.incomes=incomes;
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
		//combCurrency.addItemListener(this);
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
	
	public ArrayList<Income> getIncomes(){
		return this.incomes;
	}
	
	public static boolean isNumeric(String str){  //check if the string composed with numbers
		for (int i = str.length();--i>=0;){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	public void reminder(String str){
		Object[] okObjects = new Object[] {"OK"};
		JOptionPane.showOptionDialog(null, str, "Message", 
				JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE,null,okObjects,null);
	}
	
	
	public void addAction(){
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currency=(String)combCurrency.getSelectedItem();
				String deposit=textField.getText();
				String PINnumber=String.valueOf(passwordField.getPassword());
				if(deposit.equals("")||!isNumeric(deposit)){    //check number of deposit
					reminder("Please input the right deposit!");
				}else{
					if(Double.parseDouble(deposit)<=0){    //check number of deposit
						reminder("Please input the positive number!");
					}else{
						if(!rdbtnChecking.isSelected()&&!rdbtnSaving.isSelected()){   //check if there is an account be chosen
							reminder("Please choose one account!");
						}else{
							if(!PINnumber.equals(getCustomer().getChecking().getMoneypassword())||!isNumeric(PINnumber)){//check the PIN number
								reminder("Wrong PIN number!");
							}else{
								if(rdbtnChecking.isSelected()){		 //deposit in checking account						
									double depositnumber=Double.parseDouble(deposit);
									Currency curren=new Currency(currency,depositnumber);
									getCustomer().getChecking().getBalance().add(curren);
									reminder("Deposit successfully!");
									dispose();
								}
							
								if(rdbtnSaving.isSelected()){     //deposit in saving account	
									if(getCustomer().getSaving()!=null){
										double depositnumber=Double.parseDouble(deposit);
										Currency curren=new Currency(currency,depositnumber);
										getCustomer().getSaving().getBalance().add(curren);
										reminder("Deposit successfully!");
										dispose();
									}else{
										reminder("You do not have saving account!");
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
