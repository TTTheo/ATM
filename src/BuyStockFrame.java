import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.List;

import javax.swing.JLabel;

public class BuyStockFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JButton btnBuy;
	private JButton btnCancel;
	private Customer customer;
	//private Conn con;
	private PreparedStatement sql;
	private ResultSet res;
	private JLabel lblNumberOfStocks;
	private JTextField textField_1;
	private JTextArea textArea;
	private JLabel lblCompany;
	private JTextField textField_2;
	private StockDao conn=new StockDao();
	private Tool tool=new Tool();
	/**
	 * Create the frame.
	 */
	public BuyStockFrame(Customer customer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customer=customer;
		//this.con=con;
		init();
		addAction();
	}
	
	public void init(){
		textField = new JTextField();
		textField.setBounds(41, 22, 241, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(297, 21, 93, 23);
		contentPane.add(btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 64, 349, 67);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnBuy = new JButton("Buy");
		btnBuy.setBounds(41, 209, 93, 23);
		contentPane.add(btnBuy);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(297, 209, 93, 23);
		contentPane.add(btnCancel);
		
		lblNumberOfStocks = new JLabel("Number of stocks you buy: ");
		lblNumberOfStocks.setBounds(41, 176, 156, 15);
		contentPane.add(lblNumberOfStocks);
		
		textField_1 = new JTextField();
		textField_1.setBounds(207, 173, 183, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblCompany = new JLabel("Company:");
		lblCompany.setBounds(41, 151, 54, 15);
		contentPane.add(lblCompany);
		
		textField_2 = new JTextField();
		textField_2.setBounds(110, 145, 280, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
	}
	
	public void addAction(){
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=textField.getText();
				Stock stock=conn.select(company);
				if(stock==null){
					tool.reminder("No such stock!");
				}else{
					textArea.setText("Company: "+stock.getCompany()+"\nPrice: "+stock.getPrice()+" Dollars");
				}
			}
		});
		
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=textField_2.getText();
				String num=textField_1.getText();
				if(company.equals("")){
					tool.reminder("The company can not be empty!");
				}else if(num.equals("")){
					tool.reminder("The price can not be empty!");
				}else if(!Tool.isNumeric(num)){
					tool.reminder("The price can not be negtive!");
				}else{
					Stock stock=conn.select(company);
					if(stock==null){
						tool.reminder("No such stock!");
					}else{
						CustomerStock custock=new CustomerStock(stock.getCompany(),stock.getPrice(),Integer.parseInt(num));
						//getCustomer().getInvest().addStock(custock);
						//add stock to customer
						AccountDao con=new AccountDao();
						double checking=getCustomer().getChecking().getBalance().getDollar().getMoney();
						double stockmoney=custock.getPrice()*custock.getNumofStock()+5;
						if(checking<stockmoney){
							tool.reminder("You do not have enough money!");
						}else{
							Stock selctstock=conn.select(stock.getCompany());
							if(selctstock==null){
								tool.reminder("No such stock!");
							}else{
								//add stock to customer
								getCustomer().getInvest().addStock(custock);
								CustomerStockDao conn=new CustomerStockDao();
								conn.insert(getCustomer().getUsername(),custock);
								//substract balance
								getCustomer().getChecking().getBalance().substract(new Currency("Dollar",stockmoney));
								con.update(getCustomer().getChecking());
								
								IncomeDao incomedao=new IncomeDao();
								incomedao.insert(new Income(new Currency("Dollar",5),"Buy Stock"));
								tool.reminder("Buy successfully!");
							}
						}
					}
				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
}
