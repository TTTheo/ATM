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
	private Tool tool=new Tool();

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
				StockDao stockcon=new StockDao();
				Stock stock=stockcon.select(company);
				if(stock==null){
					tool.reminder("The stock do not exist!");
				}else{
					//check if any customer have the stock
					CustomerStockDao con=new CustomerStockDao();
					ArrayList<CustomerStock> custock=con.selectByCompany(company);
					if(custock.size()!=0){
						tool.reminder("You can not delete it because someone owe it!");
					}else{
						//delete the stock
						StockDao conn=new StockDao();
						conn.delete(company);
						tool.reminder("Delete successfully!");
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
