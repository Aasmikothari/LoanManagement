package com.hexa.dao;

import com.hexa.entity.Loan;
import com.hexa.exception.CustomerNotFoundException;
import com.hexa.exception.LoanNotFoundException;
import com.hexa.entity.Customer;


import java.util.List;

public interface LoanDao {
    void applyLoan(Loan loan, int custId) throws CustomerNotFoundException;
    Loan getLoanByLoanId(int loanId) throws LoanNotFoundException;
    List<Loan> getAllLoans();
    void addCustomer(Customer customer);
}