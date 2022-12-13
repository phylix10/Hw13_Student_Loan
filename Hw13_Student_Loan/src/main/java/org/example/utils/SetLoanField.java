package org.example.utils;

import org.example.entity.Account;
import org.example.entity.Loan;
import org.example.entity.enumeration.Grade;
import org.example.entity.enumeration.LoanType;
import org.example.securiy.SecurityUtils;

import java.time.LocalDate;

public class SetLoanField {

    public Loan setField(LoanType loanType, Grade grade, Long amount) {
        Account loggedInAccount = SecurityUtils.getAccount();
        Long loanRegistrationDate = LocalDate.now().toEpochDay();

        Loan loan = new Loan();
        loan.setLoanType(loanType);
        loan.setGrade(grade);
        loan.setAmount(amount);
        loan.setLoanRegistrationDate(loanRegistrationDate);
        loan.setAccount(loggedInAccount);

        return loan;
    }
}
