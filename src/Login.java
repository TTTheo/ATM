
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;

public class Login extends JFrame {

	private JPanel contentPane;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField username;
	private JRadioButton rdbtnCustomer;
	private JRadioButton rdbtnManager;
	private JLabel lblCustomerDoNot;
	private JButton btnOpenANew;
	private JButton btnLogin;
	private ButtonGroup btngroup;
	private ArrayList<Income> incomes=new ArrayList<Income>();
	//private ArrayList<Customer> customers=new ArrayList<Customer>();
	//private ArrayList<Manager> managers=new ArrayList<Manager>();
	private ArrayList<Loan> loans=new ArrayList<Loan>();
	private ArrayList<Transaction> transactions=new ArrayList<Transaction>();
	private Customer customer;
	private JPasswordField password;
	private Tool tool=new Tool();
	//private Conn con;
	
	/**
	 * Create the frame.
	 */
	//public Login(ArrayList<Income> incomes,ArrayList<Loan> loans,ArrayList<Transaction> transactions) {
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
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
		lblUsername = new JLabel("Username: ");
		lblUsername.setBounds(76, 56, 93, 22);
		contentPane.add(lblUsername);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(76, 103, 74, 15);
		contentPane.add(lblPassword);
		
		username = new JTextField();
		username.setBounds(158, 57, 179, 21);
		contentPane.add(username);
		username.setColumns(10);
		
		
		rdbtnCustomer = new JRadioButton("Customer");
		rdbtnCustomer.setBounds(123, 131, 81, 23);
		contentPane.add(rdbtnCustomer);
		
		rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.setBounds(236, 131, 81, 23);
		contentPane.add(rdbtnManager);
		btngroup=new ButtonGroup();
		btngroup.add(rdbtnCustomer);
		btngroup.add(rdbtnManager);
		
		lblCustomerDoNot = new JLabel("Customer do not have an account?");
		lblCustomerDoNot.setBounds(48, 238, 200, 15);
		contentPane.add(lblCustomerDoNot);
		
		btnOpenANew = new JButton("Open a new account");
		btnOpenANew.setBounds(258, 234, 154, 23);
		contentPane.add(btnOpenANew);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(173, 170, 93, 23);
		contentPane.add(btnLogin);
		
		password = new JPasswordField();
		password.setBounds(160, 100, 177, 21);
		contentPane.add(password);
	}
	
	
	public void addAction(){
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName=username.getText();
				String passWord=String.valueOf(password.getPassword());
				
				if(rdbtnCustomer.isSelected()){   //login as customer
					boolean ifright=false;
					//check if the username and the password are correct
					UserDao conn=new UserDao();
					User user=conn.select(userName);
					if(user==null){
						ifright=false;
					}else{
						if(user.getPassword().equals(passWord)){
							ifright=true;
							AccountDao con=new AccountDao();
							Checking check=(Checking) con.selectChecking(userName);
							Saving save=(Saving) con.selectSaving(userName);
							customer=new Customer(user.getName(),user.getUsername(),user.getPassword(),user.getPhone());
							customer.createChecking(check);
							customer.createSaving(save);
							LoanDao loandao=new LoanDao();
							ArrayList<Loan> loans=(ArrayList<Loan>) loandao.selectSpecific(customer.getUsername());
							customer.setLoans(loans);
							AccountStockDao stockdao=new AccountStockDao();
							InvestmentDao investdao=new InvestmentDao();
							Investment invest=investdao.select(userName);
							customer.createInvest(invest);
							ArrayList<CustomerStock> custock=(ArrayList<CustomerStock>) stockdao.selectListofAccount(customer.getInvest().getAccountID());
							customer.getInvest().setStock(custock);
							//create investment
							CustomerFrame customerFrame=new CustomerFrame(getCustomer());
							customerFrame.setVisible(true);
							dispose();
						}
						//setCustomer(customers.get(i));
						
					}
					/*for(int i=0;i<customers.size();i++){  
						//check if the username and the password are correct
						/*if(customers.get(i).getUser().getUsername().equals(userName)&&customers.get(i).getUser().getPassword().equals(passWord)){
							setCustomer(customers.get(i));
							
							CustomerFrame customerFrame=new CustomerFrame(getCustomer(),customers,managers,incomes,loans,transactions);
							customerFrame.setVisible(true);
							ifright=true; 
							dispose();
							setCustomers(customerFrame.getCustomers());
							setTransactions(customerFrame.getTransactions());
							setLoans(customerFrame.getLoans());
							break;
						}	
						
					}*/
					if(!ifright){  //if wrong, reminder
						tool.reminder("Wrong Username or Password!");
					}
					
				}else if(rdbtnManager.isSelected()){   //login as manager
					boolean ifright=false;
					ManagerDao conn=new ManagerDao();
					Manager mg=conn.select(userName);
					if(mg==null){
						ifright=false;
					}else{
						if(mg.getPassword().equals(passWord)){
							ifright=true;
							ManagerFrame managerFrame=new ManagerFrame();
							managerFrame.setVisible(true);
							dispose();
						}
					}
					/*for(int i=0;i<managers.size();i++){
						//check if the username and the password are correct
						if(managers.get(i).getUser().getUsername().equals(userName)&&managers.get(i).getUser().getPassword().equals(passWord)){
							ManagerFrame managerFrame=new ManagerFrame(customers,managers,incomes,loans,transactions);
							managerFrame.setVisible(true);
							ifright=true;
							dispose();
							break;
						}						
					}*/
					if(!ifright){
						tool.reminder("Wrong Username or Password!");
					}
					
				}else{
					//check if the user choose the type of users
					tool.reminder("Choose you are customer or manager!");
				}
			}
		});
		
		btnOpenANew.addActionListener(new ActionListener() {    //open a new account
			public void actionPerformed(ActionEvent e) {
				OpenAccount newAccount=new OpenAccount();
				newAccount.setVisible(true);
				//setCustomers(newAccount.getCusto());
				//setIncomes(newAccount.getIncomes());
			}
		});
	}
	
	
	public void setCustomer(Customer customer){
		this.customer=customer;
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	/*public void setIncomes(ArrayList<Income> incomes){
		this.incomes=incomes;
	}
	
	public void setTransactions(ArrayList<Transaction> transactions){
		this.transactions=transactions;
	}
	
	public void setLoans(ArrayList<Loan> loans){
		this.loans=loans;
	}
	
	
	public ArrayList<Customer> getCustomers(){
		return this.customers;
	}
	
	public void setCustomers(ArrayList<Customer> customers){
		this.customers=customers;
	}*/
	
	
}
