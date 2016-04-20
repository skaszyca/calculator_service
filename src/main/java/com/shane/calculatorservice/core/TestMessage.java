package com.shane.calculatorservice.core;

public class TestMessage {
    private final long id;
    private final String message;
	
    public TestMessage(long id, String message) {
        this.id = id;
		this.message = message;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
	
}