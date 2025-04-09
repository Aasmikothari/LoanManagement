package com.hexa.entity;

public abstract class Loan {
    private int loanId;
    private String loanName;
    private double loanAmt;
    private int loanTenure; 
    private String loanType; 
    private int customerId; 

    public Loan() {}

    public Loan(int loanId, String loanName, double loanAmt, int loanTenure, String loanType, int customerId) {
        this.loanId = loanId;
        this.loanName = loanName;
        this.loanAmt = loanAmt;
        this.loanTenure = loanTenure;
        this.loanType = loanType;
        this.customerId = customerId;
    }

    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }

    public String getLoanName() { return loanName; }
    public void setLoanName(String loanName) { this.loanName = loanName; }

    public double getLoanAmt() { return loanAmt; }
    public void setLoanAmt(double loanAmt) { this.loanAmt = loanAmt; }

    public int getLoanTenure() { return loanTenure; }
    public void setLoanTenure(int loanTenure) { this.loanTenure = loanTenure; }

    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    @Override
    public String toString() {
        return "Loan [ID=" + loanId + ", Name=" + loanName + ", Amount=" + loanAmt + ", Tenure=" + loanTenure +
               ", Type=" + loanType + ", CustomerID=" + customerId + "]";
    }
}
