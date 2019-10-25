/*
 * One of the many ButtonListener classes, handles the transition back to the Banker Menu panel
 */
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;

public class exitButtonListener implements ActionListener{
	private CoreInfo coreInfo;

	exitButtonListener(CoreInfo coreInfo) {
		this.coreInfo = coreInfo;
	}
	
	public void actionPerformed(ActionEvent e) {	
		MyPanels mp = new MyPanels();
		mp.setBankerMenuPanel(this.coreInfo);	
	}
}
