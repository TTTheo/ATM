

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class LoanFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblEnterTheLoan;
	private JComboBox comboBox;
	private JButton btnSubmit;
	private JButton btnCancle;
	private Customer customer=new Customer("","","","");
	private JComboBox comboBox_1;
	private JLabel lblChooseLength;
	private JLabel lblMonths;
	private JLabel lbltheInterestOf;
	private JLabel lblCollateral;
	private JTextField textField_1;
	private Tool tool=new Tool();
	//private ArrayList<Loan> loans=new ArrayList<Loan>();
	//private ArrayList<Income> incomes=new ArrayList<Income>();
	/**
	 * Create the frame.
	 */
	//public LoanFrame(Customer customer,ArrayList<Loan> loans,ArrayList<Income> incomes) {
	public LoanFrame(Customer customer) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customer=customer;
		//this.loans=loans;
		//this.incomes=incomes;
		init();
		addAction();
	}
	
	public void init(){
		lblEnterTheLoan = new JLabel("Enter the loan:");
		lblEnterTheLoan.setBounds(63, 24, 117, 15);
		contentPane.add(lblEnterTheLoan);
		
		String[] currency={"Dollar","RMB","Euro"};
		comboBox = new JComboBox(currency);
		comboBox.setBounds(63, 49, 86, 21);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(159, 49, 175, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(63, 213, 93, 23);
		contentPane.add(btnSubmit);
		
		btnCancle = new JButton("Cancle");
		btnCancle.setBounds(253, 213, 93, 23);
		contentPane.add(btnCancle);
		
		String[] loanlength={"1","3","6","9","12"};
		comboBox_1 = new JComboBox(loanlength);
		comboBox_1.setBounds(159, 80, 111, 21);
		contentPane.add(comboBox_1);
		
		lblChooseLength = new JLabel("Choose length:");
		lblChooseLength.setBounds(63, 83, 101, 15);
		contentPane.add(lblChooseLength);
		
		lblMonths = new JLabel("months");
		lblMonths.setBounds(280, 83, 54, 15);
		contentPane.add(lblMonths);
		
		lbltheInterestOf = new JLabel("(The interest of the loan is 1%.)");
		lbltheInterestOf.setBounds(56, 111, 278, 15);
		contentPane.add(lbltheInterestOf);
		
		lblCollateral = new JLabel(" Collateral: ");
		lblCollateral.setBounds(63, 154, 86, 15);
		contentPane.add(lblCollateral);
		
		textField_1 = new JTextField();
		textField_1.setBounds(159, 151, 175, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	/*public ArrayList<Loan> getLoans(){
		return this.loans;
	}*/
	

	
	public void addAction(){
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currency=(String)comboBox.getSelectedItem();
				String loanAmount=textField.getText();
				if(loanAmount.equals("")||!tool.isNumeric(loanAmount)){    //check the entering numbers
					tool.reminder("Please input right number!");
				}else{
					double loannumber=Double.parseDouble(loanAmount);
					Currency curren=new Currency(currency,loannumber);
					int loanlength=Integer.parseInt((String)comboBox_1.getSelectedItem());
					float interest=(float) 0.1;
					String collateral=textField_1.getText();
					if(collateral.equals("")){       					 //check the collateral
						tool.reminder("You are not allowed to loan if you do not have a collateral!");
					}else{
						Loan loan=new Loan(curren,interest,loanlength,collateral);
						getCustomer().addLoan(loan);  	//add loans to customer's whole loan
						LoanDao loanDao = new LoanDao() ;
						loanDao.insert(getCustomer().getUsername(), loan) ;
						//loans.add(loan);  
						//incomes.add(new Income(new Currency(currency,loannumber*0.1),"Loan"));//add loans to whole loan
						IncomeDao incomedao=new IncomeDao();
						incomedao.insert(new Income(new Currency(currency,loannumber*0.1),"Loan"));
						tool.reminder("Loan successfully!");
						dispose();
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
