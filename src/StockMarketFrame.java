
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StockMarketFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnBack;
	private JLabel lblStocksMarket;
	private StockDao conn=new StockDao();
	/**
	 * Create the frame.
	 */
	public StockMarketFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addAction();
		setMarket();
	}
	
	public void init(){
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 40, 401, 193);
		contentPane.add(scrollPane);
		
		
		btnBack = new JButton("Back");
		btnBack.setBounds(316, 243, 93, 23);
		contentPane.add(btnBack);
		
		lblStocksMarket = new JLabel("Stocks Market");
		lblStocksMarket.setBounds(20, 15, 93, 15);
		contentPane.add(lblStocksMarket);
	}
	
	public void addAction(){
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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
	
}
