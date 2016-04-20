package com.shane.calculatorservice.polish;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author   Shane Kaszyca <shane1234@gmail.com>
 * @version  1.0
 * @since    2016-02-26
 */
public class PolishValidator {

	public static final String RETURN_INVALID_OPERATOR = "invalid_operator";
	
	private static final String VALID_CHARACTERS_PATTERN = "[\\+\\-\\*\\/\\%pd0-9 ]+";
		
	private String m_inputString;
	private Pattern m_validCharPattern;
	
	private void setupValidators() {
		m_validCharPattern = Pattern.compile(VALID_CHARACTERS_PATTERN);
	}

	/**
	* Constructor.  Initialize variables and setup validators.
	**/
	public PolishValidator(String strInputString) {
		m_inputString = strInputString;
		setupValidators();
	}
	
	/**
	* Setter for member variable m_inputString
	**/
	public void setInputString(String strInputString) {
		m_inputString = strInputString;
	}
	
	/**
	* Getter for member variable m_inputString
	**/
	public String getInputString() {
		return m_inputString;
	}

	/**
	* Method to validate whether or not there are any invalid characters
	* in the input string
	**/
	private boolean allValidCharacters() {
		Matcher matcher = m_validCharPattern.matcher(m_inputString);
		return matcher.matches();
	}
	
	/**
	* Main method to validate the input provided to the class.
	* This method will return either null or an error code.
	* Returning null is a good thing.  If an error code is returned
	* Then the caller can use this to return a human-readable message to the user.
	**/
	public String validateInput() {
		String strReturnValue = null;

		strReturnValue = (m_inputString == null || m_inputString.equalsIgnoreCase("")) ? PolishConstants.FORMULANOTPROVIDED : null;		

		if (strReturnValue == null) {
			strReturnValue = (!allValidCharacters()) ? PolishConstants.INVALID_CHARACTERS : null;
		}
		
		return strReturnValue;
	}
	
	
}
