package com.hexa.entity;

import java.time.LocalDate;

public class Customer {
    private int customerId;
    private String customerName;
    private String contactNo;
    private LocalDate dateOfJoining;

    public Customer() {}

    public Customer(int customerId, String customerName, String contactNo, LocalDate dateOfJoining) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.contactNo = contactNo;
        this.dateOfJoining = dateOfJoining;
    }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getContactNo() { return contactNo; }
    public void setContactNo(String contactNo) { this.contactNo = contactNo; }

    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public void setDateOfJoining(LocalDate dateOfJoining) { this.dateOfJoining = dateOfJoining; }

    @Override
    public String toString() {
        return "Customer [ID=" + customerId + ", Name=" + customerName + ", Contact=" + contactNo +
               ", DOJ=" + dateOfJoining + "]";
    }
}

