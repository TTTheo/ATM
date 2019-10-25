/*
 * One of the many ButtonListener classes, handles the banker's request of removing a customer
 * from the bank. Show all the customers to the banker and records bankers choice
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;


public class manageButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	
	manageButtonListener (CoreInfo coreInfo, String messageToShow) {
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
	}
	
	public void actionPerformed(ActionEvent e) {
		String[] customerList = new String[coreInfo.getCustomerList().size()];
		int index = 0;
		for (CustomerInfo customer : coreInfo.getCustomerList()) {
			customerList[index] = customer.getCustomerID();
			index++;
		}
		coreInfo.getMessageArea().setText(messageToShow);
		coreInfo.getMessageArea().setBounds(100, 50, 200, 50);
		coreInfo.getFrame().add(coreInfo.getMessageArea());
		coreInfo.getPanel().removeAll();
		coreInfo.getPanel().setLayout(new GridLayout(2, 2, 20, 10));
		JLabel labelOne = new JLabel("Which Customer?");
		coreInfo.getPanel().add(labelOne);
		final JComboBox selectBox = new JComboBox(customerList);
		coreInfo.getPanel().add(selectBox);
		JButton deleteButton = new JButton("Delete");
		coreInfo.getPanel().add(deleteButton);
		deleteButtonListener dAction = new deleteButtonListener(this.coreInfo);
		deleteButton.addActionListener(dAction);
		JButton exitButton = new JButton("I Need My Customer");
		coreInfo.getPanel().add(exitButton);
		exitButtonListener eAction = new exitButtonListener(this.coreInfo);
		exitButton.addActionListener(eAction);
	}

}
