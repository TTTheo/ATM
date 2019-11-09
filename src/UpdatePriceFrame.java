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


public class UpdatePriceFrame extends JFrame{
	
	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JButton btnUpdate;
	private JButton btnCancel;
	private JLabel lblPrice;
	private JTextField textField;
	private JTextArea textArea;
	private StockDao conn=new StockDao();
	private Tool tool=new Tool();
	
	public UpdatePriceFrame() {
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
		//The place where the names of all stocks can be found
		comboBox = new JComboBox();
		comboBox.setBounds(42, 31, 244, 21);
		contentPane.add(comboBox);
		addComboBox();
		
		//Press "Search" Button to query the price of chosen stock 
		btnSearch = new JButton("Search");
		btnSearch.setBounds(298, 30, 93, 23);
		contentPane.add(btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 73, 353, 61);
		contentPane.add(scrollPane);
		
		//The place where presents the price of chosen stock
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		//Press "Update Price" Button to change the stock price in database
		btnUpdate = new JButton("Update Price");
		btnUpdate.setBounds(42, 214, 120, 23);
		contentPane.add(btnUpdate);
		
		lblPrice = new JLabel("New Price: ");
		lblPrice.setBounds(41, 152, 70, 15);
		contentPane.add(lblPrice);
		
		//The place where captures the banker's input for the new stock price
		textField = new JTextField();
		textField.setBounds(104, 149, 287, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(298, 214, 93, 23);
		contentPane.add(btnCancel);
		
//		lblNubmerOfStocks = new JLabel("Nubmer of stocks you sell: ");
//		lblNubmerOfStocks.setBounds(42, 189, 166, 15);
//		contentPane.add(lblNubmerOfStocks);
//		
//		textField_1 = new JTextField();
//		textField_1.setBounds(220, 186, 171, 21);
//		contentPane.add(textField_1);
//		textField_1.setColumns(10);
	}
	
	public void addComboBox(){
		List<Stock> stocks = new ArrayList<>() ;
		stocks=conn.selectAll();	
		for(int i=0;i<stocks.size();i++){
			comboBox.addItem(stocks.get(i).getCompany());
		}
	}
	
	public void addAction(){
		//Search Action that returns the names of all stocks
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company=(String)comboBox.getSelectedItem();
				Stock stock=conn.select(company);
				textArea.setText("Company: "+company+"\nPrice: "+stock.getPrice()+" Dollars");
			}
		});
		
		//Update Action that captures the new price and updates the price in database
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String company = (String)comboBox.getSelectedItem();
				String price = textField.getText();
				if (!tool.isNumeric(price)) {
					tool.reminder("Invalid input for a stock price!!");
				} else {
					if (conn.update(new Stock(company, Double.parseDouble(price)))) {
						tool.reminder("Price Updated!!");
						dispose();
					} else {
						tool.reminder("An Error has occurred!!");
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