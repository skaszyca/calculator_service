package com.shane.calculatorservice;

import com.shane.calculatorservice.resources.*;
import com.shane.calculatorservice.health.*;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class CalculatorService extends Service<CalculatorConfiguration> {
    public static void main(String[] args) throws Exception {
        new CalculatorService().run(args);
    }

    @Override
    public void initialize(Bootstrap<CalculatorConfiguration> bootstrap) {
        bootstrap.setName("calculate");
    }

	@Override
	public void run(CalculatorConfiguration configuration,
					Environment environment) {
		final String template = configuration.getTemplate();
		final String defaultFormula = configuration.getDefaultFormula();
		final String message = configuration.getMessage();
		final String errorCode = configuration.getErrorCode();
		environment.addResource(new CalculatorResource(template, defaultFormula, errorCode));
		environment.addResource(new EchoTestResource(message));
		environment.addHealthCheck(new TemplateHealthCheck(template));
	}
	
}