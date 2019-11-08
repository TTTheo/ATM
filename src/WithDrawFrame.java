

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JPasswordField;

public class WithDrawFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel;	
	private JComboBox comboBox;	
	private JButton btnSubmit;	
	private JButton btnCancle;
	private JRadioButton rdbtnChecking;	
	private JRadioButton rdbtnSaving;
	private ButtonGroup btngroup;
	private Customer customer=new Customer("","","","");
	//private ArrayList<Income> incomes=new ArrayList<Income>();
	private JLabel lblEnterYourPin;
	private JPasswordField passwordField;
	private AccountDao con=new AccountDao();
	private Tool tool=new Tool();

	/**
	 * Create the frame.
	 */
	//public WithDrawFrame(Customer customer,ArrayList<Income> incomes) {
	public WithDrawFrame(Customer customer) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customer=customer;
		//this.incomes=incomes;
		init();
		addAction();
	}
	
	public void init(){
		lblNewLabel = new JLabel("Enter the withdraw:");
		lblNewLabel.setBounds(59, 36, 149, 20);
		contentPane.add(lblNewLabel);
		
		String[] currency={"Dollar","RMB","Euro"};
		comboBox = new JComboBox(currency);
		comboBox.setBounds(59, 62, 73, 21);
		contentPane.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(142, 62, 204, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(53, 187, 93, 23);
		contentPane.add(btnSubmit);
		
		btnCancle = new JButton("Cancle");
		btnCancle.setBounds(254, 187, 93, 23);
		contentPane.add(btnCancle);
		
		rdbtnChecking = new JRadioButton("Checking");
		rdbtnChecking.setBounds(59, 89, 121, 23);
		contentPane.add(rdbtnChecking);
		
		rdbtnSaving = new JRadioButton("Saving");
		rdbtnSaving.setBounds(227, 89, 121, 23);
		contentPane.add(rdbtnSaving);
		
		btngroup=new ButtonGroup();
		btngroup.add(rdbtnChecking);
		btngroup.add(rdbtnSaving);
		
		lblEnterYourPin = new JLabel("Enter your PIN:");
		lblEnterYourPin.setBounds(59, 121, 121, 15);
		contentPane.add(lblEnterYourPin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(59, 146, 287, 21);
		contentPane.add(passwordField);
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	//public ArrayList<Income> getIncomes(){
		//return this.incomes;
	//}
	
	public void addAction(){
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String currency=(String)comboBox.getSelectedItem();
				String withdraw=textField.getText();
				String PINnumber=String.valueOf(passwordField.getPassword());
				if(withdraw.equals("")||!tool.isNumeric(withdraw)){     //check withdraw numbers
					tool.reminder("Please input the right withdraw!");
				}else{
					if(Double.parseDouble(withdraw)<=0){
						tool.reminder("Please input positive number!");
					}else{
						if(!rdbtnChecking.isSelected()&&!rdbtnSaving.isSelected()){    //check if there is one account be chosen
							tool.reminder("Please choose one account!");
						}else{
							if(!PINnumber.equals(getCustomer().getChecking().getMoneypassword())){     //check PIN number
								tool.reminder("Wrong PIN number!");
							}else{
								double actualnumber=0;
								if(currency.equals("Dollar")){
									actualnumber=getCustomer().getChecking().getBalance().getDollar().getMoney();
								}else if(currency.equals("RMB")){
									actualnumber=getCustomer().getChecking().getBalance().getRMB().getMoney();
								}else if(currency.equals("Euro")){
									actualnumber=getCustomer().getChecking().getBalance().getEuro().getMoney();
								}
						
								if(rdbtnChecking.isSelected()){    //withdraw in checking
									double withdrawnumber=Double.parseDouble(withdraw)+5;							
									if(withdrawnumber<=actualnumber){		//check if customer have enough money
										Currency curren=new Currency(currency,withdrawnumber);
										getCustomer().getChecking().getBalance().substract(curren);
										con.update(getCustomer().getChecking());
										//getIncomes().add(new Income(new Currency("Dollar",5),"Withdraw"));
										IncomeDao incomedao=new IncomeDao();
										incomedao.insert(new Income(new Currency("Dollar",5),"Withdraw"));
										tool.reminder("Withdraw successfully!");
										dispose();
											
									}else{
										tool.reminder("You do not have enough money!");
									}							
								}
								
								if(rdbtnSaving.isSelected()){		//withdraw in saving
									if(getCustomer().getSaving()!=null){
										double actualnumbersave=0;
										if(currency.equals("Dollar")){
											actualnumbersave=getCustomer().getSaving().getBalance().getDollar().getMoney();
										}else if(currency.equals("RMB")){
											actualnumbersave=getCustomer().getSaving().getBalance().getRMB().getMoney();
										}else if(currency.equals("Euro")){
											actualnumbersave=getCustomer().getSaving().getBalance().getEuro().getMoney();
										}
										double withdrawnumber=Double.parseDouble(withdraw)+5;								
										if(withdrawnumber<actualnumbersave){		//check if customer have enough money
											Currency curren=new Currency(currency,withdrawnumber);
											getCustomer().getSaving().getBalance().substract(curren);
											con.update(getCustomer().getSaving());
											//getIncomes().add(new Income(new Currency("Dollar",5),"Withdraw"));
											IncomeDao incomedao=new IncomeDao();
											incomedao.insert(new Income(new Currency("Dollar",5),"Withdraw"));
											tool.reminder("Withdraw successfully!");
											dispose();									
										}else{
											tool.reminder("You do not have enough money!");
										}							
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
