package programs.calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.Main;
import utils.Utils;

public class CalculatorFrame {
    private static final Logger logger = LogManager.getLogger(CalculatorFrame.class);
    public static JFrame frame = new JFrame("Calculator");
	public static void create() {
		if(Utils.isProgramOpen(frame) == false) {
			try {
				logger.info("Launching calculator...");

		        
		        Main mainInstance = new Main();
		        ImageIcon logo = new ImageIcon(mainInstance.getClass().getResource("/assets/calculator.png"));
		        
		        
		        JPanel backgroundPanel = new JPanel();
		         
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.setSize(350,400);
		        frame.setLocationRelativeTo(null);
		        frame.setIconImage(logo.getImage());

		    
		        frame.getRootPane().putClientProperty("JRootPane.titleBarBackground", new Color(45,45,45));
		        frame.getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.white);
				
		        
				CalculatorButtons.create(backgroundPanel);
				
		        frame.setContentPane(backgroundPanel);
		        frame.setVisible(true);		     
		    	} catch(Exception ex) {
		    		logger.error("Failed to load calculator!");
		    	    ex.printStackTrace(); 
		    	}
		} else {
			logger.error("Calculator program is already open!");
		}

	}
}

