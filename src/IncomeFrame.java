
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class IncomeFrame extends JFrame {

	 private JPanel contentPane;
	 private JLabel lblIncome;
	 private Customer customer=new Customer("","","","");
	 private JLabel lblManagerYourTotal;	   
	 private JScrollPane scrollPane;	  
	 private JTextArea textArea;	  
	 private JButton btnBack;
	 private ArrayList<Income> incomes=new ArrayList<Income>();
	 private ArrayList<Customer> customers=new ArrayList<Customer>();
	 private JLabel lblNewLabel;
	 /**
	  * Create the frame.
	  */
	 public IncomeFrame(ArrayList<Customer> customers,ArrayList<Income> incomes) {
	 	setResizable(false);
	  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  setBounds(100, 100, 456, 335);
	  contentPane = new JPanel();
	  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	  setContentPane(contentPane);
	  contentPane.setLayout(null);
	  this.customers=customers;
	  this.incomes=incomes;
	  init();
	  addAction();
	 }
	 
	 public void init(){
		 lblManagerYourTotal = new JLabel("Manager, your income is as follows:");
		  lblManagerYourTotal.setBounds(47, 10, 214, 16);
		  contentPane.add(lblManagerYourTotal);
		  
		  lblIncome = new JLabel("Total income:");
		  lblIncome.setBounds(47, 234, 214, 16);
		  contentPane.add(lblIncome);
		  
		  scrollPane = new JScrollPane();
		  scrollPane.setEnabled(false);
		  scrollPane.setBounds(47, 33, 340, 191);
		  contentPane.add(scrollPane);
		  
		  textArea = new JTextArea();
		  textArea.setEditable(false);
		  scrollPane.setViewportView(textArea);
		  
		  btnBack = new JButton("Back");
		  btnBack.setBounds(294, 263, 93, 23);
		  contentPane.add(btnBack);
		  
		  lblNewLabel = new JLabel("New label");
		  lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		  lblNewLabel.setBounds(126, 233, 77, 55);
		  contentPane.add(lblNewLabel);
		  showIncome();
		  
	 }
	 
	 public void showIncome(){	 //show the income in the textArea and label 
		 String printIncome="";
		 double incomedollar=0;
		 double incomermb=0;
		 double incomeeuro=0;
		 
		 /*for(int i=0;i<customers.size();i++){  //add loans' income into the whole incomes
			 
			 for(int j=0;j<customers.get(i).getLoans().size();j++){
				 incomes.add(new Income(customers.get(i).getLoans().get(j).getLoanTotal(),"Loan"));
			 }
		 }*/
		 
		 for(int i=0;i<incomes.size();i++){    //show incomes and calculate the total incomes for three currency
			 printIncome+="-------------------------------\n"+incomes.get(i).showIncome();
			 if(incomes.get(i).getIncome().getMark().equals("Dollar")){
				 incomedollar+=incomes.get(i).getIncome().getMoney();
			 }else if(incomes.get(i).getIncome().getMark().equals("RMB")){
				 incomermb+=incomes.get(i).getIncome().getMoney();
			 }else if(incomes.get(i).getIncome().getMark().equals("Euro")){
				 incomeeuro+=incomes.get(i).getIncome().getMoney();
			 }
		 }

		 textArea.setText(printIncome);
		 lblNewLabel.setText("<html>Dollar:"+incomedollar+"<br>RMB:"+incomermb+"<br>Euro:"+incomeeuro);
	 }

	 public void setCustomer(Customer customer){
		  this.customer=customer;
	 }
		 
	 public Customer getCustomer(){
		 return this.customer;
	 }
		 
	 public ArrayList<Income> getIncomes(){
			return this.incomes;
	}
	 
	 public void addAction(){
		 btnBack.addActionListener(new ActionListener() {
			  	public void actionPerformed(ActionEvent e) {
			  		dispose();
			  	}
			  });
	 }
 
}