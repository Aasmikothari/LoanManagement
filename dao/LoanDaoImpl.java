package com.hexa.dao;

import com.hexa.db.DBUtil;
import com.hexa.entity.*;
import com.hexa.exception.CustomerNotFoundException;
import com.hexa.exception.LoanNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanDaoImpl implements LoanDao {

    @Override
    public void applyLoan(Loan loan, int custId) throws CustomerNotFoundException {
        try (Connection conn = DBUtil.getConnection()) {

            String checkQuery = "SELECT 1 FROM customer WHERE cust_id = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, custId);
                ResultSet rs = checkStmt.executeQuery();
                if (!rs.next()) {
                    throw new CustomerNotFoundException("Customer with ID " + custId + " not found.");
                }
            }

            String insertQuery = """
                INSERT INTO loan (loanId, loanName, loanAmt, loanTenure, loanType, customer_id,
                                  propertyName, proprtyValue, carname, carvalue)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setInt(1, loan.getLoanId());
                pstmt.setString(2, loan.getLoanName());
                pstmt.setDouble(3, loan.getLoanAmt());
                pstmt.setInt(4, loan.getLoanTenure());
                pstmt.setString(5, loan.getLoanType());
                pstmt.setInt(6, custId);

                // Handle specific subclass
                if (loan instanceof HomeLoan homeLoan) {
                    pstmt.setString(7, homeLoan.getPropertyName());
                    pstmt.setDouble(8, homeLoan.getPropertyValue());
                    pstmt.setNull(9, Types.VARCHAR);
                    pstmt.setNull(10, Types.DOUBLE);
                } else if (loan instanceof CarLoan carLoan) {
                    pstmt.setNull(7, Types.VARCHAR);
                    pstmt.setNull(8, Types.DOUBLE);
                    pstmt.setString(9, carLoan.getCarName());
                    pstmt.setDouble(10, carLoan.getCarValue());
                } else {
                    pstmt.setNull(7, Types.VARCHAR);
                    pstmt.setNull(8, Types.DOUBLE);
                    pstmt.setNull(9, Types.VARCHAR);
                    pstmt.setNull(10, Types.DOUBLE);
                }

                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Loan getLoanByLoanId(int loanId) throws LoanNotFoundException {
        Loan loan = null;

        String query = "SELECT * FROM loan WHERE loanId = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, loanId);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                throw new LoanNotFoundException("Loan with ID " + loanId + " not found.");
            }

            String loanType = rs.getString("loanType");

            if ("HomeLoan".equalsIgnoreCase(loanType)) {
                loan = new HomeLoan(
                        rs.getInt("loanId"),
                        rs.getString("loanName"),
                        rs.getDouble("loanAmt"),
                        rs.getInt("loanTenure"),
                        loanType,
                        rs.getInt("customer_id"),
                        rs.getString("propertyName"),
                        rs.getDouble("proprtyValue")
                );
            } else if ("CarLoan".equalsIgnoreCase(loanType)) {
                loan = new CarLoan(
                        rs.getInt("loanId"),
                        rs.getString("loanName"),
                        rs.getDouble("loanAmt"),
                        rs.getInt("loanTenure"),
                        loanType,
                        rs.getInt("customer_id"),
                        rs.getString("carname"),
                        rs.getDouble("carvalue")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loan;
    }


    @Override
    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();

        String query = "SELECT * FROM loan";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String loanType = rs.getString("loanType");

                if ("HomeLoan".equalsIgnoreCase(loanType)) {
                    HomeLoan homeLoan = new HomeLoan(
                            rs.getInt("loanId"),
                            rs.getString("loanName"),
                            rs.getDouble("loanAmt"),
                            rs.getInt("loanTenure"),
                            loanType,
                            rs.getInt("customer_id"),
                            rs.getString("propertyName"),
                            rs.getDouble("proprtyValue")
                    );
                    loans.add(homeLoan);

                } else if ("CarLoan".equalsIgnoreCase(loanType)) {
                    CarLoan carLoan = new CarLoan(
                            rs.getInt("loanId"),
                            rs.getString("loanName"),
                            rs.getDouble("loanAmt"),
                            rs.getInt("loanTenure"),
                            loanType,
                            rs.getInt("customer_id"),
                            rs.getString("carname"),
                            rs.getDouble("carvalue")
                    );
                    loans.add(carLoan);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loans;
    }
    
    @Override
    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customer (cust_id, cust_name, contact_no, doj) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, customer.getCustomerId());
            pstmt.setString(2, customer.getCustomerName());
            pstmt.setString(3, customer.getContactNo());
            pstmt.setDate(4, java.sql.Date.valueOf(customer.getDateOfJoining()));

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Customer added successfully.");
            } else {
                System.out.println("Failed to add customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
