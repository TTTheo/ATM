import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;


public class UpdatePriceFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnCancel;
	private JLabel lblCompany;
	private JLabel lblNewPrice;
	private JButton btnUpdate;
	private JComboBox comboBox;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	//private Conn con;
	private StockDao conn=new StockDao();
	private Tool reminder=new Tool();

	/**
	 * Create the frame.
	 */
	public UpdatePriceFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//this.con=con;
		init();
		
	}
	
	public void init(){
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(296, 211, 93, 23);
		contentPane.add(btnCancel);
		
		lblCompany = new JLabel("Company:");
		lblCompany.setBounds(64, 124, 70, 15);
		contentPane.add(lblCompany);
		
		textField = new JTextField();
		textField.setBounds(144, 121, 245, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblNewPrice = new JLabel("New Price:");
		lblNewPrice.setBounds(64, 164, 80, 15);
		contentPane.add(lblNewPrice);
		
		textField_1 = new JTextField();
		textField_1.setBounds(144, 161, 243, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(60, 211, 93, 23);
		contentPane.add(btnUpdate);
		
		comboBox = new JComboBox();
		comboBox.setBounds(59, 24, 216, 21);
		contentPane.add(comboBox);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(296, 23, 93, 23);
		contentPane.add(btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 65, 328, 39);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	
	public void addAction(){
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=(String) comboBox.getSelectedItem();
				Stock stock=conn.select(company);
				textArea.setText("Company: "+stock.getCompany()+"\nPrice: "+stock.getPrice()+" Dollars");
				/*try {
					
					String query = "SELECT * FROM Stock where company = \"" + company + "\"" ;
					Statement st = con.getCon().createStatement();
					ResultSet rs = st.executeQuery(query);
					Stock stock=null;
					while(rs.next()) {
						stock = new Stock(rs.getString("company"),rs.getDouble("price")) ;
					}
					textArea.setText("Company: "+stock.getCompany()+"\nPrice: "+stock.getPrice()+" Dollars");
				}catch(SQLException ex) {
					System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
				}
				*/
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=textField.getText();
				String pricestr=textField_1.getText();
				if(company.equals("")){
					reminder.reminder("The company can not be empty!");
				}else if(pricestr.equals("")){
					reminder.reminder("The price can not be empty!");
				}else if(!isNumeric(pricestr)){
					reminder.reminder("The price can not be negtive!");
				}else{
					
					/*try {
						String query = "SELECT * FROM Stock where company = \"" + company + "\"" ;
						Statement st = con.getCon().createStatement();
						ResultSet rs = st.executeQuery(query);
						if(rs==null){
							reminder("No such stock!");
						}else{
							Stock stock=new Stock(company,Double.parseDouble(pricestr));
							String query1 = "UPDATE Stock SET price = " + stock.getPrice() +" where company = \"" + stock.getCompany() + "\"" ;
							Statement st1 = con.getCon().createStatement();
							st.executeUpdate(query);
						}*/
					Stock selectstock=conn.select(company);
					if(selectstock==null){				
						reminder.reminder("No such stock!");
					}else{
						Stock stock=new Stock(company,Double.parseDouble(pricestr));
						conn.update(stock);
						reminder.reminder("Update successfully!");
					}
						
					/*}catch(SQLException ex) {
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
