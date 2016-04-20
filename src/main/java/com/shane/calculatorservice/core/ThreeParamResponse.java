package com.shane.calculatorservice.core;

public class ThreeParamResponse {
    private final long id;
    private final String result;
	private final String errorCode;
	
    public ThreeParamResponse(long id, String result, String errorCode) {
        this.id = id;
        this.result = result;
		this.errorCode = errorCode;
    }

    public long getId() {
        return id;
    }

    public String getResult() {
        return result;
    }
	
    public String getErrorCode() {
        return errorCode;
    }
	
}