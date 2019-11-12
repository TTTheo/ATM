

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

public class CustomerFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnBalance;	
	private JButton btnShowTranscation;	
	private JButton btnDeposit;	
	private JButton btnWithdraw;	
	private JButton btnLoan;
	private Customer customer=new Customer("","","","");	
	private JButton btnCloseAccount;
	private JButton btnLogOut;
	private JButton btnOpenSaving;
	private JButton btnInvestment;
	//private Conn con;
	private Tool tool=new Tool();
	
	/**
	 * Create the frame.
	 */
	public CustomerFrame(Customer customer) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customer=customer;
		init();
		addAction();
	}
	
	public void setCustomer(Customer customer){
		this.customer=customer;
	}
	public Customer getCustomer(){
		return this.customer;
	}
	
	public void init(){
		btnBalance = new JButton("Balance");
		btnBalance.setBounds(62, 57, 129, 29);
		contentPane.add(btnBalance);
		
		btnShowTranscation = new JButton("Transcation");
		btnShowTranscation.setBounds(62, 120, 129, 29);
		contentPane.add(btnShowTranscation);
		
		btnDeposit = new JButton("Deposit");

		btnDeposit.setBounds(291, 57, 131, 29);
		contentPane.add(btnDeposit);
		
		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.setBounds(291, 120, 131, 29);
		contentPane.add(btnWithdraw);
		
		btnLoan = new JButton("Loan");
		btnLoan.setBounds(62, 183, 129, 29);
		contentPane.add(btnLoan);
		
		btnCloseAccount = new JButton("Close Account");
		btnCloseAccount.setBounds(292, 183, 130, 29);
		contentPane.add(btnCloseAccount);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(393, 10, 93, 23);
		contentPane.add(btnLogOut);
		
		btnOpenSaving = new JButton("Open Saving");
		btnOpenSaving.setBounds(62, 250, 129, 29);
		contentPane.add(btnOpenSaving);
		
		btnInvestment = new JButton("Investment");
		btnInvestment.setBounds(291, 250, 131, 29);
		contentPane.add(btnInvestment);
		
	}
	
	public void addAction(){
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepositFrame depositframe=new DepositFrame(getCustomer());
				depositframe.setVisible(true);
				setCustomer(depositframe.getCustomer());
			}
		});
		
		btnBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BalanceFrame balanceframe=new BalanceFrame(getCustomer());
				balanceframe.setCustomer(getCustomer());
				balanceframe.setVisible(true);
			}
		});
		
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WithDrawFrame withdrawframe=new WithDrawFrame(getCustomer());
				withdrawframe.setVisible(true);
				setCustomer(withdrawframe.getCustomer());
			}
		});
		
		btnLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoanFrame loanframe=new LoanFrame(getCustomer());
				loanframe.setVisible(true);
				setCustomer(loanframe.getCustomer());
			}
		});
		
		btnShowTranscation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionFrame transactionframe=new TransactionFrame(getCustomer());
				transactionframe.setVisible(true);
				setCustomer(transactionframe.getCustomer());
			}
		});
		
		btnCloseAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseAccountFrame closeaccountframe=new CloseAccountFrame(getCustomer());
				closeaccountframe.setVisible(true);
				setCustomer(closeaccountframe.getCustomer());
			}
		});
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login=new Login();
				login.setVisible(true);
			}
		});
		
		btnOpenSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check if the customer have saving account
				AccountDao con=new AccountDao();
				con.select(getCustomer().getUsername());
				if(customer.getSaving()==null){		
					//check if the customer have enough money to close the account
					if(customer.getChecking().getBalance().getDollar().getMoney()>=5){
						customer.createSaving(new Saving(getNewSavingAccount(),customer.getChecking().getMoneypassword(),new Balance()));
						con.insert(customer.getSaving(),customer.getUsername());
						customer.getChecking().getBalance().substract(new Currency("Dollar",5));
						con.update(customer.getChecking());
						//incomes.add(new Income(new Currency("Dollar",5),"Open account"));
						IncomeDao incomedao=new IncomeDao();
						incomedao.insert(new Income(new Currency("Dollar",5),"Open Account"));
						tool.reminder("Open saving account successfully!");
					}else{
						tool.reminder("You do not have enough money to open!");
					}
				}else{
					tool.reminder("You already have saving account!");
				}
			}
		});
		
		btnInvestment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getCustomer().getInvest()!=null){
					InvestFrame invest=new InvestFrame(getCustomer());
					invest.setVisible(true);
				}else{
					tool.reminder("You do not have investment account!");
				}
			}
		});
		
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
	     return newAccount;
	}
}
