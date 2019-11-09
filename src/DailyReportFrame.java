
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class DailyReportFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblReport;	
	private JScrollPane scrollPane;	
	private JTextArea textArea;	
	private JButton btnBack;

	/**
	 * Create the frame.
	 */
	public DailyReportFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 378);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		init();
		addAction();
		setReport();
	}
	
	public void init(){
		lblReport = new JLabel("Report:");
		lblReport.setBounds(31, 10, 65, 15);
		contentPane.add(lblReport);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 35, 447, 258);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(385, 306, 93, 23);
		contentPane.add(btnBack);
	}
	
	public void setReport(){   //set daily report in textArea
		String coustomerinfo="No transactions";
		TransactionDao con=new TransactionDao();
		List<Transaction> trans=con.selectAll();
		if(trans!=null){
			for(int i=0;i<trans.size();i++){
				coustomerinfo="";
				coustomerinfo+="Transactions:\r\n"+trans.get(i).showTrans()+"\n";
			}
		}		
		textArea.setText(coustomerinfo);
	}
	
	public void addAction(){
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
