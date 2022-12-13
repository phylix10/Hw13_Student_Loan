package org.example.menu;

import org.example.entity.*;
import org.example.entity.enumeration.*;
import org.example.exception.UserNotFoundException;
import org.example.securiy.SecurityUtils;
import org.example.service.*;
import org.example.utils.*;


import java.time.LocalDate;
import java.util.*;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final AccountService accountService = new AccountService();
    private static final LoanService loanService = new LoanService();
    private static final PrintMenu printMenu = new PrintMenu();
    private static final SetCardFields setCardField = new SetCardFields();
    private static final SetLoanField setLoanField = new SetLoanField();
    private static final SetInstallmentsForLoans setInstallmentsForLoans = new SetInstallmentsForLoans();
    private static final CardService cardService = new CardService();
    private static final InstallmentsService installmentsService = new InstallmentsService();
    private static final MainMenu app = new MainMenu();
    private static final LoanValidation loanValidation = new LoanValidation();
    private static final SetStudentRegistrationFields studentRegistrationFields = new SetStudentRegistrationFields();

    public void run() {

        boolean flag = true;

        while (flag) {
            printMenu.mainMenu();

            System.out.print("Choose an option: ");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1:
                        app.login();
                        break;
                    case 2:
                        app.register();
                        break;
                    case 3:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public void register() {
        Student student = studentRegistrationFields.setRequiredFields();

        if (student != null) {
            try {
                studentService.add(student);
                System.out.println("Registration was successful -> your password: " + student.getAccount().getPassword());
            } catch (RuntimeException runtimeException) {
                System.out.println(runtimeException.getMessage());
            }
        }
    }

    public void login() {
        System.out.print("Enter your username (nationalCode): ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        try {
            if (accountService.login(username, password)) {

                boolean flag = true;

                while (flag) {
                    printMenu.loginMenu();

                    System.out.print("Choose an option: ");

                    try {
                        int input = scanner.nextInt();
                        scanner.nextLine();

                        switch (input) {
                            case 1:
                                registerLoan();
                                break;
                            case 2:
                                loanRepayment();
                                break;
                            case 3:
                                flag = false;
                                break;
                        }
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                    }
                }
            }
        } catch (UserNotFoundException userNotFoundException) {
            System.out.println(userNotFoundException.getMessage());
        }
    }

    public void registerLoan() {
        if (loanValidation.isOpen()) {
            Account loggedInAccount = SecurityUtils.getAccount();

            if (!loggedInAccount.getStudent().getIsGraduated()) {
                boolean flag = true;

                while (flag) {
                    printMenu.loanTypeMenu();

                    System.out.print("Choose an option: ");

                    try {
                        int input = scanner.nextInt();
                        scanner.nextLine();

                        switch (input) {
                            case 1:
                                tuitionLoan();
                                break;
                            case 2:
                                educationLoan();
                                break;
                            case 3:
                                housingLoan();
                                break;
                            case 4:
                                flag = false;
                                break;
                        }
                    } catch (InputMismatchException e) {
                        scanner.nextLine();
                    }
                }
            } else {
                System.out.println("You have graduated :(");
            }
        } else {
            System.out.println("Student loan application is closed :(");
        }
    }

    public void tuitionLoan() {
        Account loggedInAccount = SecurityUtils.getAccount();

        Grade grade = loggedInAccount.getStudent().getGrade();
        LoanType loanType = LoanType.TUITION;

        if (!loanValidation.isRegistered(loggedInAccount.getId(), LoanType.TUITION)) {
            if (loggedInAccount.getStudent().getTypeOfUniversity() == UniversityType.DAILY_STATE_UNIVERSITY) {
                System.out.println("You are not a tuition-paying student");
            } else {
                if (grade == Grade.ASSOCIATE_DEGREE || grade == Grade.UNDERGRADUATE) {
                    Long amount = 1_300_000L;

                    Loan loan = setLoanField.setField(loanType, grade, amount);

                    loanService.add(loan);

                    setInstallmentsForLoans.save(loanType, loggedInAccount, amount);

                } else if (grade == Grade.CONTINUOUS_MASTERS_DEGREE || grade == Grade.DISCONTINUOUS_MASTERS_DEGREE || grade == Grade.PROFESSIONAL_DOCTORATE || grade == Grade.CONTINUOUS_DOCTORAL_DEGREE) {
                    Long amount = 2_600_000L;

                    Loan loan = setLoanField.setField(loanType, grade, amount);

                    loanService.add(loan);

                    setInstallmentsForLoans.save(loanType, loggedInAccount, amount);

                } else if (grade == Grade.NON_CONTINUOUS_SPECIALIZED_DOCTORATE) {
                    Long amount = 65_000_000L;

                    Loan loan = setLoanField.setField(loanType, grade, amount);

                    loanService.add(loan);

                    setInstallmentsForLoans.save(loanType, loggedInAccount, amount);
                }
            }
        } else {
            System.out.println("You have received the tuition loan this semester");
        }
    }

    public void educationLoan() {
        Account loggedInAccount = SecurityUtils.getAccount();

        Grade grade = loggedInAccount.getStudent().getGrade();
        LoanType loanType = LoanType.EDUCATION_LOAN;

        if (!loanValidation.isRegistered(loggedInAccount.getId(), LoanType.EDUCATION_LOAN)) {
            if (grade == Grade.ASSOCIATE_DEGREE || grade == Grade.UNDERGRADUATE) {
                Long amount = 1_900_000L;

                Loan loan = setLoanField.setField(loanType, grade, amount);

                loanService.add(loan);

                setInstallmentsForLoans.save(loanType, loggedInAccount, amount);

            } else if (grade == Grade.CONTINUOUS_MASTERS_DEGREE || grade == Grade.DISCONTINUOUS_MASTERS_DEGREE || grade == Grade.PROFESSIONAL_DOCTORATE || grade == Grade.CONTINUOUS_DOCTORAL_DEGREE) {
                Long amount = 2_250_000L;

                Loan loan = setLoanField.setField(loanType, grade, amount);

                loanService.add(loan);

                setInstallmentsForLoans.save(loanType, loggedInAccount, amount);

            } else if (grade == Grade.NON_CONTINUOUS_SPECIALIZED_DOCTORATE) {
                Long amount = 2_600_000L;

                Loan loan = setLoanField.setField(loanType, grade, amount);

                loanService.add(loan);

                setInstallmentsForLoans.save(loanType, loggedInAccount, amount);
            }
        } else {
            System.out.println("You have received the education loan this semester");
        }
    }

    public void housingLoan() {
        Account loggedInAccount = SecurityUtils.getAccount();

        Grade grade = loggedInAccount.getStudent().getGrade();
        LoanType loanType = LoanType.HOUSING_LOAN;

        if (loggedInAccount.getStudent().getIsMarried()) {
            if (loggedInAccount.getStudent().getHouseType() == HouseType.RENTAL) {
                if (loanValidation.checkGetLoanBySpouse(loggedInAccount, loanType)) {
                    City city = loggedInAccount.getStudent().getHousingInformation().getCity();
                    if (loanValidation.checkGetHousingLoanInThisYear(loggedInAccount.getId(), loanType)) {
                        if (city == City.TEHRAN) {
                            Long amount = 32_000_000L;

                            Loan loan = setLoanField.setField(loanType, grade, amount);

                            loanService.add(loan);

                            setInstallmentsForLoans.save(loanType, loggedInAccount, amount);

                        } else if (city == City.GUILAN || city == City.ESFAHAN || city == City.EAST_AZARBAIJAN || city == City.FARS || city == City.KHOZESTAN || city == City.QOM || city == City.KHORASAN_RAZAVI || city == City.ALBORZ) {
                            Long amount = 26_000_000L;

                            Loan loan = setLoanField.setField(loanType, grade, amount);

                            loanService.add(loan);

                            setInstallmentsForLoans.save(loanType, loggedInAccount, amount);

                        } else if (city == City.OTHER) {
                            Long amount = 19_500_000L;

                            Loan loan = setLoanField.setField(loanType, grade, amount);

                            loanService.add(loan);

                            setInstallmentsForLoans.save(loanType, loggedInAccount, amount);
                        }
                    } else {
                        System.out.println("You have received a mortgage loan this year :(");
                    }
                } else {
                    System.out.println("You have received a housing deposit loan from your spouse this year :(");
                }
            } else {
                System.out.println("You do not have a rented house :(");
            }
        } else {
            System.out.println("You are not married :(");
        }
    }

    public void loanRepayment() {
        Account loggedInAccount = SecurityUtils.getAccount();

        if (loggedInAccount.getStudent().getIsGraduated()) {
            boolean flag = true;

            while (flag) {
                printMenu.loanRepaymentMenu();

                System.out.print("Choose an option: ");

                try {
                    int input = scanner.nextInt();
                    scanner.nextLine();

                    switch (input) {
                        case 1:
                            showInstallmentsPaid();
                            break;
                        case 2:
                            showUnpaidInstallments();
                            break;
                        case 3:
                            repayment();
                            break;
                        case 4:
                            flag = false;
                            break;
                    }
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("You have not graduated :(");
        }
    }

    public void showInstallmentsPaid() {

        Account loggedInAccount = SecurityUtils.getAccount();

        List<Installments> list = installmentsService.findInstallments(loggedInAccount.getId(), true);
        if (list == null) {
            System.out.println("You have no paid installments :(");
        } else {
            System.out.println("_____________________________________________________________________________");
            System.out.println("| Id  |   Loan Type       |  Date Of Payment    |       Amount Paid         |");
            System.out.println("_____________________________________________________________________________");
            for (Installments installment : list) {
                System.out.printf("|  %-3s|   %-16s|    %-17s|    %-23s|%n",
                        installment.getId(),
                        installment.getLoanType(),
                        LocalDate.ofEpochDay(installment.getDateOfPayment()),
                        installment.getAmountOfInstallment());
                System.out.println("_____________________________________________________________________________");
            }
        }
    }

    public void showUnpaidInstallments() {

        Account loggedInAccount = SecurityUtils.getAccount();

        List<Installments> list = installmentsService.findInstallments(loggedInAccount.getId(), false);
        if (list == null) {
            System.out.println("You have no paid installments :(");
        } else {
            System.out.println("______________________________________________________________________");
            System.out.println("| Id  |   Loan Type       |  Due Date    |   Amount Of Installment   |");
            System.out.println("______________________________________________________________________");
            for (Installments installment : list) {
                System.out.printf("|  %-3s|   %-16s|  %-12s|    %-23s|%n",
                        installment.getId(),
                        installment.getLoanType(),
                        LocalDate.ofEpochDay(installment.getDueDate()),
                        installment.getAmountOfInstallment());
                System.out.println("______________________________________________________________________");
            }
        }
    }

    public void repayment() {
        boolean flag = true;

        while (flag) {
            printMenu.repaymentMenu();

            System.out.print("Choose an option: ");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1:
                        repaymentTuition();
                        break;
                    case 2:
                        repaymentEducation();
                        break;
                    case 3:
                        repaymentHousing();
                        break;
                    case 4:
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
    }

    public void repaymentTuition() {
        Account loggedInAccount = SecurityUtils.getAccount();

        List<Installments> list = installmentsService.findInstallmentsByLoanType(loggedInAccount.getId(), false, LoanType.TUITION);
        if (list == null) {
            System.out.println("You do not have any tuition loan installments :(");
        } else {
            boolean flag = true;

            Installments installment = list.get(0);
            System.out.println("_________________You Are Paying The Following Loan____________________");
            System.out.println("| Id  |   Loan Type       |  Due Date    |   Amount Of Installment   |");
            System.out.println("______________________________________________________________________");
            System.out.printf("|  %-3s|   %-16s|  %-12s|    %-23s|%n",
                    installment.getId(),
                    installment.getLoanType(),
                    LocalDate.ofEpochDay(installment.getDueDate()),
                    installment.getAmountOfInstallment());
            System.out.println("______________________________________________________________________");

            while (flag) {
                Card card = setCardField.setCard();

                Card newCard = cardService.findCardById(1L);

                if (Objects.equals(card.getCardNumber(), newCard.getCardNumber()) && Objects.equals(card.getCvv2(), newCard.getCvv2()) && Objects.equals(card.getExpirationDate(), newCard.getExpirationDate()) && Objects.equals(card.getSecondPassword(), newCard.getSecondPassword())) {
                    Long id = list.get(0).getId();
                    Long dateOfPayment = LocalDate.now().toEpochDay();

                    installmentsService.updatePaymentStatus(id, dateOfPayment, true);

                    System.out.println("The installment payment has been made successfully :)");
                    flag = false;
                } else {
                    System.out.println("The entered card information is incorrect :(");
                }
            }
        }
    }

    public void repaymentEducation() {
        Account loggedInAccount = SecurityUtils.getAccount();

        List<Installments> list = installmentsService.findInstallmentsByLoanType(loggedInAccount.getId(), false, LoanType.EDUCATION_LOAN);
        if (list == null) {
            System.out.println("You do not have any education loan installments :(");
        } else {

            Installments installment = list.get(0);

            System.out.println("_________________You Are Paying The Following Loan____________________");
            System.out.println("| Id  |   Loan Type       |  Due Date    |   Amount Of Installment   |");
            System.out.println("______________________________________________________________________");
            System.out.printf("|  %-3s|   %-16s|  %-12s|    %-23s|%n",
                    installment.getId(),
                    installment.getLoanType(),
                    LocalDate.ofEpochDay(installment.getDueDate()),
                    installment.getAmountOfInstallment());
            System.out.println("______________________________________________________________________");

            boolean flag = true;

            while (flag) {
                Card card = setCardField.setCard();

                Card newCard = cardService.findCardById(1L);

                if (Objects.equals(card.getCardNumber(), newCard.getCardNumber()) && Objects.equals(card.getCvv2(), newCard.getCvv2()) && Objects.equals(card.getExpirationDate(), newCard.getExpirationDate()) && Objects.equals(card.getSecondPassword(), newCard.getSecondPassword())) {
                    Long id = list.get(0).getId();
                    Long dateOfPayment = LocalDate.now().toEpochDay();

                    installmentsService.updatePaymentStatus(id, dateOfPayment, true);

                    System.out.println("The installment payment has been made successfully :)");
                    flag = false;
                } else {
                    System.out.println("The entered card information is incorrect :(");
                }
            }
        }
    }

    public void repaymentHousing() {
        Account loggedInAccount = SecurityUtils.getAccount();

        List<Installments> list = installmentsService.findInstallmentsByLoanType(loggedInAccount.getId(), false, LoanType.HOUSING_LOAN);
        if (list == null) {
            System.out.println("You do not have any housing loan installments :(");
        } else {

            Installments installment = list.get(0);

            System.out.println("_________________You Are Paying The Following Loan____________________");
            System.out.println("| Id  |   Loan Type       |  Due Date    |   Amount Of Installment   |");
            System.out.println("______________________________________________________________________");
            System.out.printf("|  %-3s|   %-16s|  %-12s|    %-23s|%n",
                    installment.getId(),
                    installment.getLoanType(),
                    LocalDate.ofEpochDay(installment.getDueDate()),
                    installment.getAmountOfInstallment());
            System.out.println("______________________________________________________________________");

            boolean flag = true;

            while (flag) {
                Card card = setCardField.setCard();

                Card newCard = cardService.findCardById(1L);

                if (Objects.equals(card.getCardNumber(), newCard.getCardNumber()) && Objects.equals(card.getCvv2(), newCard.getCvv2()) && Objects.equals(card.getExpirationDate(), newCard.getExpirationDate()) && Objects.equals(card.getSecondPassword(), newCard.getSecondPassword())) {
                    Long id = list.get(0).getId();
                    Long dateOfPayment = LocalDate.now().toEpochDay();

                    installmentsService.updatePaymentStatus(id, dateOfPayment, true);

                    System.out.println("The installment payment has been made successfully :)");
                    flag = false;
                } else {
                    System.out.println("The entered card information is incorrect :(");
                }
            }
        }
    }
}
