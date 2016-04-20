package com.shane.calculatorservice.core;

public class Formula {
    private String formula;
	
    public Formula(String formula) {
        this.formula = formula;
    }

    public Formula() {
    }

    public String getFormula() {
        return formula;
    }
	
    public void setFormula(String formula) {
        this.formula = formula;
    }	
}