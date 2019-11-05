

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollBar;

import java.awt.ScrollPane;

public class CheckupFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblEnterName;	
	private JButton btnCheck;	
	private JButton btnBack;	
	private JComboBox comboBox;
	private JScrollPane scrollPane;
	//private Customer customer=new Customer("","","");	
	//private ArrayList<Customer> customers=new ArrayList<Customer>();
	private JTextArea textArea;
	private AccountDao con=new AccountDao();
	/**
	 * Create the frame.
	 */
	public CheckupFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 523, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//this.customer=customer;
		//this.customers=customers;
		init();
		addAction();
	}
	
	public void init(){

		lblEnterName = new JLabel("Enter name");
		lblEnterName.setBounds(41, 34, 79, 15);
		contentPane.add(lblEnterName);
		
		btnCheck = new JButton("Check");
		btnCheck.setBounds(376, 30, 80, 23);
		contentPane.add(btnCheck);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(363, 282, 93, 23);
		contentPane.add(btnBack);
		
		String[] customercheck={"All"};
		comboBox = new JComboBox(customercheck);
		comboBox.setBounds(130, 31, 236, 21);
		contentPane.add(comboBox);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 67, 415, 205);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		/*for(int i=0;i<customers.size();i++){
			comboBox.addItem(customers.get(i).getUser().getName());
		}*/
		setcomboBox();
	}
	
	public void setcomboBox(){
		UserDao con=new UserDao();
		List<User> users=con.selectAll();
		if(users!=null){
			for(int i=0;i<users.size();i++){
				comboBox.addItem(users.get(i).getName());
			}
		}
	}
	
	
	public void addAction(){
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String choosename=(String)comboBox.getSelectedItem();
				String infoall="";
				if(choosename.equals("All")){   //all customers' information will show
					List<Customer> customers=con.selectAllCustomer();
					for(int i=0;i<customers.size();i++){
						infoall+=customers.get(i).showCustomer()+"------------------------\r\nLoan:\r\n"
								+customers.get(i).showLoan()+"\r\n";						
					}
					textArea.setText(infoall);
				}else{
					Customer customer=con.selectCustomer(choosename);			//select a specifc customer
					/*for(int i=0;i<customers.size();i++){  //specific customer's information will show
						if(choosename.equals(customers.get(i).getUser().getName())){
							infoall=customers.get(i).showCustomer()+"------------------------\r\nLoan:\r\n"+customers.get(i).showLoan();
							textArea.setText(infoall);
							break;
						}
					}*/
					infoall=customer.showCustomer()+"------------------------\r\nLoan:\r\n"+customer.showLoan();
					textArea.setText(infoall);
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
