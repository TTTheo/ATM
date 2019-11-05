import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ManageStockFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblStockMarket;
	private JButton btnAddStock;
	private JButton btnDeleteStock;
	private JButton btnUpdatePrice;
	private JButton btnBack;
	private StockDao conn=new StockDao();
	//private Tool reminder=new Tool();

	/**
	 * Create the frame.
	 */
	public ManageStockFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//this.con=con;
		init();
		addAction();
		setMarket();
	}
	
	public void init(){
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 41, 316, 199);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblStockMarket = new JLabel("Stock Market");
		lblStockMarket.setBounds(39, 16, 105, 15);
		contentPane.add(lblStockMarket);
		
		btnAddStock = new JButton("Add stock");
		btnAddStock.setBounds(365, 41, 105, 23);
		contentPane.add(btnAddStock);
		
		btnDeleteStock = new JButton("Delete stock");
		btnDeleteStock.setBounds(365, 100, 105, 23);
		contentPane.add(btnDeleteStock);
		
		btnUpdatePrice = new JButton("Update price");
		btnUpdatePrice.setBounds(365, 160, 105, 23);
		contentPane.add(btnUpdatePrice);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(365, 217, 105, 23);
		contentPane.add(btnBack);
	}
	
	public void setMarket(){
		List<Stock> stocks = new ArrayList<>() ;
		stocks=conn.selectAll();
		Object[][] content=new Object[stocks.size()][2];
		for(int i=0;i<stocks.size();i++){
			content[i][0]=stocks.get(i).getCompany();
			content[i][1]=stocks.get(i).getPrice();
		}
		Object[] title={"company","price"};
		
		table = new JTable(content, title);
		scrollPane.setViewportView(table);
	}
	
	public void addAction(){
		btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStockFrame addstock=new AddStockFrame();
				addstock.setVisible(true);
			}
		});
		
		btnDeleteStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteStockFrame deletestock=new DeleteStockFrame();
				deletestock.setVisible(true);
			}
		});
		
		btnUpdatePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//UpdatePriceFrame updateprice=new UpdatePriceFrame();
				//updateprice.setVisible(true);
				Random rd=new Random();
				
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
