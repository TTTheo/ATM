

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.SwingConstants;

public class OpenAccount extends JFrame {

	private JPanel contentPane;
	private JTextField textname;
	private JTextField textusername;
	private JTextField textpassword;
	private JTextField textPIN;
	private JLabel lblName;
	private JLabel lblPassword;	
	private JLabel lblUsername;	
	private JCheckBox checkchecking;
	private JCheckBox checksaving;
	private JLabel lblChooseTheAccount;	
	private JButton btnCreate;	
	private JLabel lblpasswordShouldBe;	
	private JLabel lblPinNumber;	
	private JLabel lblThePinOf;
	//private ArrayList<Income> incomes=new ArrayList<Income>();
	//private ArrayList<Customer> customers=new ArrayList<Customer>();
	private JLabel lblYouMustDeposit;
	private Customer newCustomer;
	private JButton btnBack;
	private JTextField textField;
	private JLabel lblAmount;
	private JLabel lblcheckingAccount;
	private JLabel lblPhone;
	private JTextField textField_1;
	private JLabel lbldigitsNumbers;
	private AccountDao conaccount=new AccountDao();
	private IncomeDao incomedao=new IncomeDao();
	private Tool tool=new Tool();
	
	/**
	 * Create the frame.
	 */
	public OpenAccount() {
		//customers=new Customers(custo.getCustomers());
		//this.customers=customers;
		//this.incomes=incomes;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 515, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addAction();
	}
	
	public void init(){
		lblName = new JLabel("Name:");
		lblName.setBounds(41, 32, 73, 15);
		contentPane.add(lblName);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(41, 110, 73, 15);
		contentPane.add(lblPassword);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setBounds(41, 85, 73, 15);
		contentPane.add(lblUsername);
		
		checkchecking = new JCheckBox("Checking");
		checkchecking.setEnabled(false);
		checkchecking.setSelected(true);
		checkchecking.setBounds(60, 182, 103, 23);
		contentPane.add(checkchecking);
		
		checksaving = new JCheckBox("Saving");
		checksaving.setBounds(214, 182, 103, 23);
		contentPane.add(checksaving);
		
		textname = new JTextField();
		textname.setBounds(127, 29, 202, 21);
		contentPane.add(textname);
		textname.setColumns(10);
		
		textusername = new JTextField();
		textusername.setBounds(127, 82, 202, 21);
		contentPane.add(textusername);
		textusername.setColumns(10);
		
		textpassword = new JTextField();
		textpassword.setBounds(127, 107, 202, 21);
		contentPane.add(textpassword);
		textpassword.setColumns(10);
		
		lblChooseTheAccount = new JLabel("Choose the account you want to open:");
		lblChooseTheAccount.setBounds(41, 161, 276, 15);
		contentPane.add(lblChooseTheAccount);
		
		btnCreate = new JButton("Create");
		btnCreate.setBounds(41, 356, 93, 23);
		contentPane.add(btnCreate);
		
		lblpasswordShouldBe = new JLabel("(Password should be no more than 10 characters.)");
		lblpasswordShouldBe.setBounds(41, 136, 307, 15);
		contentPane.add(lblpasswordShouldBe);
		
		lblPinNumber = new JLabel("PIN number:");
		lblPinNumber.setBounds(41, 215, 92, 15);
		contentPane.add(lblPinNumber);
		
		textPIN = new JTextField();
		textPIN.setBounds(127, 211, 202, 21);
		contentPane.add(textPIN);
		textPIN.setColumns(10);
		
		lblThePinOf = new JLabel("(The PIN of cheking and saving are same. It must be 6-digit numbers.)");
		lblThePinOf.setBounds(33, 240, 423, 33);
		contentPane.add(lblThePinOf);
		
		lblYouMustDeposit = new JLabel("You must deposit at least 5 dollars for each account into checking account.");
		lblYouMustDeposit.setBounds(33, 278, 456, 15);
		contentPane.add(lblYouMustDeposit);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(363, 356, 93, 23);
		contentPane.add(btnBack);
		
		textField = new JTextField();
		textField.setBounds(127, 312, 202, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(41, 315, 73, 15);
		contentPane.add(lblAmount);
		
		lblcheckingAccount = new JLabel("(checking account)");
		lblcheckingAccount.setBounds(339, 315, 132, 15);
		contentPane.add(lblcheckingAccount);
		
		lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(41, 57, 54, 15);
		contentPane.add(lblPhone);
		
		textField_1 = new JTextField();
		textField_1.setBounds(127, 54, 202, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lbldigitsNumbers = new JLabel("(10-digits numbers)");
		lbldigitsNumbers.setBounds(351, 57, 120, 15);
		contentPane.add(lbldigitsNumbers);
		
	}
	
	public void addAction(){
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String accountID=getNewAccount();
				String name=textname.getText();
				if(name.equals("")){    //check name
					tool.reminder("Please fill the name!");
				}else{
					String phone=textField_1.getText();     
					if(phone.equals("")||!tool.isNumeric(phone)||phone.length()!=10){     //check phone
						tool.reminder("Please fill the right phone!");
					}else{
						String username=textusername.getText();
						if(username.equals("")||username.length()>10){           //check username
							tool.reminder("Please fill the right username!");
						}else{
							boolean ifright=true;
							UserDao conn=new UserDao();
							Customer user=conn.select(username);
							if(user!=null){
								tool.reminder("The username exists!");
								ifright=false;
							}else{
								ifright=true;
							}
							/*for(int i=0;i<customers.size();i++){
								if(customers.get(i).getUser().getUsername().equals(username)){  //check if username exist
									reminder("The username exists!");
									ifright=false;
									break;
								}
							}*/
							if(ifright){
								String password=textpassword.getText();
								if(password.equals("")){     //check password
									tool.reminder("Please fill the password!");
								}else{
									if(password.length()>10){    //check password's length
										tool.reminder("The password should be mo more than 10 characters!");
									}else{
										String PIN=textPIN.getText();
										if(PIN.equals("")){   //check PIN number
											tool.reminder("Please fill the PIN number!");
										}else{
											if(PIN.length()!=6){     //check PIN number's length
												tool.reminder("The PIN number should be 6 numbers!");
											}else{
												if(!tool.isNumeric(PIN)){		//check PIN number
													tool.reminder("The PIN number should be numbers!");
												}else{
													//deposit
													String amountstr=textField.getText();
													double payment=0;
													boolean checking=checkchecking.isSelected();
													boolean saving=checksaving.isSelected();
													String checkingID=null;
													String savingID=null;
													if(amountstr.equals("")||!tool.isNumeric(amountstr)){    //check deposit
														tool.reminder("Please enter the right deposit!");
													}else{
														if(checking){        
															payment+=5;	
														}
														if(saving){			
															payment+=5;
														}
														double amount=Double.parseDouble(amountstr);
														if(amount<payment){    //check deposit
															tool.reminder("Your checking deposit is not enough!");
														}else{
															//newCustomer=new Customer(name,username, password,phone);
															newCustomer=new Customer(name,username, password,phone);
															UserDao con=new UserDao();
															con.insert(newCustomer);
															if(checking){         //create new checking account
																checkingID=getNewCheckingAccount();
																
																newCustomer.createChecking(new Checking(checkingID,PIN,new Balance(new Currency("Dollar",amount-payment),
																		new Currency("RMB",0),new Currency("Euro",0))));
																
																conaccount.insert(newCustomer.getChecking(),newCustomer.getUsername());
																//getIncomes().add(new Income(new Currency("Dollar",5),"Open Accounts"));
																
																incomedao.insert(new Income(new Currency("Dollar",5),"Open Account"));
																conaccount.update(newCustomer.getChecking());
															}
															if(saving){			//create new saving account
																savingID=getNewSavingAccount();
																//getIncomes().add(new Income(new Currency("Dollar",5),"Open Accounts"));//add bank's income
																newCustomer.createSaving(new Saving(savingID,PIN,new Balance()));	
																conaccount.insert(newCustomer.getSaving(),newCustomer.getUsername());
																incomedao.insert(new Income(new Currency("Dollar",5),"Open Account"));
																//conaccount.update(newCustomer.getSaving());
																//getIncomes().add(new Income(new Currency("Dollar",5),"Open Accounts"));
															}
															//conaccount.update(newCustomer.getChecking());
															//newCustomer.getChecking().getBalance().add(new Currency("Dollar",amount));   //set balance
															//newCustomer.getChecking().getBalance().substract(new Currency("Dollar",payment));  //pay the charge
															//customers.add(newCustomer);   //add customer
															tool.reminder("Open account successfully!");
															ShowInfo showinfo=new ShowInfo(name,username,checkingID,savingID,phone);  //show information
															showinfo.setVisible(true);
																
															dispose();
														}
													}
												}										
											}
										}
									}
								}
							}					
						}
					}
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public void setCustomer(Customer newCustomer){
		this.newCustomer=newCustomer;
	}
	
	/*public ArrayList<Customer> getCusto(){
		return this.customers;
	}*/
	
	//public ArrayList<Income> getIncomes(){
		//return this.incomes;
	//}
	
	public String getNewCheckingAccount(){     //create new checking account number
		 Random rand=new Random();
	     String newAccount="";
	     for(int a=0;a<12;a++){
	    	 newAccount+=rand.nextInt(10);
	     }
	     AccountDao con=new AccountDao();
	     CheckandSave cs=con.select(newAccount);
	     while(cs!=null){
	    	 Random rands=new Random();
    	     String newAccounts="";
    	     for(int a=0;a<12;a++){
    	    	 newAccounts+=rand.nextInt(10);
    	     }
    	     newAccount=newAccounts;
	     }
	     /*for(int i=0;i<customers.size();i++){  // check if the account number exist
	    	 while(newAccount.equals(customers.get(i).getChecking().getAccountNumber())){
	    		 Random rands=new Random();
	    	     String newAccounts="";
	    	     for(int a=0;a<12;a++){
	    	    	 newAccounts+=rand.nextInt(10);
	    	     }
	    	     newAccount=newAccounts;
	    	 }
	     }	*/
	     return newAccount;
	}
	
	public String getNewSavingAccount(){     //create new saving account number
		 Random rand=new Random();
	     String newAccount="";
	     for(int a=0;a<12;a++){
	    	 newAccount+=rand.nextInt(10);
	     }
	     AccountDao con=new AccountDao();
	     CheckandSave cs=con.select(newAccount);
	     while(cs!=null){
	    	 Random rands=new Random();
    	     String newAccounts="";
    	     for(int a=0;a<12;a++){
    	    	 newAccounts+=rand.nextInt(10);
    	     }
    	     newAccount=newAccounts;
	     }
	     /*for(int i=0;i<customers.size();i++){   // check if the account number exist
	    	 if(customers.get(i).getSaving()!=null){
		    	 while(newAccount.equals(customers.get(i).getSaving().getAccountNumber())){	
		    		 Random rands=new Random();
			    	 String newAccounts="";
			    	 for(int a=0;a<12;a++){
			    	     newAccounts+=rand.nextInt(10);
			    	 }
			    	 newAccount=newAccounts;
		    	 }
	    	 }	
	     }	*/	
	     return newAccount;
	}

}
