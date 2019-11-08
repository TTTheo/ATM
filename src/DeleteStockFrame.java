import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DeleteStockFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblCompany;
	private JButton btnDelete;
	private JButton btnCancel;
	//private Conn con;
	private Tool reminder=new Tool();

	/**
	 * Create the frame.
	 */
	public DeleteStockFrame() {
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
		lblCompany = new JLabel("Company:");
		lblCompany.setBounds(66, 75, 75, 15);
		contentPane.add(lblCompany);
		
		textField = new JTextField();
		textField.setBounds(151, 72, 198, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(66, 176, 93, 23);
		contentPane.add(btnDelete);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(256, 176, 93, 23);
		contentPane.add(btnCancel);
	}
	
	public void addAction(){
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=textField.getText();
				//ArrayList<Customer> customers=new ArrayList<Customer>();
				//customers=selectAll();
				//check if customer have the stock
				/*try {
					//need to check customer
					String query = "DELETE FROM Stock where company = \"" + company + "\"" ;
					Statement st = con.getCon().createStatement();
					st.executeUpdate(query);
					
				}catch(SQLException ex) {
					System.err.format("SQL State: %s\n%s", ex.getSQLState(), ex.getMessage());
				}*/
				StockDao stockcon=new StockDao();
				Stock stock=stockcon.select(company);
				if(stock==null){
					reminder.reminder("The stock do not exist!");
				}else{
					//check if any customer have the stock
					AccountStockDao con=new AccountStockDao();
					List<CustomerStock> custock=con.selectList(company);
					if(custock!=null){
						reminder.reminder("You can not delete it because someone owe it!");
					}else{
						StockDao conn=new StockDao();
						conn.delete(company);
						Tool reminder=new Tool();
						reminder.reminder("Delete successfully!");
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

}
