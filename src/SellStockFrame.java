import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;


public class SellStockFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JButton btnSell;
	private JButton btnCancel;
	private Customer customer;
	private JLabel lblCompany;
	private JTextField textField;
	private JLabel lblNubmerOfStocks;
	private JTextField textField_1;
	private JTextArea textArea;
	private StockDao conn=new StockDao();
	private Tool tool=new Tool();
	/**
	 * Create the frame.
	 */
	public SellStockFrame(Customer customer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customer=customer;
		init();
		addAction();
	}
	
	public void init(){
		comboBox = new JComboBox();
		comboBox.setBounds(42, 31, 244, 21);
		contentPane.add(comboBox);
		addComboBox();
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(298, 30, 93, 23);
		contentPane.add(btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 73, 353, 61);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		btnSell = new JButton("Sell");
		btnSell.setBounds(42, 214, 93, 23);
		contentPane.add(btnSell);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(298, 214, 93, 23);
		contentPane.add(btnCancel);
		
		lblCompany = new JLabel("Company: ");
		lblCompany.setBounds(41, 152, 75, 15);
		contentPane.add(lblCompany);
		
		textField = new JTextField();
		textField.setBounds(126, 149, 265, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNubmerOfStocks = new JLabel("Nubmer of stocks you sell: ");
		lblNubmerOfStocks.setBounds(42, 189, 166, 15);
		contentPane.add(lblNubmerOfStocks);
		
		textField_1 = new JTextField();
		textField_1.setBounds(220, 186, 171, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
	
	public void addComboBox(){
		List<Stock> stocks = new ArrayList<>() ;
		stocks=conn.selectAll();
		
		for(int i=0;i<stocks.size();i++){
			comboBox.addItem(stocks.get(i).getCompany());
		}
	}
	
	public void addAction(){
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=(String)comboBox.getSelectedItem();
				Stock stock=conn.select(company);
				textArea.setText("Company: "+company+"\nPrice: "+stock.getPrice()+" Dollars");
			}
		});
		
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=textField.getText();
				String num=textField_1.getText();
				//check if all filling information are in correct form
				if(company.equals("")){
					tool.reminder("The company can not be empty!");
				}else if(num.equals("")){
					tool.reminder("The price can not be empty!");
				}else if(!tool.isNumeric(num)){
					tool.reminder("The price can not be negtive!");
				}else{
					Stock stock=conn.select(company);
					if(stock==null){
						tool.reminder("No such stock!");
					}else{
						
						int amount=Integer.parseInt(num);
						CustomerStock custock=new CustomerStock(company, stock.getPrice(),amount);
						double stockmoney=custock.getPrice()*custock.getNumofStock()-5;
						CustomerStockDao con=new CustomerStockDao();
						//check if the customer have such stock
						if(con.select(getCustomer().getUsername(), company).getNumofStock()<amount){
							tool.reminder("You do not have enough stocks!");
						}else{
							//delete stock and reset balance
							getCustomer().getInvest().deleteStock(custock);
							CustomerStock companystock=con.select(getCustomer().getUsername(),company);
							System.out.println((con.select(getCustomer().getUsername(), company).getNumofStock()));
							//delete stock if customer has 0 such stock, else update the amount
							if(companystock.getNumofStock()-custock.getNumofStock()==0){
								con.delete(getCustomer().getUsername(),company);
							}else{
								CustomerStock newcustock=new CustomerStock(custock.getCompany(),custock.getPrice(),companystock.getNumofStock()-custock.getNumofStock());
								con.update(getCustomer().getUsername(),newcustock);
							}
							//update balance
							getCustomer().getChecking().getBalance().add(new Currency("Dollar",stockmoney));
							AccountDao conn=new AccountDao();
							conn.update(getCustomer().getChecking());
							IncomeDao incomedao=new IncomeDao();
							incomedao.insert(new Income(new Currency("Dollar",5),"Sell Stock"));
							tool.reminder("Sell successfully!");
							dispose();
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
