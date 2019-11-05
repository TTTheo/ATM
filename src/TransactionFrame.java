

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JPasswordField;

public class TransactionFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textTransfer;
	private JTextField textAmount;
	private JLabel lblTransferTo;	
	private JLabel lblAmount;	
	private JComboBox comboBox;	
	private JButton btnSubmit;	
	private JButton btnCancle;
	private Customer customer=new Customer("","","","");
	//private ArrayList<Income> incomes=new ArrayList<Income>();
	//private ArrayList<Customer> customers=new ArrayList<Customer>();
	//private ArrayList<Transaction> transactions=new ArrayList<Transaction>();
	private JLabel lblEnterYourPin;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	//public TransactionFrame(Customer customer,ArrayList<Income> incomes,ArrayList<Transaction> transactions) {
	public TransactionFrame(Customer customer) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customer=customer;
		//this.customers=customers;
		//this.incomes=incomes;
		//this.transactions=transactions;
		init();
		addAction();
		
	}

	public void init(){
		lblTransferTo = new JLabel("Transfer to:");
		lblTransferTo.setBounds(60, 41, 77, 15);
		contentPane.add(lblTransferTo);
		
		textTransfer = new JTextField();
		textTransfer.setBounds(147, 38, 227, 21);
		contentPane.add(textTransfer);
		textTransfer.setColumns(10);
		
		lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(60, 93, 54, 15);
		contentPane.add(lblAmount);
		
		String[] currency={"Dollar","RMB","Euro"};
		comboBox = new JComboBox(currency);
		comboBox.setBounds(116, 90, 67, 21);
		contentPane.add(comboBox);
		
		textAmount = new JTextField();
		textAmount.setBounds(193, 90, 181, 21);
		contentPane.add(textAmount);
		textAmount.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(59, 209, 93, 23);
		contentPane.add(btnSubmit);
		
		btnCancle = new JButton("Cancle");
		btnCancle.setBounds(267, 209, 93, 23);
		contentPane.add(btnCancle);
		
		lblEnterYourPin = new JLabel("Enter your PIN:");
		lblEnterYourPin.setBounds(60, 132, 92, 15);
		contentPane.add(lblEnterYourPin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(60, 157, 314, 21);
		contentPane.add(passwordField);
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	/*public ArrayList<Customer> getCustomers(){
		return this.customers;
	}
	
	public ArrayList<Income> getIncomes(){
		return this.incomes;
	}
	
	public ArrayList<Transaction> getTransactions(){
		return this.transactions;
	}
	public void setTransactions(ArrayList<Transaction> transactions){
		this.transactions=transactions;
	}*/
	
	public static boolean isNumeric(String str){
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
				String currency=(String)comboBox.getSelectedItem();
				String transferAmount=textAmount.getText();
				String PINnumber=String.valueOf(passwordField.getPassword());
				if(transferAmount.equals("")||!isNumeric(transferAmount)){    //check transaction number
					reminder("Please input right transaction!");
				}else{
					double transfernumber=Double.parseDouble(transferAmount)+5;
					double actualnumber=0;
					if(currency.equals("Dollar")){
						actualnumber=getCustomer().getChecking().getBalance().getDollar().getMoney();
					}else if(currency.equals("RMB")){
						actualnumber=getCustomer().getChecking().getBalance().getRMB().getMoney();
					}else if(currency.equals("Euro")){
						actualnumber=getCustomer().getChecking().getBalance().getEuro().getMoney();
					}
					if(transfernumber>actualnumber){      //check if the customer have enough money
						reminder("You do not have enough money!");
					}else{
						Currency curren=new Currency(currency,transfernumber);
						Currency transcurren=new Currency(currency,transfernumber-5);
						String recieverID=textTransfer.getText();
						Date date=new Date();
						String senderID=getCustomer().getChecking().getAccountNumber();	
						if(recieverID.equals("")){
							reminder("Please input reciever's account number!");
						}else{
							if(!PINnumber.equals(getCustomer().getChecking().getMoneypassword())){      //check PIN number
								reminder("Wrong PIN number!");
							}else{
								boolean ifright=false;
								AccountDao conn=new AccountDao();
								CheckandSave cs=conn.select(recieverID);
								//CheckandSave cs1=conn.select(senderID);
								if(!recieverID.equals(senderID)&&cs!=null){
									ifright=true;
									Transaction transaction=new Transaction(transcurren,date,senderID,recieverID,createTransID());
									TransactionDao con=new TransactionDao();
									con.insert(transaction);
								}
								/*for(int i=0;i<customers.size();i++){
									if(customers.get(i).getChecking().getAccountNumber().equals(recieverID)		
										&&!customers.get(i).getChecking().getAccountNumber().equals(senderID)){   //find receiver
										Transaction transaction=new Transaction(transcurren,date,getCustomer().getUser(),customers.get(i).getUser(),senderID,recieverID);
										getCustomer().addTransaction(transaction);
										customers.get(i).addTransaction(transaction);   //add customer's transaction
										transactions.add(transaction);		//add into whole transaction
										getCustomer().getChecking().getBalance().substract(curren);   //set receiver's balance
										customers.get(i).getChecking().getBalance().add(curren);	//set sender's balance
										
										ifright=true;
										reminder("Transaction successfully!");
										//getIncomes().add(new Income(new Currency("Dollar",5),"Transaction"));    //add bank's incomes
										dispose();
										break;
									}
								}*/
								if(!ifright){
									reminder("Wrong account number!Please input againe!");
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
	
	public String createTransID(){     //create new checking account number
		 Random rand=new Random();
	     String newAccount="";
	     for(int a=0;a<10;a++){
	    	 newAccount+=rand.nextInt(10);
	     }
	     AccountDao con=new AccountDao();
	     CheckandSave cs=con.select(newAccount);
	     while(cs!=null){
	    	 Random rands=new Random();
   	     String newAccounts="";
   	     for(int a=0;a<10;a++){
   	    	 newAccounts+=rand.nextInt(10);
   	     }
   	     newAccount=newAccounts;
	     }
	     return newAccount;
	}
	
}
