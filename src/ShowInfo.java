

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowInfo extends JFrame {

	private JPanel contentPane;
	private JLabel lblName;
	private JLabel lblYourCheckingAccount;
	private JLabel lblYourSavingAccount;
	
	JButton btnBackToLogin;
	private JLabel lblYourUsernameIs;
	private JLabel lblYourPhoneIs;

	/**
	 * Create the frame.
	 */
	public ShowInfo(String name,String username,String checkingID,String savingID,String phone) {
		setResizable(false);    
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		init(name,username,checkingID,savingID,phone);
		backtoLogin();
	}
	
	public void init(String name,String username,String checkingID,String savingID,String phone){
		lblName = new JLabel("Welcome!"+name+"!");
		lblName.setBounds(54, 23, 99, 15);
		contentPane.add(lblName);
		
		lblYourCheckingAccount = new JLabel("Your checking account number is "+checkingID+".");
		lblYourCheckingAccount.setBounds(54, 132, 297, 15);
		contentPane.add(lblYourCheckingAccount);
		
		lblYourSavingAccount = new JLabel("Your saving account number is "+savingID+".");
		lblYourSavingAccount.setBounds(54, 166, 325, 15);
		contentPane.add(lblYourSavingAccount);
		
		btnBackToLogin = new JButton("Back to login");
		btnBackToLogin.setBounds(50, 204, 127, 23);
		contentPane.add(btnBackToLogin);
		
		lblYourUsernameIs = new JLabel("Your username is "+username+".");
		lblYourUsernameIs.setBounds(54, 96, 297, 15);
		contentPane.add(lblYourUsernameIs);
		
		lblYourPhoneIs = new JLabel("Your phone is "+phone+".");
		lblYourPhoneIs.setBounds(54, 57, 262, 15);
		contentPane.add(lblYourPhoneIs);
		
		if(checkingID==null){
			lblYourCheckingAccount.setVisible(false);
		}
		if(savingID==null){
			lblYourSavingAccount.setVisible(false);
		}
		
	}
	
	public void backtoLogin(){
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
