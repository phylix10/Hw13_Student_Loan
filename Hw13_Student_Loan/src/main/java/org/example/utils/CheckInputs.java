package org.example.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class CheckInputs {

    public boolean nameValidation(String name) {
        return name.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){2,25}$");
    }

    public boolean birthCertificateValidation(String birthCertificate) {
        return birthCertificate.matches("\\d{0,10}");
    }

    public boolean nationalCodeValidation(String nationalCode) {
        if (nationalCode.length() != 10) {
            return false;
        } else {
            String[] allDigitEqual = {"0000000000", "1111111111", "2222222222", "3333333333",
                    "4444444444", "5555555555", "6666666666", "7777777777", "8888888888", "9999999999"};
            if (Arrays.asList(allDigitEqual).contains(nationalCode)) {
                return false;
            } else {
                int sum = 0;
                int length = 10;
                for (int i = 0; i < length - 1; i++) {
                    sum += Integer.parseInt(String.valueOf(nationalCode.charAt(i))) * (length - i);
                }

                int r = Integer.parseInt(String.valueOf(nationalCode.charAt(9)));

                int c = sum % 11;

                return (((c < 2) && (r == c)) || ((c >= 2) && ((11 - c) == r)));
            }
        }
    }

    public boolean birthDateValidation(String birthDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate birthdate = LocalDate.parse(birthDate, formatter);
            if (birthdate.isAfter(LocalDate.now().minusYears(15)) || birthdate.isBefore(LocalDate.now().minusYears(90))) {
                System.out.println("Your age is below the age limit :(");
                return false;
            } else {
                return true;
            }

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean studentNumberValidation(String studentNumber) {
        return studentNumber.matches("\\d{12}");
    }

    public boolean universityNameValidation(String universityName) {
        return universityName.matches("(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){2,50}$");
    }

    public boolean enteringYearValidation(String enteringYear) {
        if (enteringYear.matches("^19([5-9]\\d)|^20(\\d\\d)$")) {
            LocalDate localDate = LocalDate.parse(enteringYear + "-09" + "-23");
            return localDate.getYear() <= LocalDate.now().getYear();
        } else {
            return false;
        }
    }

    public boolean addressValidation(String address) {
        return address.matches("^[a-zA-Z\\d _.,-]{2,100}$");
    }

    public boolean housingRentalContractNumberValidation(String housingRentalContractNumber) {
        return housingRentalContractNumber.matches("\\d{3,15}");
    }

    public boolean cardNumberValidation(String cardNumber) {
        if (cardNumber.matches("\\d{16}")) {
            if (cardNumber.startsWith("603799") || cardNumber.startsWith("589463") || cardNumber.startsWith("627353") || cardNumber.startsWith("628023")) {
                return true;
            } else {
                System.out.println("This bank is not supported");
                return false;
            }
        } else {
            System.out.println("invalid card number :(");
            return false;
        }
    }

    public boolean cvv2Validation(String cvv2) {
        return cvv2.matches("\\d{3,4}");
    }

    public boolean expirationDateValidation(String expirationDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        try {
            LocalDate localDate = LocalDate.parse(expirationDate + "-01", formatter);
            if ((localDate.getYear() < now.getYear()) && (localDate.getMonth().getValue() < now.getMonth().getValue())) {
                System.out.println("Your card has expired :(");
                return false;
            } else if (localDate.getYear() < now.getYear()) {
                System.out.println("Your card has expired :(");
                return false;
            } else if (localDate.getYear() - now.getYear() > 10) {
                System.out.println("Expiry date more than allowed :(");
                return false;
            } else {
                return true;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean checkSecondPassword(String password) {
        return password.matches("\\d{6,16}");
    }
}
