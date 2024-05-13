package utils;

import java.io.IOException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import main.ProgramList;
import programs.calculator.CalculatorFrame;
import programs.mainframe.MainFrameScrollPane;

public class Utils {
    private static final Logger logger = LogManager.getLogger(Utils.class);
    
    // This function launches the program
    public static void launchProgram() {
        ProgramList selectedProgram = MainFrameScrollPane.getSelectedProgramValue();
        
        if (selectedProgram != null) {
            switch (selectedProgram) {
                case CALCULATOR:
                    CalculatorFrame.create();
                    break;
                case GRAPH_EDITOR:
                	logger.info("Program does not exist yet!");
                    break;
                case NOTES:
                	logger.info("Program does not exist yet!");
                    break;
            }
        } else {
            logger.error("Please select a program first!");
        }
    }
    
    
    // Checks if the given program (JFrame) is running and returns a boolean
    public static boolean isProgramOpen(JFrame program) {
        return program != null && program.isVisible();
    }
    
    
    // Prints a fancy message in console when the program is launched
    public static void printFancyStartMessage() {
    	if(ConfigManager.isFancyMessage) {
    		   String programName = ConsoleColors.YELLOW_BOLD_BRIGHT 
    				+ 	"  __  __       _   _          _            _     _              _   "
    		   		+ "\n |  \\/  | __ _| |_| |__      / \\   ___ ___(_)___| |_ __ _ _ __ | |_ "
    		   		+ "\n | |\\/| |/ _` | __| '_ \\    / _ \\ / __/ __| / __| __/ _` | '_ \\| __|"
    		   		+ "\n | |  | | (_| | |_| | | |  / ___ \\\\__ \\__ \\ \\__ \\ || (_| | | | | |_ "
    		   		+ "\n |_|  |_|\\__,_|\\__|_| |_| /_/   \\_\\___/___/_|___/\\__\\__,_|_| |_|\\__|\n" + ConsoleColors.GREEN;
    		   
    	       System.out.println(ConsoleColors.GREEN + "*********************************************************************");
    	       System.out.println(programName);
    	       System.out.println(ConsoleColors.GREEN + "*********************************************************************" + ConsoleColors.RESET);                                                                      
    	}
    }
   
    
    public static void openConsole() {
        try {
            new ProcessBuilder("cmd", "/c", "start").start();
        } catch (IOException e) {
        	logger.error("Couldn't open console!");
            e.printStackTrace();
        }
    }
}