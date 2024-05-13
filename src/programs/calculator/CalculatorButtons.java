package programs.calculator;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.Utils;
import utils.math.CalculatorEngine;

public class CalculatorButtons {
    private static final Logger logger = LogManager.getLogger(CalculatorFrame.class);
    
    private static final String DIVIDE_SYMBOL = "\u00F7";
    private static final String MULTIPLY_SYMBOL = "×";
    private static final String BACK_SYMBOL = "↚";
    private static final String INFINITY_SYMBOL = "∞";
    
    private static final int textFieldSize = 20;
    
    private static final Font normalFont = new Font("Arial", Font.BOLD, 24);
    private static final Font unicodeFont = new Font("Lucida Sans Unicode", Font.BOLD, 32);
	
    
    // creates buttons for the calculator frame and logic (This function is then passed over to CalculatorFrame.java)
	public static void create(JPanel panel) {
        JTextField textField = new JTextField(textFieldSize);
		
		JButton clearButton = new JButton("C");
		JButton backButton = new JButton(BACK_SYMBOL);		
		JButton equalButton = new JButton("=");		
		JButton commaButton = new JButton(",");
		JButton paraLeftButton = new JButton("(");
		JButton paraRightButton = new JButton(")");
		
		JButton[] operatorButtons = {
		new JButton("+"),
		new JButton("-"),
		new JButton(DIVIDE_SYMBOL),
		new JButton(MULTIPLY_SYMBOL)
		};
		
		JButton[] numberButtons = new JButton[10];
		numberButtons[0] = new JButton("0");
		numberButtons[1] = new JButton("1");
		numberButtons[2] = new JButton("2");
		numberButtons[3] = new JButton("3");
		numberButtons[4] = new JButton("4");
		numberButtons[5] = new JButton("5");
		numberButtons[6] = new JButton("6");
		numberButtons[7] = new JButton("7");
		numberButtons[8] = new JButton("8");
		numberButtons[9] = new JButton("9");
		
        textField.setDocument(new CalculatorTextfieldRestriction());
		textField.setFont(new Font("Arial", Font.BOLD, 16));
        textField.setPreferredSize(new Dimension(300, 40));
        
		clearButton.setFont(normalFont);		
		backButton.setFont(unicodeFont);		
		equalButton.setFont(normalFont);		
		commaButton.setFont(normalFont);
		paraLeftButton.setFont(normalFont);
		paraRightButton.setFont(normalFont);

		setFont(operatorButtons, normalFont);
		setFont(numberButtons, normalFont);
			
        panel.add(textField, BorderLayout.NORTH);
        
        
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));        
        buttonPanel.add(clearButton);
        buttonPanel.add(backButton);
        buttonPanel.add(equalButton);
        buttonPanel.add(commaButton);
        buttonPanel.add(paraLeftButton);
        buttonPanel.add(paraRightButton);
        
        for (JButton numberButton : numberButtons) {
        	buttonPanel.add(numberButton);
        }
        
        for (JButton operatorButton : operatorButtons) {
        	buttonPanel.add(operatorButton);
        }
        
        
        panel.add(buttonPanel, BorderLayout.CENTER);
        
		addEnterKeyListener(textField);
        
		// Adds a Listener to each Button in the Panel
		Component[] components = buttonPanel.getComponents();
		for(Component component : components) {
			if(component instanceof JButton) {
				JButton button = (JButton) component;
				buttonListener(button, textField);
			}
		}
	}
	
	
	// Adds a listener to each button type
	public static void buttonListener(JButton button, JTextField textField) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
            	switch(button.getText()) {
            	case "=":
            		float result = CalculatorEngine.calculateExpressionNashorn(text);  		
            		if(Float.isInfinite(result)) {
            			textField.setText(INFINITY_SYMBOL);
            		} else {
                        String resultString = Float.toString(result);
                		textField.setText(resultString);
                		System.out.println("Result: " + resultString);
            		}
            		break;
            	case "C":
            		textField.setText("");
            		break;
            	case BACK_SYMBOL:
                    if (!text.isEmpty()) {
                        textField.setText(text.substring(0, text.length() - 1));
                    }
            		break;
            	case ",":
            		if(lastDigitIsNumber(textField) == true) {
                		textField.setText(textField.getText() + ",");
            		}
            		break;
            	case "+":
            		if(lastDigitIsNumber(textField) == true) {
                		textField.setText(textField.getText() + "+");
            		}
            		break;
            	case "-":
            		if(lastDigitIsNumber(textField) == true) {
                		textField.setText(textField.getText() + "-");
            		}
            		break;
            	case DIVIDE_SYMBOL:
            		if(lastDigitIsNumber(textField) == true) {
                		textField.setText(textField.getText() + DIVIDE_SYMBOL);
            		}
            		break;
            	case MULTIPLY_SYMBOL:
            		if(lastDigitIsNumber(textField) == true) {
                		textField.setText(textField.getText() + MULTIPLY_SYMBOL);
            		}
            		break; 
            	case "(":
            		textField.setText(textField.getText() + "(");
            		break;
            	case ")":
        		textField.setText(textField.getText() + ")");
        			break;
            	//0-9
            	default:
            		textField.setText(textField.getText() + button.getText());
            		break;
            	}
            }
        });
	}
	
	
	// Checks if the last digit in the textField is a number
	private static boolean lastDigitIsNumber(JTextField textField) {
        String text = textField.getText();
        if (!text.isEmpty() && Character.isDigit(text.charAt(text.length() - 1))) {
        	return true;
        } else {
        	return false;
        }
	}
	
		
	// Sets the font for an array of JButtons
	private static void setFont(JButton[] buttons, Font font) {
	    for (JButton button : buttons) {
	        button.setFont(font);
	    }
	}
	
	
	public static void addEnterKeyListener(JTextField textField) {
		String text = textField.getText();
	    textField.addKeyListener(new KeyListener() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        }
	        @Override
	        public void keyPressed(KeyEvent e) {
	            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	           		float result = CalculatorEngine.calculateExpressionNashorn(text);  		
            		if(Float.isInfinite(result)) {
            			textField.setText(INFINITY_SYMBOL);
            		} else {
                        String resultString = Float.toString(result);
                		textField.setText(resultString);
                		System.out.println("Result: " + resultString);
            		}
	            }
	        }
	        @Override
	        public void keyReleased(KeyEvent e) {
	        }
	    });
	}
}








// Restricts what you can type in the Textfield 
class CalculatorTextfieldRestriction extends PlainDocument {    
    private static final char DIVIDE_SYMBOL = '\u00F7';
    private static final char MULTIPLY_SYMBOL = '×';
    private static final char BACK_SYMBOL = '↚';
	
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }

        String currentText = getText(0, getLength());
        String allowedChars = "0123456789+-*/,().E∞" + MULTIPLY_SYMBOL + DIVIDE_SYMBOL + BACK_SYMBOL;

        for (char c : str.toCharArray()) {
            if (allowedChars.indexOf(c) != -1) {
                super.insertString(offs++, String.valueOf(c), a);
            }
        }
    }
}
