/*
 * The root of the application, initialize all the classes that will be stored inside
 * coreInfo
 */
import javax.swing.*;
import java.util.ArrayList;

public class MainFrame extends JFrame{
	String theLog = "";
	JTextArea messageArea = new JTextArea("Welcome to MyBank");
	private ArrayList<CustomerInfo> customerList = new ArrayList<CustomerInfo>();
	
	MainFrame (){
		JPanel thePanel = new JPanel();
		MyPanels mp = new MyPanels();
		CoreInfo coreInfo = new CoreInfo(this, messageArea, thePanel, customerList, theLog);
		mp.setMainMenuPanel(coreInfo);
	}
	
	public static void main( String[] args ) {
		// Create a new frame
		JFrame frame = new MainFrame();	

		// Initialize frame information
		frame.setTitle( "MyBank Application");
		frame.setSize( 400, 300 );
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		// Turn it on
		frame.setVisible( true );
    } 
}
