import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

import java.awt.ScrollPane;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class InvestFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JButton btnStocksMarket;
	private JButton btnBuyStock;
	private JButton btnSellStock;
	private JButton btnBack;
	private Customer customer;
	private JButton btnUpdateYourStocks;
	/**
	 * Create the frame.
	 */
	public InvestFrame(Customer customer) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customer=customer;
		init();
		addAction();
		setMarket();
	}
	
	public void init(){
		lblNewLabel = new JLabel("Your stocks:");
		lblNewLabel.setBounds(27, 10, 120, 15);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 35, 314, 215);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		btnStocksMarket = new JButton("Stocks Market");
		btnStocksMarket.setBounds(350, 83, 158, 23);
		contentPane.add(btnStocksMarket);
		
		btnBuyStock = new JButton("Buy stock");
		btnBuyStock.setBounds(350, 134, 158, 23);
		contentPane.add(btnBuyStock);
		
		btnSellStock = new JButton("Sell stock");
		btnSellStock.setBounds(350, 181, 158, 23);
		contentPane.add(btnSellStock);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(350, 227, 158, 23);
		contentPane.add(btnBack);
		
		btnUpdateYourStocks = new JButton("Update your stocks");
		btnUpdateYourStocks.setBounds(350, 36, 158, 23);
		contentPane.add(btnUpdateYourStocks);
	}
	
	public void addAction(){
		btnStocksMarket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockMarketFrame stockmarket=new StockMarketFrame();
				stockmarket.setVisible(true);
			}
		});
		
		btnBuyStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyStockFrame buystock=new BuyStockFrame(getCustomer());
				buystock.setVisible(true);
			}
		});
		
		btnSellStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellStockFrame sellstock=new SellStockFrame(getCustomer());
				sellstock.setVisible(true);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnUpdateYourStocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMarket();
			}
		});
	}
	
	public void setMarket(){
		//set customer stocks
		ArrayList<CustomerStock> stock=getCustomer().getInvest().getStock();
		String str="";
		if(stock!=null){
			for(int i=0;i<stock.size();i++){
				str+="Company:"+stock.get(i).getCompany()+"\nPrice:"+stock.get(i).getPrice()+" Dollars\nAmount:"+
						stock.get(i).getNumofStock()+"\n---------------------------------------------\n";
			}
			textArea.setText(str);
		}
	}
	
	public Customer getCustomer(){
		return this.customer;
	}
}
