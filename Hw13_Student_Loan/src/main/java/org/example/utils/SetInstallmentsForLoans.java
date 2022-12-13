package org.example.utils;

import org.example.entity.Account;
import org.example.entity.Card;
import org.example.entity.Installments;
import org.example.entity.enumeration.Grade;
import org.example.entity.enumeration.LoanType;
import org.example.service.CardService;
import org.example.service.InstallmentsService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SetInstallmentsForLoans {
    private final InstallmentCalculation installmentCalculation = new InstallmentCalculation();
    private final InstallmentsService installmentsService = new InstallmentsService();
    private final CardService cardService = new CardService();

    private static final DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");

    public long setDueDate(Account account) {
        LocalDate localDate = LocalDate.now();
        String day = localDate.format(dayFormatter);
        long dueDate;
        Grade grade = account.getStudent().getGrade();

        if (grade == Grade.ASSOCIATE_DEGREE || grade == Grade.DISCONTINUOUS_MASTERS_DEGREE) {
            int entryYear = LocalDate.parse(account.getStudent().getEnteringYear()).getYear();
            entryYear += 3;
            dueDate = LocalDate.parse(entryYear + "-" + localDate.getMonth().getValue() + "-" + day).toEpochDay();
        } else if (grade == Grade.UNDERGRADUATE) {
            int entryYear = LocalDate.parse(account.getStudent().getEnteringYear()).getYear();
            entryYear += 5;
            dueDate = LocalDate.parse(entryYear + "-" + localDate.getMonth().getValue() + "-" + day).toEpochDay();
        } else if (grade == Grade.CONTINUOUS_MASTERS_DEGREE) {
            int entryYear = LocalDate.parse(account.getStudent().getEnteringYear()).getYear();
            entryYear += 7;
            dueDate = LocalDate.parse(entryYear + "-" + localDate.getMonth().getValue() + "-" + day).toEpochDay();
        } else {
            int entryYear = LocalDate.parse(account.getStudent().getEnteringYear()).getYear();
            entryYear += 6;
            dueDate = LocalDate.parse(entryYear + "-" + localDate.getMonth().getValue() + "-" + day).toEpochDay();
        }

        return dueDate;
    }

    public Installments setFirstInstallment(LoanType loanType, Account account, Double amount) {
        long dueDate = setDueDate(account);

        Installments firstInstallment = new Installments();
        firstInstallment.setLoanType(loanType);
        firstInstallment.setDueDate(dueDate);
        firstInstallment.setAmountOfInstallment(amount);
        firstInstallment.setIsPayment(false);
        firstInstallment.setAccount(account);

        return firstInstallment;
    }

    public Installments setSecondInstallment(LoanType loanType, Account account, Double amount) {
        long oldDueDate = setDueDate(account);
        LocalDate localDate = LocalDate.ofEpochDay(oldDueDate);
        String day = localDate.format(dayFormatter);
        long dueDate = LocalDate.parse((localDate.getYear() + 1) + "-" + localDate.getMonth().getValue() + "-" + day).toEpochDay();

        Installments firstInstallment = new Installments();
        firstInstallment.setLoanType(loanType);
        firstInstallment.setDueDate(dueDate);
        firstInstallment.setAmountOfInstallment(amount);
        firstInstallment.setIsPayment(false);
        firstInstallment.setAccount(account);

        return firstInstallment;
    }

    public Installments setThirdInstallment(LoanType loanType, Account account, Double amount) {
        long oldDueDate = setDueDate(account);
        LocalDate localDate = LocalDate.ofEpochDay(oldDueDate);
        String day = localDate.format(dayFormatter);
        long dueDate = LocalDate.parse((localDate.getYear() + 2) + "-" + localDate.getMonth().getValue() + "-" + day).toEpochDay();

        Installments firstInstallment = new Installments();
        firstInstallment.setLoanType(loanType);
        firstInstallment.setDueDate(dueDate);
        firstInstallment.setAmountOfInstallment(amount);
        firstInstallment.setIsPayment(false);
        firstInstallment.setAccount(account);

        return firstInstallment;
    }

    public Installments setFourthInstallment(LoanType loanType, Account account, Double amount) {
        long oldDueDate = setDueDate(account);
        LocalDate localDate = LocalDate.ofEpochDay(oldDueDate);
        String day = localDate.format(dayFormatter);
        long dueDate = LocalDate.parse((localDate.getYear() + 3) + "-" + localDate.getMonth().getValue() + "-" + day).toEpochDay();

        Installments firstInstallment = new Installments();
        firstInstallment.setLoanType(loanType);
        firstInstallment.setDueDate(dueDate);
        firstInstallment.setAmountOfInstallment(amount);
        firstInstallment.setIsPayment(false);
        firstInstallment.setAccount(account);

        return firstInstallment;
    }

    public Installments setFifthInstallment(LoanType loanType, Account account, Double amount) {
        long oldDueDate = setDueDate(account);
        LocalDate localDate = LocalDate.ofEpochDay(oldDueDate);
        String day = localDate.format(dayFormatter);
        long dueDate = LocalDate.parse((localDate.getYear() + 4) + "-" + localDate.getMonth().getValue() + "-" + day).toEpochDay();

        Installments firstInstallment = new Installments();
        firstInstallment.setLoanType(loanType);
        firstInstallment.setDueDate(dueDate);
        firstInstallment.setAmountOfInstallment(amount);
        firstInstallment.setIsPayment(false);
        firstInstallment.setAccount(account);

        return firstInstallment;
    }

    public void save(LoanType loanType, Account loggedInAccount, Long amount) {
        Installments firstInstallment = setFirstInstallment(loanType, loggedInAccount, (installmentCalculation.firstYear(amount) * 12));
        Installments secondInstallment = setSecondInstallment(loanType, loggedInAccount, (installmentCalculation.secondYear(amount) * 12));
        Installments thirdInstallment = setThirdInstallment(loanType, loggedInAccount, (installmentCalculation.thirdYear(amount) * 12));
        Installments fourthInstallment = setFourthInstallment(loanType, loggedInAccount, (installmentCalculation.fourthYear(amount) * 12));
        Installments fifthInstallment = setFifthInstallment(loanType, loggedInAccount, (installmentCalculation.fifthYear(amount) * 12));

        installmentsService.add(firstInstallment);
        installmentsService.add(secondInstallment);
        installmentsService.add(thirdInstallment);
        installmentsService.add(fourthInstallment);
        installmentsService.add(fifthInstallment);

        Card card = cardService.findCardByAccountId(loggedInAccount.getId());

        System.out.println("The loan in the amount: " + amount + " was deposited to the account with the card number: " +
                card.getCardNumber().substring(0, 4) + "-" + card.getCardNumber().substring(4, 6) + "**" + "-" +
                "****" + "-" + card.getCardNumber().substring(12, 16));
    }
}
