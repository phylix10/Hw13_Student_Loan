package org.example.utils;

import org.example.entity.Account;
import org.example.entity.Loan;
import org.example.entity.Student;
import org.example.entity.enumeration.LoanType;
import org.example.repository.AccountRepository;
import org.example.service.LoanService;
import org.example.service.StudentService;

import java.time.LocalDate;
import java.util.List;

public class LoanValidation {

    private final LoanService loanService = new LoanService();
    private final StudentService studentService = new StudentService();
    private final AccountRepository accountRepository = new AccountRepository();
    public boolean isOpen() {
        LocalDate now = LocalDate.now();

        LocalDate aban1 = LocalDate.parse(now.getYear() + "-10-22");
        LocalDate aban8 = LocalDate.parse(now.getYear() + "-10-31");

        LocalDate azar14 = LocalDate.parse(now.getYear() + "-12-04");
        LocalDate azar23 = LocalDate.parse(now.getYear() + "-12-15");

        return (now.isAfter(aban1) && now.isBefore(aban8)) || (now.isAfter(azar14) && now.isBefore(azar23));
    }

    public Boolean isRegistered(Long accountId, LoanType loanType) {
        Boolean isRegister = null;

        List<Loan> loans = loanService.findLoanByAccountId(accountId, loanType);

        if (loans != null) {
            for (Loan loan : loans) {
                LocalDate localDate = LocalDate.ofEpochDay(loan.getLoanRegistrationDate());
                LocalDate systemDate = LocalDate.now();

                isRegister = localDate.getYear() == systemDate.getYear() && localDate.getMonth().equals(systemDate.getMonth());
            }
        } else {
            isRegister = false;
        }

        return isRegister;
    }

    public Boolean checkGetLoanBySpouse(Account account, LoanType loanType) {
        Student student = studentService.findByNationalCode(account.getStudent().getSpouseInformation().getNationalCode());
        if (student != null) {
            Account newAccount = accountRepository.findByUsername(student.getNationalCode());
            Loan loan = loanService.findHousingLoanInThisYear(newAccount.getId(), loanType);
            return loan == null;
        } else {
            return true;
        }
    }

    public Boolean checkGetHousingLoanInThisYear(Long accountId, LoanType loanType) {
        Loan loan = loanService.findHousingLoanInThisYear(accountId, loanType);

        return loan == null;
    }
}
