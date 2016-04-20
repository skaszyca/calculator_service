package com.shane.calculatorservice.polish;

/**
 * @author   Shane Kaszyca <shane1234@gmail.com>
 * @version  1.0
 * @since    2016-02-26
 */
public final class PolishConstants {

	public static final String INPUT_PROMPT  = ":> ";
	public static final String HELP_KEYWORD  = "HELP";
	public static final String EXIT_KEYWORD  = "EXIT";
	public static final String QUIT_KEYWORD  = "QUIT";
	
	public static final String PROPERTIES_FILE = "text.properties";
	
	// Validation Error Codes
	// These constants should map to an error message in the text.properties file
	public static final String INVALID_CHARACTERS = "INVALID_CHARACTERS";
	public static final String ERRORDIVIDEBYZERO = "ERRORDIVIDEBYZERO";
	public static final String OPERANDSFOROPERATOR = "OPERANDSFOROPERATOR";
	public static final String OPERANDNOTSUPPORTED = "OPERANDNOTSUPPORTED";
	public static final String FORMULANOTPROVIDED = "FORMULANOTPROVIDED";
}