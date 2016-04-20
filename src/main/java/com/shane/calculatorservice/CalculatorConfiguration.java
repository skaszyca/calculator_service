package com.shane.calculatorservice;

import com.yammer.dropwizard.config.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class CalculatorConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private String template;

    @NotEmpty
    @JsonProperty
    private String defaultFormula;
	
	@NotEmpty
	@JsonProperty
	private String errorCode;

	@NotEmpty
	@JsonProperty
	private String message;

    public String getTemplate() {
        return template;
    }

    public String getDefaultFormula() {
        return defaultFormula;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

}