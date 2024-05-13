package programs.mainframe;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.Main;

import java.awt.*;
import java.io.IOException;

public class MainFrame {
    private static final Logger logger = LogManager.getLogger(MainFrame.class);
    public static JFrame frame = new JFrame("Math Assistant");
    public static void create() {
    	try {
    	    // Basic JFrame settings
            Main mainInstance = new Main();

            ImageIcon logo = new ImageIcon(mainInstance.getClass().getResource("/assets/icon.png"));

            // New JPanel
            JPanel backgroundPanel = new JPanel();

            // Basic JFrame Settings
 
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500,800);
            frame.setLocationRelativeTo(null);
            frame.setIconImage(logo.getImage());

        
            frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(45,45,45));
            frame.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.white);


            
            backgroundPanel.setLayout(null);
    		
            
            frame.setContentPane(backgroundPanel);
            frame.setVisible(true);
            
            
            MainFrameScrollPane.create(backgroundPanel);  
            MainFrameButtons.create(backgroundPanel);
            

    	    logger.info("MainFrame loaded successfully");
    	} catch(HeadlessException ex) {
    		logger.error("Failed to load MainFrame!");
    	    ex.printStackTrace(); 
    	}
    }
    
}

