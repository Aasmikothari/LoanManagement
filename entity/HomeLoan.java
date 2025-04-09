package com.hexa.entity;

public class HomeLoan extends Loan {
    private String propertyName;
    private double propertyValue;

    public HomeLoan() {}

    public HomeLoan(int loanId, String loanName, double loanAmt, int loanTenure,
                    String loanType, int customerId, String propertyName, double propertyValue) {
        super(loanId, loanName, loanAmt, loanTenure, loanType, customerId);
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
    }

    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

    public double getPropertyValue() { return propertyValue; }
    public void setPropertyValue(double propertyValue) { this.propertyValue = propertyValue; }

    @Override
    public String toString() {
        return super.toString() + ", HomeLoan [Property=" + propertyName + ", Value=" + propertyValue + "]";
    }
}
