package com.shane.calculatorservice.polish;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Properties;
import java.util.EmptyStackException;

import java.lang.String;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.shane.calculatorservice.core.PolishResult;

/**
 * @author   Shane Kaszyca <shane1234@gmail.com>
 * @version  1.0
 * @since    2016-02-26
 */
public class PolishCalculator {

	private String errorCode;
	private String resultValue;
	private String formula;
	
	private static Properties textProperties = new Properties();
	private static ArrayList<String> validOperators = new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "%", "p", "d"));
	
	public void PolishCalculator() {
		errorCode = null;
		resultValue = null;
	}
	
	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	/**
	 * getResult is the main actor in this class.  Using the formula that is set in the constructor, do validation
	 * followed by calculation and return a PolishResult object that conains our result value and error code.
	 * @return A simple Java Object containing the error code and result value.
	 */
	public PolishResult calculateResult() {
				
		PolishValidator pVal = new PolishValidator(formula);
		errorCode = pVal.validateInput();

		if (errorCode == null) {
			Stack<String> resultStack = calculateRPNResult(formula);

			if (resultStack != null) {
				resultValue = resultStack.toString();
			}
		}
			
		return new PolishResult(resultValue, errorCode);
	}

	
	/**
	 * Calculates the result of a Reverse Polish Notation math formula.
	 * The method will return a stack to the caller.  If the formula
	 * has a valid integer result then the stack will have only one value
	 * in it.  If there is an error, then we will return null.  If there
	 * are not enough operators, then the stack could contain multiple values
	 * <p>
	 *
	 * @param  strInput The mathematical formula in a string format
	 * @return          A Stack containing the final result or left over parameters
	 */
	private Stack<String> calculateRPNResult(String strInput) {
		ArrayList<String> inputParts = new ArrayList<String>(Arrays.asList(strInput.split(" ")));
		Stack<String> rpnStack = new Stack<String>();
		
		String strResult = "";
		
		for (int i = 0; i < inputParts.size(); i++) {
			String strInputPart = inputParts.get(i);
			try {
				int iOperand = Integer.parseInt(strInputPart);
				
				// If we didn't throw an exception, we have an integer.  Push the inputPart onto the rpnStack
				rpnStack.push(strInputPart);
				
			// If we can't parseInt, then either the input part is a valid operator or a value greater than supported integers
			} catch (NumberFormatException nfe) {
			
				if (validOperators.contains(strInputPart)) {
					
					try {
					
						// Duplicate Operator
						if (strInputPart.equals("d")) {
						
							// We need to do the parseInt method to ensure we're going to duplicate an operand
							// We don't just want to push a peek onto the stack without knowing it's an integer
							rpnStack.push(String.valueOf(Integer.parseInt(rpnStack.peek())));

							continue;
							
						// Print (Pop) Operator
						} else if (strInputPart.equals("p")) {
							int i_operandA = Integer.parseInt(rpnStack.pop());

							// For purposes of the webservice version of the polish calculator, the P operator is now a "pop" - not print.
							//System.out.println(i_operandA);

							continue;
							
						// Other Math Operators
						} else {
							int i_operandA = Integer.parseInt(rpnStack.pop());
							int i_operandB = Integer.parseInt(rpnStack.pop());
						
							if (strInputPart.equals("+")) {
								rpnStack.push(String.valueOf(i_operandA + i_operandB));
							} else if (strInputPart.equals("-")) {
								rpnStack.push(String.valueOf(i_operandB - i_operandA));
							} else if (strInputPart.equals("*")) {
								rpnStack.push(String.valueOf(i_operandA * i_operandB));
							} else if (strInputPart.equals("/")) {
								if (i_operandA == 0) {
									errorCode = PolishConstants.ERRORDIVIDEBYZERO;
									return null;
								} else {
									rpnStack.push(String.valueOf(i_operandB / i_operandA));
								}
							} else if (strInputPart.equals("%")) {
								if (i_operandA == 0) {
									errorCode = PolishConstants.ERRORDIVIDEBYZERO;
									return null;
								} else {
									rpnStack.push(String.valueOf(i_operandB % i_operandA));
								}
							}
						}
						
					// There were not enough operands for the operator
					} catch (EmptyStackException ese) {
						errorCode = "OPERANDSFOROPERATOR:" + strInputPart;
						return null;
					}
						
				// If the value was not a valid operator, then it was a number too big or too small to support.
				} else {
					errorCode = "OPERANDNOTSUPPORTED:" + strInputPart;
					return null;
				}
			}
		}
		
		return rpnStack;

	}
	
}