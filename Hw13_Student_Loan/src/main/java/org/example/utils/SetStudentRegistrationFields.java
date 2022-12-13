package org.example.utils;

import org.example.entity.*;
import org.example.entity.enumeration.City;
import org.example.entity.enumeration.Grade;
import org.example.entity.enumeration.HouseType;
import org.example.entity.enumeration.UniversityType;
import org.example.exception.UserExistException;

import java.util.ArrayList;
import java.util.List;

public class SetStudentRegistrationFields {

    private final SetInputs setInput = new SetInputs();

    public Student setRequiredFields() {

        try {
            String nationalCode = setInput.setNationalCode();

            String firstname = setInput.setFirstName();

            String lastname = setInput.setLastName();

            String fatherName = setInput.setFatherName();

            String motherName = setInput.setMotherName();

            String birthCertificateNumber = setInput.setBirthCertificate();

            Long birthdate = setInput.setBirthdate();

            String studentNumber = setInput.setStudentNumber();

            String universityName = setInput.setUniversityName();

            UniversityType universityType = setInput.setUniversityType();

            String enteringYear = setInput.setEnteringYear();

            Grade grade = setInput.setGrade();

            String password = setInput.setPassword();

            Account account = new Account();
            account.setUsername(nationalCode);
            account.setPassword(password);

            Student student = new Student();
            student.setFirstName(firstname);
            student.setLastName(lastname);
            student.setFatherName(fatherName);
            student.setMotherName(motherName);
            student.setBirthCertificateNumber(birthCertificateNumber);
            student.setNationalCode(nationalCode);
            student.setDateOfBirth(birthdate);
            student.setStudentNumber(studentNumber);
            student.setUniversityName(universityName);
            student.setTypeOfUniversity(universityType);
            student.setEnteringYear(enteringYear);
            student.setGrade(grade);
            student.setAccount(account);

            account.setStudent(student);

            boolean isMarried = setInput.setIsMarried();

            String spouseFirstname;

            String spouseLastname;

            String spouseNationalCode;

            if (isMarried) {
                spouseFirstname = setInput.setSpouseFirstname();

                spouseLastname = setInput.setSpouseLastname();

                spouseNationalCode = setInput.setSpouseNationalCode(nationalCode);

                SpouseInformation spouseInformation = new SpouseInformation();
                spouseInformation.setFirstName(spouseFirstname);
                spouseInformation.setLastName(spouseLastname);
                spouseInformation.setNationalCode(spouseNationalCode);
                spouseInformation.setStudent(student);

                student.setSpouseInformation(spouseInformation);
            }

            HouseType houseType = setInput.setHouseType();

            City city;

            String address;

            String housingRentalContractNumber;

            if (houseType == HouseType.RENTAL) {
                city = setInput.setCity();

                address = setInput.setAddress();

                housingRentalContractNumber = setInput.setHousingRentalContractNumber();

                HousingInformation housingInformation = new HousingInformation();
                housingInformation.setCity(city);
                housingInformation.setAddress(address);
                housingInformation.setHousingRentalContractNumber(housingRentalContractNumber);
                housingInformation.setStudent(student);

                student.setHousingInformation(housingInformation);
            }

            boolean isGraduated = setInput.setIsGraduated(student.getEnteringYear(), student.getGrade());

            student.setIsMarried(isMarried);
            student.setHouseType(houseType);
            student.setIsGraduated(isGraduated);

            String cardNumber = setInput.setCardNumber();

            String cvv2 = setInput.setCvv2();

            Long expirationDate = setInput.setExpirationDate();

            String secondPassword = setInput.setSecondPassword();

            Card card = new Card();
            card.setCardNumber(cardNumber);
            card.setCvv2(cvv2);
            card.setExpirationDate(expirationDate);
            card.setSecondPassword(secondPassword);
            card.setAccount(account);

            List<Card> cards = new ArrayList<>();
            cards.add(card);

            account.setCards(cards);

            return student;
        } catch (UserExistException userExistException) {
            System.out.println(userExistException.getMessage());
            return null;
        }
    }
}
