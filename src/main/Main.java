package main;

import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import programs.mainframe.MainFrame;
import utils.ConfigManager;
import utils.Utils;
import utils.math.numbers.ComplexNumber;

public class Main {
	/*
	 * External libraries used:
	 * - Log4j
	 * - Flatlaf 
	 * - Nashorn JS
	 */
	
	 private static final Logger logger = LogManager.getLogger(Main.class);
	    
	    public static void main(String[] args) {    	
	            logger.traceEntry();
	            logger.info("Program started!");
	            ConfigManager.initializeConfigProperties();
	            ConfigManager.setUIfromConfig();
	            Utils.printFancyStartMessage();	            
	            if(ConfigManager.isDebugMode) { Utils.openConsole(); }
	            
	            
	            // Load the Mainframe on the Event Dispatch Thread
	            SwingUtilities.invokeLater(() -> { MainFrame.create(); });	            
	            registerShutdownHook();	            	          	          
	    }
	    
	    
	    private static void registerShutdownHook() {
	        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
	            logger.info("Program stopped! Saving files...");
	            
	            try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            
	            // Clears the Console
	            for (int i = 0; i < 100; i++) {
	                System.out.println();
	            }
	        }));
	    }
}
