/*
 * The class that creating pop-up error message window for the application
 * takes in the error message to show for its constructor. All the resources are 
 * released after user click the Retry button
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorWindow {
	private String errorMessage;
	
	ErrorWindow(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public void show() {
		JFrame errorFrame = new JFrame();		
		// Initialize frame information
		errorFrame.setTitle("Error Message");
		errorFrame.setSize( 300, 150 );
		errorFrame.setLocationRelativeTo(null);
		errorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		// Turn it on
		errorFrame.setVisible( true );
		// Error Message in JLabel
		JLabel errorMessage = new JLabel(this.errorMessage);
		errorMessage.setBounds(25, 25, 50, 50);
		errorFrame.add(errorMessage);
		// Close Button to exit the window
		JButton exitButton = new JButton("Retry");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorFrame.setVisible( false );
				errorFrame.dispose();
			}
		});
		errorFrame.add(exitButton);
		errorFrame.setLayout(new FlowLayout());
	}
}
