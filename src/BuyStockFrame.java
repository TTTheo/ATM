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
	private Tool reminder=new Tool();
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
					reminder.reminder("No such stock!");
				}else{
					textArea.setText("Company: "+stock.getCompany()+"\nPrice: "+stock.getPrice()+" Dollars");
				}
				/*try{
					sql=con.getCon().prepareStatement("SELECT * FROM Stock where company=?");
					sql.setString(1, company);
					res=sql.executeQuery();
					if(res==null){
						reminder("No such stock!");
					}else{
						double price=res.getDouble("price");
						textArea.setText("Company: "+company+"\nPrice: "+price+" Dollars");
					}
					
				}catch(Exception ex){
					ex.printStackTrace();
				}*/
			}
		});
		
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=textField_2.getText();
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
						CustomerStock custock=new CustomerStock(stock.getCompany(),stock.getPrice(),Integer.parseInt(num));
						//getCustomer().getInvest().addStock(custock);
						//add stock to customer
					}
					/*try{
						String query = "SELECT * FROM Stock where company = \"" + company + "\"" ;
						Statement st = con.getCon().createStatement();
						ResultSet rs = st.executeQuery(query);
						if(rs==null){
							reminder("No such stock!");
						}else{
							//check if the customer have enough money 
							CustomerStock custock=new CustomerStock(company,rs.getDouble("price"),Integer.parseInt(num));
							//insert into customer's stock
						}
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
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	public static boolean isNumeric(String str){  //check if the string composed with numbers
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
}
