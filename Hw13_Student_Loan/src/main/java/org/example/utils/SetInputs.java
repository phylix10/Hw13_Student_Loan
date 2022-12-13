package org.example.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.entity.enumeration.City;
import org.example.entity.enumeration.Grade;
import org.example.entity.enumeration.HouseType;
import org.example.entity.enumeration.UniversityType;
import org.example.exception.UserExistException;
import org.example.menu.PrintMenu;
import org.example.service.StudentService;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SetInputs {

    private static final StudentService studentService = new StudentService();
    private static final CheckInputs checkInput = new CheckInputs();
    private static final Scanner scanner = new Scanner(System.in);
    private static final PrintMenu printMenu = new PrintMenu();

    public String setFirstName() {
        String firstname;
        while (true) {
            System.out.print("Enter your first name: ");
            firstname = scanner.nextLine();
            if (checkInput.nameValidation(firstname)) {
                return firstname;
            } else {
                System.out.println("invalid first name :(");
            }
        }
    }

    public String setLastName() {
        String lastname;
        while (true) {
            System.out.print("Enter your last name: ");
            lastname = scanner.nextLine();
            if (checkInput.nameValidation(lastname)) {
                return lastname;
            } else {
                System.out.println("invalid last name :(");
            }
        }
    }

    public String setFatherName() {
        String fatherName;
        while (true) {
            System.out.print("Enter your father's name: ");
            fatherName = scanner.nextLine();
            if (checkInput.nameValidation(fatherName)) {
                return fatherName;
            } else {
                System.out.println("invalid father's name :(");
            }
        }
    }

    public String setMotherName() {
        String motherName;
        while (true) {
            System.out.print("Enter your mother's name: ");
            motherName = scanner.nextLine();
            if (checkInput.nameValidation(motherName)) {
                return motherName;
            } else {
                System.out.println("invalid mother's name :(");
            }
        }
    }

    public String setBirthCertificate() {
        String birthCertificate;
        while (true) {
            System.out.print("Enter your birth certificate number: ");
            birthCertificate = scanner.nextLine();
            if (checkInput.birthCertificateValidation(birthCertificate)) {
                return birthCertificate;
            } else {
                System.out.println("invalid birth certificate number :(");
            }
        }
    }

    public String setNationalCode() {
        String nationalCode;
        while (true) {
            System.out.print("Enter your national code (10 digit): ");
            nationalCode = scanner.nextLine();
            if (checkInput.nationalCodeValidation(nationalCode)) {
                if (studentService.findByNationalCode(nationalCode) == null) {
                    return nationalCode;
                } else {
                    throw new UserExistException("A student with this national code is already registered, please login");
                }
            } else {
                System.out.println("invalid national code :(");
            }
        }
    }

    public Long setBirthdate() {
        String birthdate;
        while (true) {
            System.out.print("Enter your birth date (example: 2002-03-14): ");
            birthdate = scanner.nextLine();
            if (checkInput.birthDateValidation(birthdate)) {
                return LocalDate.parse(birthdate).toEpochDay();
            } else {
                System.out.println("invalid birth date :(");
            }
        }
    }

    public String setStudentNumber() {
        String studentNumber;
        while (true) {
            System.out.print("Enter your student number (12 digit): ");
            studentNumber = scanner.nextLine();
            if (checkInput.studentNumberValidation(studentNumber)) {
                return studentNumber;
            } else {
                System.out.println("invalid student number :(");
            }
        }
    }

    public String setUniversityName() {
        String universityName;
        while (true) {
            System.out.print("Enter your university name: ");
            universityName = scanner.nextLine();
            if (checkInput.universityNameValidation(universityName)) {
                return universityName;
            } else {
                System.out.println("invalid university name :(");
            }
        }
    }

    public UniversityType setUniversityType() {
        boolean flag = true;

        UniversityType universityType = null;
        while (flag) {
            printMenu.universityTypeMenu();

            System.out.print("Choose an option: ");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1:
                        universityType = UniversityType.DAILY_STATE_UNIVERSITY;
                        flag = false;
                        break;
                    case 2:
                        universityType = UniversityType.PUBLIC_NIGHT_UNIVERSITY;
                        flag = false;
                        break;
                    case 3:
                        universityType = UniversityType.NONPROFIT_UNIVERSITY;
                        flag = false;
                        break;
                    case 4:
                        universityType = UniversityType.PARDIS_UNIVERSITY;
                        flag = false;
                        break;
                    case 5:
                        universityType = UniversityType.EXCESS_CAPACITY;
                        flag = false;
                        break;
                    case 6:
                        universityType = UniversityType.PAYAM_NOOR_UNIVERSITY;
                        flag = false;
                        break;
                    case 7:
                        universityType = UniversityType.UNIVERSITY_OF_APPLIED_SCIENCES;
                        flag = false;
                        break;
                    case 8:
                        universityType = UniversityType.AZAD_UNIVERSITY;
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
        return universityType;
    }

    public String setEnteringYear() {
        String enteringYear;
        while (true) {
            System.out.print("Enter your entering year (example: 2018): ");
            enteringYear = scanner.nextLine();
            if (checkInput.enteringYearValidation(enteringYear)) {
                return enteringYear + "-09-23";
            } else {
                System.out.println("invalid entering year :(");
            }
        }
    }

    public Grade setGrade() {
        boolean flag = true;

        Grade grade = null;
        while (flag) {
            printMenu.gradeMenu();

            System.out.print("Choose an option: ");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1:
                        grade = Grade.ASSOCIATE_DEGREE;
                        flag = false;
                        break;
                    case 2:
                        grade = Grade.UNDERGRADUATE;
                        flag = false;
                        break;
                    case 3:
                        grade = Grade.CONTINUOUS_MASTERS_DEGREE;
                        flag = false;
                        break;
                    case 4:
                        grade = Grade.DISCONTINUOUS_MASTERS_DEGREE;
                        flag = false;
                        break;
                    case 5:
                        grade = Grade.PROFESSIONAL_DOCTORATE;
                        flag = false;
                        break;
                    case 6:
                        grade = Grade.CONTINUOUS_DOCTORAL_DEGREE;
                        flag = false;
                        break;
                    case 7:
                        grade = Grade.NON_CONTINUOUS_SPECIALIZED_DOCTORATE;
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
        return grade;
    }

    public Boolean setIsMarried() {
        boolean flag = true;

        Boolean isMarried = null;
        while (flag) {
            printMenu.marriedMenu();

            System.out.print("Choose an option: ");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1:
                        isMarried = true;
                        flag = false;
                        break;
                    case 2:
                        isMarried = false;
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
        return isMarried;
    }

    public String setPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&";
        String password = RandomStringUtils.random(8, characters);

        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%&])(?=\\S+$).{8}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches()) {
            return password;
        } else {
            return setPassword();
        }
    }

    public String setSpouseFirstname() {
        String spouseFirstname;
        while (true) {
            System.out.print("Enter your spouse first name: ");
            spouseFirstname = scanner.nextLine();
            if (checkInput.nameValidation(spouseFirstname)) {
                return spouseFirstname;
            } else {
                System.out.println("invalid spouse first name :(");
            }
        }
    }

    public String setSpouseLastname() {
        String spouseLastname;
        while (true) {
            System.out.print("Enter your spouse last name: ");
            spouseLastname = scanner.nextLine();
            if (checkInput.nameValidation(spouseLastname)) {
                return spouseLastname;
            } else {
                System.out.println("invalid spouse last name :(");
            }
        }
    }

    public String setSpouseNationalCode(String nationalCode) {
        String spouseNationalCode;
        while (true) {
            System.out.print("Enter your spouse national code (10 digit): ");
            spouseNationalCode = scanner.nextLine();
            if (checkInput.nationalCodeValidation(spouseNationalCode) && !spouseNationalCode.equals(nationalCode)) {
                return spouseNationalCode;
            } else {
                System.out.println("invalid spouse national code :(");
            }
        }
    }

    public HouseType setHouseType() {
        boolean flag = true;

        HouseType houseType = null;
        while (flag) {
            printMenu.homeTypeMenu();

            System.out.print("Choose an option: ");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1:
                        houseType = HouseType.PRIVATE;
                        flag = false;
                        break;
                    case 2:
                        houseType = HouseType.RENTAL;
                        flag = false;
                        break;
                    case 3:
                        houseType = HouseType.DORM;
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
        return houseType;
    }

    public City setCity() {
        boolean flag = true;

        City city = null;
        while (flag) {
            printMenu.cityMenu();

            System.out.print("Choose an option: ");

            try {
                int input = scanner.nextInt();
                scanner.nextLine();

                switch (input) {
                    case 1:
                        city = City.TEHRAN;
                        flag = false;
                        break;
                    case 2:
                        city = City.GUILAN;
                        flag = false;
                        break;
                    case 3:
                        city = City.ESFAHAN;
                        flag = false;
                        break;
                    case 4:
                        city = City.EAST_AZARBAIJAN;
                        flag = false;
                        break;
                    case 5:
                        city = City.FARS;
                        flag = false;
                        break;
                    case 6:
                        city = City.KHOZESTAN;
                        flag = false;
                        break;
                    case 7:
                        city = City.QOM;
                        flag = false;
                        break;
                    case 8:
                        city = City.KHORASAN_RAZAVI;
                        flag = false;
                        break;
                    case 9:
                        city = City.ALBORZ;
                        flag = false;
                        break;
                    case 10:
                        city = City.OTHER;
                        flag = false;
                        break;
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
        return city;
    }

    public String setAddress() {
        String address;
        while (true) {
            System.out.print("Enter your address: ");
            address = scanner.nextLine();
            if (checkInput.addressValidation(address)) {
                return address;
            } else {
                System.out.println("invalid address :(");
            }
        }
    }

    public String setHousingRentalContractNumber() {
        String housingRentalContractNumber;
        while (true) {
            System.out.print("Enter your housing rental contract number: ");
            housingRentalContractNumber = scanner.nextLine();
            if (checkInput.housingRentalContractNumberValidation(housingRentalContractNumber)) {
                return housingRentalContractNumber;
            } else {
                System.out.println("invalid housing rental contract number :(");
            }
        }
    }

    public boolean setIsGraduated(String enteringYear, Grade grade) {
        boolean isGraduated;

        int now = LocalDate.now().getYear();

        if (grade == Grade.ASSOCIATE_DEGREE || grade == Grade.DISCONTINUOUS_MASTERS_DEGREE) {
            int entryYear = LocalDate.parse(enteringYear).getYear();
            entryYear += 2;
            isGraduated = now > entryYear;
        } else if (grade == Grade.UNDERGRADUATE) {
            int entryYear = LocalDate.parse(enteringYear).getYear();
            entryYear += 4;
            isGraduated = now > entryYear;
        } else if (grade == Grade.CONTINUOUS_MASTERS_DEGREE) {
            int entryYear = LocalDate.parse(enteringYear).getYear();
            entryYear += 6;
            isGraduated = now > entryYear;
        } else {
            int entryYear = LocalDate.parse(enteringYear).getYear();
            entryYear += 5;
            isGraduated = now > entryYear;
        }
        return isGraduated;
    }

    public String setCardNumber() {
        Map<String, String> cards = new HashMap<>();
        cards.put("Melli", "603799**********");
        cards.put("Refah", "589463**********");
        cards.put("Tejarat", "627353**********");
        cards.put("Maskan", "628023**********");
        System.out.println("___________ Supported Banks ___________");
        System.out.println("|   Bank Name   |    Card Prefix      |");
        System.out.println("_______________________________________");
        cards.forEach ((k, v) -> {
            System.out.printf("|   %-12s|   %-18s|%n",
                    k,
                    v);
            System.out.println("_______________________________________");
        });
        String cardNumber;
        while (true) {
            System.out.print("Enter your card number (16 digit): ");
            cardNumber = scanner.nextLine();
            if (checkInput.cardNumberValidation(cardNumber)) {
                return cardNumber;
            }
        }
    }

    public String setCvv2() {
        String cvv2;
        while (true) {
            System.out.print("Enter your cvv2 (3 or 4 digit): ");
            cvv2 = scanner.nextLine();
            if (checkInput.cvv2Validation(cvv2)) {
                return cvv2;
            } else {
                System.out.println("invalid cvv2 :(");
            }
        }
    }

    public Long setExpirationDate() {
        String expirationDate;
        while (true) {
            System.out.print("Enter your expiration date (example: 2023-11): ");
            expirationDate = scanner.nextLine();
            if (checkInput.expirationDateValidation(expirationDate)) {
                return LocalDate.parse(expirationDate + "-01").toEpochDay();
            } else {
                System.out.println("invalid expiration date :(");
            }
        }
    }

    public String setSecondPassword() {
        String password;
        while (true) {
            System.out.print("Enter your second password (Minimum 6 and maximum 16 digits): ");
            password = scanner.nextLine();
            if (checkInput.checkSecondPassword(password)) {
                return password;
            } else {
                System.out.println("invalid second password :(");
            }
        }
    }
}
