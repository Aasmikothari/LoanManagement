package com.hexa.entity;

public class CarLoan extends Loan {
    private String carName;
    private double carValue;

    public CarLoan() {}

    public CarLoan(int loanId, String loanName, double loanAmt, int loanTenure,
                   String loanType, int customerId, String carName, double carValue) {
        super(loanId, loanName, loanAmt, loanTenure, loanType, customerId);
        this.carName = carName;
        this.carValue = carValue;
    }

    public String getCarName() { return carName; }
    public void setCarName(String carName) { this.carName = carName; }

    public double getCarValue() { return carValue; }
    public void setCarValue(double carValue) { this.carValue = carValue; }

    @Override
    public String toString() {
        return super.toString() + ", CarLoan [Car=" + carName + ", Value=" + carValue + "]";
    }
}
