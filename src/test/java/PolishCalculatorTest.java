import org.junit.Test;

import java.util.Stack;
import java.util.Properties;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.FileNotFoundException;
import com.shane.calculatorservice.polish.*;
import com.shane.calculatorservice.core.PolishResult;

public class PolishCalculatorTest {

    @Test
    public void testPolishValidation() {
	
		// Happy path test - see if all valid characters are good to go.
        PolishValidator validator = new PolishValidator("123-4+5*6/7%890 pd");
		String validResponse = validator.validateInput();

		assertEquals(null, validResponse);

		// Unhappy path test - throw some invalid characters in the mix.
		validator.setInputString("123-4+5*6/7%8abc defg90 pd");
		validResponse = validator.validateInput();
		assertEquals(PolishConstants.INVALID_CHARACTERS, validResponse);

		// Unhappy path test - specific Izea test
		validator.setInputString("2 2 r");
		validResponse = validator.validateInput();
		assertEquals(PolishConstants.INVALID_CHARACTERS, validResponse);

		// Unhappy path test - specific Izea test
		validator.setInputString("2.5 3 +");
		validResponse = validator.validateInput();
		assertEquals(PolishConstants.INVALID_CHARACTERS, validResponse);
    }

	@Test
	public void testFormulaFailure() {

		// Test to see that non integers fail
		PolishCalculator pc = new PolishCalculator();
		pc.setFormula("2147483648 0 +");
		
		PolishResult pr = pc.calculateResult();
	
		assertEquals(null, pr.getResult());
		assertEquals(PolishConstants.OPERANDNOTSUPPORTED + ":2147483648", pr.getError());

		// Test to ensure that formulas without enough operands are rejected
		pc.setFormula("98 +");
		
		pr = pc.calculateResult();
	
		assertEquals(null, pr.getResult());
	
	}
	
	@Test
	public void testAdd() {
		String strTenResult = "[10]";
		
		PolishCalculator pc = new PolishCalculator();
		pc.setFormula("1 9 +");
		
		PolishResult pr = pc.calculateResult();
		assertEquals(strTenResult, pr.getResult());

		pc.setFormula("2 8 +");
		
		pr = pc.calculateResult();
		assertEquals(strTenResult, pr.getResult());

		pc.setFormula("3 7 +");
		
		pr = pc.calculateResult();
		assertEquals(strTenResult, pr.getResult());

		pc.setFormula("4 6 +");
		
		pr = pc.calculateResult();
		assertEquals(strTenResult, pr.getResult());

		pc.setFormula("5 5 +");
		
		pr = pc.calculateResult();
		assertEquals(strTenResult, pr.getResult());

		pc.setFormula("1 2 3 4 + + +");
		
		pr = pc.calculateResult();
		assertEquals(strTenResult, pr.getResult());
		
	}

	@Test
	public void testSubtract() {
	
		String strFiveResult = "[5]";
		
		PolishCalculator pc = new PolishCalculator();
		pc.setFormula("10 5 -");
		
		PolishResult pr = pc.calculateResult();
		assertEquals(strFiveResult, pr.getResult());

		pc.setFormula("9 4 -");
		
		pr = pc.calculateResult();
		assertEquals(strFiveResult, pr.getResult());

		pc.setFormula("8 3 -");
		
		pr = pc.calculateResult();
		assertEquals(strFiveResult, pr.getResult());
		
		pc.setFormula("7 2 -");
		
		pr = pc.calculateResult();
		assertEquals(strFiveResult, pr.getResult());
		
		pc.setFormula("6 1 -");
		
		pr = pc.calculateResult();
		assertEquals(strFiveResult, pr.getResult());

		pc.setFormula("20 8 - 4 - 3 -");
		
		pr = pc.calculateResult();
		assertEquals(strFiveResult, pr.getResult());
	}

	@Test
	public void testMultiply() {
		String strResult = "[16]";
		
		PolishCalculator pc = new PolishCalculator();
		pc.setFormula("16 1 *");
		
		PolishResult pr = pc.calculateResult();
		assertEquals(strResult, pr.getResult());

		pc.setFormula("8 2 *");
		
		pr = pc.calculateResult();
		assertEquals(strResult, pr.getResult());

		pc.setFormula("4 4 *");
		
		pr = pc.calculateResult();
		assertEquals(strResult, pr.getResult());

		pc.setFormula("1 2 4 2 * * *");
		
		pr = pc.calculateResult();
		assertEquals(strResult, pr.getResult());
	}	

	@Test
	public void testDivide() {
		String strResult = "[3]";
		
		PolishCalculator pc = new PolishCalculator();
		pc.setFormula("24 8 /");
		
		PolishResult pr = pc.calculateResult();
		assertEquals(strResult, pr.getResult());

		pc.setFormula("12 4 /");
		
		pr = pc.calculateResult();
		assertEquals(strResult, pr.getResult());

		pc.setFormula("9 3 /");
		
		pr = pc.calculateResult();
		assertEquals(strResult, pr.getResult());

		pc.setFormula("90 2 / 15 /");
		
		pr = pc.calculateResult();
		assertEquals(strResult, pr.getResult());
		
		pc.setFormula("52 0 /");
		
		pr = pc.calculateResult();
		assertEquals(PolishConstants.ERRORDIVIDEBYZERO, pr.getError());		
	}	

	@Test
	public void testMod() {
	
		PolishCalculator pc = new PolishCalculator();
		pc.setFormula("10 5 %");
		
		PolishResult pr = pc.calculateResult();
		assertEquals("[0]", pr.getResult());

		pc.setFormula("1978 2 %");
		
		pr = pc.calculateResult();
		assertEquals("[0]", pr.getResult());

		pc.setFormula("100 7 %");
		
		pr = pc.calculateResult();
		assertEquals("[2]", pr.getResult());

	}	
	
	
	@Test
	public void testDuplicate() {
		PolishCalculator pc = new PolishCalculator();
		pc.setFormula("123 234 d");
		
		PolishResult pr = pc.calculateResult();
		assertEquals("[123, 234, 234]", pr.getResult());
		
		pc.setFormula("31415 d");
		
		pr = pc.calculateResult();
		assertEquals("[31415, 31415]", pr.getResult());
	}	

	
	@Test
	public void testIzeaExamples() {
		PolishCalculator pc = new PolishCalculator();
		pc.setFormula("6 7 +");
		
		PolishResult pr = pc.calculateResult();
		assertEquals("[13]", pr.getResult());
		
		pc.setFormula("4 -2 * 2 *");
		
		pr = pc.calculateResult();
		assertEquals("[-16]", pr.getResult());

		pc.setFormula("2 3 4 + d p *");
		
		pr = pc.calculateResult();
		assertEquals("[14]", pr.getResult());

		pc.setFormula("2 8 2 +");
		
		pr = pc.calculateResult();
		assertEquals("[2, 10]", pr.getResult());

		pc.setFormula("2 +");
		
		pr = pc.calculateResult();
		assertEquals(PolishConstants.OPERANDSFOROPERATOR + ":+", pr.getError());
	}		

	@Test
	public void testLeftoverStack() {
		PolishCalculator pc = new PolishCalculator();
		pc.setFormula("10 20 30");
		
		PolishResult pr = pc.calculateResult();
		assertEquals("[10, 20, 30]", pr.getResult());
	
	}	
	
}