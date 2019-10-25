/*
 * The class that holds the important information the ensures the functionality of the application:
 * (1) The reference to the only instances of JFrame and JPanel in this application, for the various phases
 * 	of GUI when using the application are all about changing contents inside the same panel/frame;
 * (2) The reference to the only instance of an ArrayList of CustomerInfo class, where all customer related
 * 	information is stored, such as customer's bank accounts, their deposits and their loans;
 * (3) An empty String that functions as the log of all the things happened in the bank since the application
 * 	has started, used for generating report for the banker to review
 */
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CoreInfo {
	private JFrame theFrame;
	private JTextArea messageArea;
	private JPanel thePanel;
	private ArrayList<CustomerInfo> customerList;
	private String theLog;
	private String interestLog = "";
	
	CoreInfo (JFrame theFrame, JTextArea messageArea, JPanel thePanel, ArrayList<CustomerInfo> customerList, String theLog) {
		this.theFrame = theFrame;
		this.messageArea = messageArea;
		this.thePanel = thePanel;
		this.customerList = customerList;
		this.theLog = theLog;
	}
	
	/*
	 * Normal Setter and Getter methods to access and modify the fields in this class
	 */
	public JFrame getFrame() {
		return this.theFrame;
	}
	
	public JPanel getPanel() {
		return this.thePanel;
	}
	
	public JTextArea getMessageArea() {
		return this.messageArea;
	}
	
	public ArrayList<CustomerInfo> getCustomerList(){
		return this.customerList;
	}
	
	public String getLog() {
		return this.theLog;
	}

	public void addLog(String newLog) {
		this.theLog += newLog;
	}
	
	public String getInterestLog() {
		return this.interestLog;
	}
	
	public void addInterestLog(String newLog) {
		this.interestLog += newLog;
	}
}
