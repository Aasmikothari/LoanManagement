package com.hexa.client;

import com.hexa.dao.LoanDao;
import com.hexa.dao.LoanDaoImpl;
import com.hexa.entity.*;
import com.hexa.exception.*;

import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LoanDao loanDao = new LoanDaoImpl();

        while (true) {
            System.out.println("\n--- Loan Management System ---");
            System.out.println("1. Register Customer");
            System.out.println("2. Apply for Loan");
            System.out.println("3. View Loan by ID");
            System.out.println("4. View All Loans");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
            	
            case 1 -> {
                System.out.print("Enter Customer ID: ");
                int id = sc.nextInt();
                sc.nextLine(); 

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Contact Number: ");
                String contact = sc.nextLine();

                System.out.print("Enter Date of Joining (yyyy-mm-dd): ");
                String dojStr = sc.nextLine();

                Customer cust = new Customer(id, name, contact, LocalDate.parse(dojStr));
                loanDao.addCustomer(cust);
            }
            
                case 2 -> {
                    System.out.print("Enter Loan ID: ");
                    int loanId = sc.nextInt();
                    sc.nextLine(); 

                    System.out.print("Enter Loan Name: ");
                    String loanName = sc.nextLine();

                    System.out.print("Enter Loan Amount: ");
                    double loanAmt = sc.nextDouble();

                    System.out.print("Enter Loan Tenure (months): ");
                    int tenure = sc.nextInt();
                    sc.nextLine(); // flush

                    System.out.print("Enter Loan Type (HomeLoan/CarLoan): ");
                    String type = sc.nextLine();

                    System.out.print("Enter Customer ID: ");
                    int custId = sc.nextInt();

                    Loan loan;
                    if ("HomeLoan".equalsIgnoreCase(type)) {
                        sc.nextLine(); 
                        System.out.print("Enter Property Name: ");
                        String propName = sc.nextLine();
                        System.out.print("Enter Property Value: ");
                        double propVal = sc.nextDouble();
                        loan = new HomeLoan(loanId, loanName, loanAmt, tenure, type, custId, propName, propVal);
                    } else {
                        sc.nextLine();
                        System.out.print("Enter Car Name: ");
                        String carName = sc.nextLine();
                        System.out.print("Enter Car Value: ");
                        double carVal = sc.nextDouble();
                        loan = new CarLoan(loanId, loanName, loanAmt, tenure, type, custId, carName, carVal);
                    }

                    try {
                        loanDao.applyLoan(loan, custId);
                        System.out.println("Loan applied successfully!");
                    } catch (CustomerNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {
                    System.out.print("Enter Loan ID: ");
                    int loanId = sc.nextInt();
                    try {
                        Loan loan = loanDao.getLoanByLoanId(loanId);
                        System.out.println(loan);
                    } catch (LoanNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 4 -> {
                    List<Loan> loans = loanDao.getAllLoans();
                    if (loans.isEmpty()) {
                        System.out.println("No loans found.");
                    } else {
                        for (Loan l : loans) {
                            System.out.println(l);
                        }
                    }
                }

                case 5 -> {
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    System.exit(0);
                }

                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
