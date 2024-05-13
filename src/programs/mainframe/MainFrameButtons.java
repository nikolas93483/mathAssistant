package programs.mainframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import utils.Utils;

public class MainFrameButtons {

	public static void create(JPanel panel) {  
		JButton launchButton = new JButton("Launch Program");
		launchButton.setBounds(225, 50, 200, 60);
		launchButton.putClientProperty("JButton.buttonType", "roundRect");
		
		// Listener to execute the program
        launchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            		Utils.launchProgram();
            }
        });
		
        
		panel.add(launchButton);
	}
	

}