import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;


public class AddStockFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblCompany;
	private JLabel lblPrice;;
	private JButton btnAdd;
	private JButton btnCancel;
	//private Conn con;
	private PreparedStatement sql;
	private ResultSet res;
	private Tool reminder=new Tool();

	/**
	 * Create the frame.
	 */
	public AddStockFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//this.con=con;
		init();
		addAction();
	}
	
	public void init(){
		textField = new JTextField();
		textField.setBounds(156, 54, 195, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblCompany = new JLabel("Company:");
		lblCompany.setBounds(82, 57, 75, 15);
		contentPane.add(lblCompany);
		
		lblPrice = new JLabel("Price:");
		lblPrice.setBounds(82, 112, 54, 15);
		contentPane.add(lblPrice);
		
		textField_1 = new JTextField();
		textField_1.setBounds(156, 109, 195, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(82, 171, 93, 23);
		contentPane.add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(258, 171, 93, 23);
		contentPane.add(btnCancel);
	}
	
	public void addAction(){
		btnAdd.addActionListener(new ActionListener() {
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
					Stock stock=new Stock(company,Double.parseDouble(pricestr));
					/*try{
							
							String query = "INSERT INTO Stock(`company`, `price`) VALUES (\"" + stock.getCompany() + "\", "+  stock.getPrice() + ")" ;
							Statement st = con.getCon().createStatement();
							st.executeUpdate(query);
							reminder.reminder("Add successfully!");
						
					}catch(SQLException ex) {
						System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
					}*/
					
					StockDao conn=new StockDao();
					Stock selectstock=conn.select(company);
					if(selectstock!=null){
						reminder.reminder("The stock have already existed!");
					}else{
						conn.insert(stock);
						reminder.reminder("Add successfully!");
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
	
	public static boolean isNumeric(String str){  //check if the string composed with numbers
		for (int i = str.length();--i>=0;){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	/*public void reminder(String str){
		Object[] okObjects = new Object[] {"OK"};
		JOptionPane.showOptionDialog(null, str, "Message", 
				JOptionPane.OK_OPTION,JOptionPane.WARNING_MESSAGE,null,okObjects,null);
	}*/
}
