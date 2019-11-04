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
	//private Conn con;
	private JLabel lblCompany;
	private JTextField textField;
	private JLabel lblNubmerOfStocks;
	private JTextField textField_1;
	private JTextArea textArea;
	private StockDao conn=new StockDao();
	private Tool reminder=new Tool();
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
		//this.con=con;
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
		scrollPane.setViewportView(textArea);
		
		btnSell = new JButton("Sell");
		btnSell.setBounds(42, 214, 93, 23);
		contentPane.add(btnSell);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(298, 214, 93, 23);
		contentPane.add(btnCancel);
		
		lblCompany = new JLabel("Company: ");
		lblCompany.setBounds(41, 152, 54, 15);
		contentPane.add(lblCompany);
		
		textField = new JTextField();
		textField.setBounds(104, 149, 287, 21);
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
		/*try {
			String query = "SELECT * FROM Stock" ;
			Statement st = con.getCon().createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				String company = rs.getString("company") ;
				double price = rs.getDouble("price") ;
				stocks.add(new Stock(company,price)) ;
			}
		}catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		}*/
		
		for(int i=0;i<stocks.size();i++){
			comboBox.addItem(stocks.get(i).getCompany());
		}
	}
	
	public void addAction(){
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=(String)comboBox.getSelectedItem();
				Stock stock=conn.select(company);
				/*try {
					String query = "SELECT * FROM Stock where company = \"" + company + "\"" ;
					Statement st = con.getCon().createStatement();
					ResultSet rs = st.executeQuery(query);
					Stock stock;
					while(rs.next()) {
						stock = new Stock(rs.getString("company"),rs.getDouble("price")) ;
					}
					double price=rs.getDouble("price");
					textArea.setText("Company: "+company+"\nPrice: "+price+" Dollars");
				}catch(SQLException ex) {
					System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
				}*/
				textArea.setText("Company: "+company+"\nPrice: "+stock.getPrice()+" Dollars");
			}
		});
		
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=textField.getText();
				String num=textField_1.getText();
				if(company.equals("")){
					reminder.reminder("The company can not be empty!");
				}else if(num.equals("")){
					reminder.reminder("The price can not be empty!");
				}else if(!isNumeric(num)){
					reminder.reminder("The price can not be negtive!");
				}else{
					Stock stock=conn.select(company);
					if(stock==null){
						reminder.reminder("No such stock!");
					}else{
						//delete stock and reset balance
					}
					/*try{
					String query = "SELECT * FROM Stock where company = \"" + company + "\"" ;
					Statement st = con.getCon().createStatement();
					ResultSet rs = st.executeQuery(query);
					//delete customer's stock and caculate 
					}catch(SQLException ex) {
						System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
					}*/
				}
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	public static boolean isNumeric(String str){  //check if the string composed with numbers
		for (int i = str.length();--i>=0;){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
}
