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
	private PreparedStatement sql;
	private ResultSet res;
	private Tool tool=new Tool();

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
					tool.reminder("The company can not be empty!");
				}else if(pricestr.equals("")){
					tool.reminder("The price can not be empty!");
				}else if(!Tool.isNumeric(pricestr)){
					tool.reminder("The price can not be negtive!");
				}else{
					Stock stock=new Stock(company,Double.parseDouble(pricestr));					
					StockDao conn=new StockDao();
					Stock selectstock=conn.select(company);
					if(selectstock!=null){
						tool.reminder("The stock have already existed!");
					}else{
						conn.insert(stock);
						tool.reminder("Add successfully!");
						dispose();
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
