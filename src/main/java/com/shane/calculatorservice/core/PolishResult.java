package com.shane.calculatorservice.core;

public class PolishResult {
    private String result;
	private String error;
	
    public PolishResult(String result, String error) {
		this.result = result;
		this.error = error;
    }

    public String getResult() {
        return result;
    }
	
    public void setResult(String result) {
        this.result = result;
    }	
	
    public String getError() {
        return error;
    }
	
    public void setError(String error) {
        this.error = error;
    }		
}