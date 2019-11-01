

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.SwingConstants;

public class PaymentFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblYourPayment;
	private JScrollPane scrollPane;	
	private JTextArea textArea;	
	private JButton btnBack;	
	private JLabel lblTotalPayment;
	private ArrayList<Customer> customers=new ArrayList<Customer>();
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public PaymentFrame(ArrayList<Customer> customers) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 337);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.customers=customers;
		init();
		addAction();
		showPayment();
	}
	
	public void init(){
		lblYourPayment = new JLabel("Your payment:");
		lblYourPayment.setBounds(47, 24, 138, 15);
		contentPane.add(lblYourPayment);
		
		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(46, 49, 350, 165);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(303, 265, 93, 23);
		contentPane.add(btnBack);
		
		lblTotalPayment = new JLabel("Total payment:");
		lblTotalPayment.setBounds(47, 232, 99, 15);
		contentPane.add(lblTotalPayment);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBounds(141, 232, 70, 56);
		contentPane.add(lblNewLabel);
	}
	
	public void showPayment(){   //show the payment in label
		String[] showpay=new String[customers.size()];
		for(int i=0;i<customers.size();i++){
			showpay[i]="";
		}
		String show="";
		String showtotal="";
		double dollaramount=0;
		double rmbamount=0;
		double euroamount=0;
		boolean ifgo=false;
		for(int i=0;i<customers.size();i++){
			boolean ifright=false;
			if(customers.get(i).getSaving()!=null){   //check customer have saving account
				if(customers.get(i).getSaving().getBalance().getDollar().getMoney()>50){
					showpay[i]+="Dollar:"+customers.get(i).getSaving().getBalance().getDollar().getMoney()*0.05+" ";
					dollaramount+=customers.get(i).getSaving().getBalance().getDollar().getMoney()*0.05;
					ifright=true;
					ifgo=true;
				}
				if(customers.get(i).getSaving().getBalance().getRMB().getMoney()>50){
					showpay[i]+="RMB:"+customers.get(i).getSaving().getBalance().getRMB().getMoney()*0.05+" ";
					rmbamount+=customers.get(i).getSaving().getBalance().getRMB().getMoney()*0.05;
					ifright=true;
					ifgo=true;
				}
				if(customers.get(i).getSaving().getBalance().getEuro().getMoney()>50){
					showpay[i]+="Euro:"+customers.get(i).getSaving().getBalance().getEuro().getMoney()*0.05+" ";
					euroamount+=customers.get(i).getSaving().getBalance().getEuro().getMoney()*0.05;
					ifright=true;
					ifgo=true;
				}
				if(ifright){
					showpay[i]=customers.get(i).showCustomerSave()+"Payment: "+showpay[i];
				}
				if(!showpay[i].equals("")){
				show+=showpay[i];
				}
			}
		}
		showtotal="<html>Dollar:"+dollaramount+"\r\nRMB:"+rmbamount+"\r\nEuro:"+euroamount+"</html>";   //set total payment
		lblNewLabel.setText(showtotal);
		if(!ifgo){
			textArea.setText("No payment.");
		}else{
			textArea.setText(show);
		}
	}
	
	
	public void addAction(){
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
