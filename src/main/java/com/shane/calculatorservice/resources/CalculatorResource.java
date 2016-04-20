package com.shane.calculatorservice.resources;

import com.shane.calculatorservice.polish.PolishCalculator;
import com.shane.calculatorservice.polish.PolishConstants;
import com.shane.calculatorservice.core.PolishResult;
import com.shane.calculatorservice.core.Formula;
import com.shane.calculatorservice.core.ThreeParamResponse;
import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/calculate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalculatorResource {
    private final String template;
    private final String defaultFormula;
	private final String errorCode;
    private final AtomicLong counter;

    public CalculatorResource(String template, String defaultFormula, String errorCode) {
        this.template = template;
        this.defaultFormula = defaultFormula;
		this.errorCode = errorCode;
        this.counter = new AtomicLong();
    }

    @POST
    @Timed
    public ThreeParamResponse calculate(Formula formula) {
	
		if (formula == null) {
			return new ThreeParamResponse(counter.incrementAndGet(), null, PolishConstants.FORMULANOTPROVIDED);
		} else {
			PolishCalculator pc = new PolishCalculator();
			pc.setFormula(formula.getFormula());
			
			PolishResult pr = pc.calculateResult();
			return new ThreeParamResponse(counter.incrementAndGet(), pr.getResult(), pr.getError());
		}
    }
}