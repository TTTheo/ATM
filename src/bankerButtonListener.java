/*
 * One of the many ButtonListener classes, handles the reconstruction of the panel inside the frame
 * as well as passing the information stored in coreInfo to the next the panel 
 */

import java.awt.event.*;

public class bankerButtonListener implements ActionListener{
	private String messageToShow;
	private CoreInfo coreInfo;
	
	bankerButtonListener (CoreInfo coreInfo, String messageToShow) {
		this.messageToShow = messageToShow;
		this.coreInfo = coreInfo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MyPanels mp = new MyPanels();
		mp.setBankerMenuPanel(this.coreInfo);
	}

}
