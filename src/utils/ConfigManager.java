package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class ConfigManager {
    private static final Logger logger = LogManager.getLogger(ConfigManager.class);
	public static Properties prop = new Properties();
	
    private static File configFile = new File("config.properties");
    
    public static boolean isDebugMode;
    public static boolean isFancyMessage;
    public static String uiTheme;
    public static String calcEngine;
    
    
    // If the config file is empty or doesn't exist, it generates one
    public static void initializeConfigProperties() {
        try {
            if (!configFile.exists()) {
                configFile.createNewFile();
                setDefaultConfigProperties();
                logger.info("Creating configuration file...");
            }
            prop.load(new FileInputStream(configFile));
            isDebugMode = Boolean.parseBoolean(prop.getProperty("debug_mode"));
            isFancyMessage = Boolean.parseBoolean(prop.getProperty("fancy_start_message"));
            uiTheme = prop.getProperty("ui_theme");
            calcEngine = prop.getProperty("calculator_engine");
            logger.info("Loading Config...");
        } catch (IOException e) {
            logger.error("Failed to load the configuration file!");
            System.exit(1);
            e.printStackTrace();
        }
    }
    
    
    // Sets and creates the default config values
    public static void setDefaultConfigProperties() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(configFile)))) {
            writer.println("# Sets the UI Theme for the entire Program");
            writer.println("ui_theme=dark");
            writer.println();
            
            writer.println("# Displays a fancy Startup message on launch");
            writer.println("fancy_start_message=true");
            writer.println();
            
            writer.println("# Turn on Debug mode");
            writer.println("debug_mode=false");
            writer.println();
            
            writer.println("# Tell the program what engine it should use for calculations (nashorn, experimental)");
            writer.println("calculation_engine=nashorn");
            writer.flush();
            logger.info("Default config saved successfully");
        } catch (IOException ex) {
            logger.error("Failed to write the default configuration file!");
            System.exit(1);
            ex.printStackTrace();
        }
    }
    
    // Sets the value for a given Property
    public static void setProperty(String key, String value) {
    	try {
    		prop.setProperty(key, value);
    		prop.store(new FileOutputStream(configFile), null);
            logger.info("Property '" + key + "' set to '" + value + "'");    		
    	} catch (IOException e) {
            logger.error("Failed to set property '" + key + "' to '" + value + "'");
    	}
    }
    
    
    // Sets the UI for the whole program from config
    public static void setUIfromConfig() {
    	String uiTheme = ConfigManager.prop.getProperty("ui_theme");
    	
    	try {
        	switch(uiTheme) {
        	case "dark":
                UIManager.setLookAndFeel(new FlatDarkLaf());
                break;
        	case "light":	
        		UIManager.setLookAndFeel(new FlatLightLaf());
        		break;
        	default:
        		//unrecognized UI theme
        		logger.warn("Unknown UI theme: " + uiTheme + ". Using default.");
                UIManager.setLookAndFeel(new FlatDarkLaf());
        		break;
        	}
    	} catch(UnsupportedLookAndFeelException e) {
            logger.error("Failed to load Look and Feel!");
            e.printStackTrace();
    	}
    }
}
