

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CloseAccountFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblChooseTheAccount;
	private JCheckBox chckbxChecking;;	
	private JCheckBox chckbxSaving;	
	private JLabel lblClosingAnAccount;	
	private JButton btnBack;
	private JButton btnSubmit;
	private Customer customer=new Customer("","","","");
	//private ArrayList<Customer> customers=new ArrayList<Customer>();
	//private ArrayList<Income> incomes=new ArrayList<Income>();
	//private ArrayList<Manager> managers=new ArrayList<Manager>();
	//private ArrayList<Loan> loans=new ArrayList<Loan>();
	//private ArrayList<Transaction> transactions=new ArrayList<Transaction>();
	//private Conn con;
	private AccountDao con=new AccountDao();

	/**
	 * Create the frame.
	 */
	//public CloseAccountFrame(Customer customer,
			//ArrayList<Income> incomes,ArrayList<Loan> loans,ArrayList<Transaction> transactions) {
	public CloseAccountFrame(Customer customer) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customer=customer;
		//this.customers=customers;
		//this.managers=managers;
		//this.incomes=incomes;
		//this.loans=loans;
		//this.transactions=transactions;
		//this.con=con;
		init();
		addAction();
	}
	
	public void init(){
		lblChooseTheAccount = new JLabel("Choose the account to close:");
		lblChooseTheAccount.setBounds(74, 25, 182, 15);
		contentPane.add(lblChooseTheAccount);
		
		chckbxChecking = new JCheckBox("Checking");
		chckbxChecking.setBounds(69, 66, 103, 23);
		contentPane.add(chckbxChecking);
		
		chckbxSaving = new JCheckBox("Saving");
		chckbxSaving.setBounds(225, 66, 103, 23);
		contentPane.add(chckbxSaving);
		
		lblClosingAnAccount = new JLabel("(Closing an account will pay 5 dollars for each account.)");
		lblClosingAnAccount.setBounds(51, 109, 354, 15);
		contentPane.add(lblClosingAnAccount);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(312, 213, 93, 23);
		contentPane.add(btnBack);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(69, 213, 93, 23);
		contentPane.add(btnSubmit);
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	//public ArrayList<Customer> getCustomers(){
		//return this.customers;
	//}
	
	//public ArrayList<Income> getIncomes(){
		//return this.incomes;
	//}
	
	public void reminder(String str){
		Object[] okObjects = new Object[] {"OK"};
		JOptionPane.showOptionDialog(null, str, "Message", 
				JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE,null,okObjects,null);
	}
	
	public void addAction(){	
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!chckbxChecking.isSelected()&&!chckbxSaving.isSelected()){   //reminder when no account is chosen
					reminder("Choose an account!");
				}
				if(chckbxSaving.isSelected()){
					if(customer.getSaving()==null){    //reminder when there is no saving account
						reminder("You do not have saving account!");
					}else{						
						if(chckbxChecking.isSelected()){       //if checking account is chosen
							if(customer.getChecking().getBalance().getDollar().getMoney()<10){    
								//reminder when there are not enough money to pay the charge
								reminder("You do not have enough money to close the account!"); 
							}else{
								if(customer.getChecking()==null){
									reminder("You do not have checking account!"); //reminder when there is no checking account
								}else{
									con.delete(getCustomer().getSaving().getAccountNumber());
									con.delete(getCustomer().getChecking().getAccountNumber());
									destroySaving();
									destroyChecking();
									dispose();
								}
							}
						}else{
							if(customer.getChecking().getBalance().getDollar().getMoney()<5){
								//reminder when there are not enough money to pay the charge
								reminder("You do not have enough money to close the account!");
							}else{
								con.delete(getCustomer().getSaving().getAccountNumber());
								destroySaving();
								
							}
						}
						
						
					}
				}else{
					if(chckbxChecking.isSelected()){
						if(customer.getSaving()==null){
							if(customer.getChecking()==null){
								reminder("You do not have checking account!");
							}else{
								if(customer.getChecking().getBalance().getDollar().getMoney()<5){
									//reminder when there are not enough money to pay the charge
									reminder("You do not have enough money to close the account!");
								}else{
									destroyChecking();
									dispose();
								}
							}
						}else{
							//checking account can not be chosen alone
							reminder("You will also close the saving account!Please choose saving account!");
						}
					}	
				}
				
				if(customer.getChecking()==null&&customer.getSaving()==null){  //both accounts do not exist, delete the customer.
					//customers.remove(customer);
					UserDao conn=new UserDao();
					conn.delete(customer.getUsername());
					dispose();
					Login login=new Login();
					login.setVisible(true);
				}
				//dispose();
				
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerFrame customerframe=new CustomerFrame(getCustomer());
				customerframe.setVisible(true);
				
				dispose();
			}
		});
	}
	
	public void destroyChecking(){	
		getCustomer().createChecking(null);  //close checking account
		//getIncomes().add(new Income(new Currency("Dollar",5),"Close Accounts"));  //add bank's income
		IncomeDao incomedao=new IncomeDao();
		incomedao.insert(new Income(new Currency("Dollar",5),"Close Account"));
		reminder("Close checking account successfully!");		
	}
	
	public void destroySaving(){
		getCustomer().createSaving(null);    //close saving account
		customer.getChecking().getBalance().substract(new Currency("Dollar",5));   //pay the charge
		//getIncomes().add(new Income(new Currency("Dollar",5),"Close Accounts"));   //add bank's income
		IncomeDao incomedao=new IncomeDao();
		incomedao.insert(new Income(new Currency("Dollar",5),"Close Account"));
		reminder("Close saving account successfully!");
	}
}
