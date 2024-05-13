package utils.math;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalculatorEngine {
 	/*
 	 * Precedence order:
 	 * 1. Parantheses
 	 * 2. Exponentiation
 	 * 3. Square Root
 	 * 4. Multiplication and division
 	 * 5. Addition and subtraction
 	 * 
 	 * Definitions:
 	 * Infix: Normally written expression
 	 * Postfix: Operators are placed after their operands. 2+3 for example becomes 23+
 	 * Prefix: Operators are placed before their operands. 2+3 becomes +23
 	 * 
 	 * Postfix and prefix notation are easier to evaluate algorithmically because there are no parantheses or precedence rules
 	 */
	 
    private static final Map<Character, Integer> precedenceMap = new HashMap<>();
    static {
        precedenceMap.put('^', 3);
        precedenceMap.put('*', 2);
        precedenceMap.put('/', 2);
        precedenceMap.put('+', 1);
        precedenceMap.put('-', 1);
    }
	
	
	private static final Logger logger = LogManager.getLogger(CalculatorEngine.class); 
	    
	// custom engine. Converts infix notation to postfix with a stackbased algorithm (NOT DONE)
	public static float calculateExpression(String ex) {
       ex = ex.replace("÷", "/").replace("×", "*").replace(",", ".");

       ex = ex.replaceAll("(\\d)([\\(])", "$1*$2");
       ex = ex.replaceAll("([\\)])(\\d)", "$1*$2");     
       ex = ex.replaceAll("(\\))(\\()", "$1*$2");        

       String[] operatorStack;
       StringBuilder postfix = new StringBuilder();
       
       for(int i = 0; i < ex.length(); i++) {
    	   char c = ex.charAt(i);
    	   
    	   if(isOperand(c)) {
    		   postfix.append(c);
    	   } else {

    	   }
       }       
	}
	
	
	private static boolean isOperand(char ch) {
	    return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z' || ch >= '0' && ch <= '9');
	}
	
	
	// Calculates the final expression inside the Textfield with the Nashorn JS Engine
	public static float calculateExpressionNashorn(String text) {
        text = text.replace("÷", "/").replace("×", "*").replace(",", ".");
        
        // handles implicit multiplication when there is no * (i have no idea how it works but it does)
        text = text.replaceAll("(\\d)([\\(])", "$1*$2");
        text = text.replaceAll("([\\)])(\\d)", "$1*$2");     
        text = text.replaceAll("(\\))(\\()", "$1*$2");        
        try {
        	ScriptEngineManager manager = new ScriptEngineManager();
        	ScriptEngine engine = manager.getEngineByName("nashorn");
        	Object result = engine.eval(text);
        	if(result instanceof Number) {
        		return ((Number) result).floatValue();
        	} else {
            logger.error("Failed to calculate solution! Invalid Expression.");
            return Float.NaN;
        	}
        } catch(ScriptException e) {
        	logger.error("Failed to calculate solution! Invalid Expression.");
            return Float.NaN;
        }
	}
}
