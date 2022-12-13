package org.example.entity;


import lombok.Getter;
import lombok.Setter;
import org.example.entity.enumeration.Grade;
import org.example.entity.enumeration.HouseType;
import org.example.entity.enumeration.UniversityType;

import javax.persistence.*;
@Getter
@Setter
@Entity
@Table(name = "Student")
public class Student extends BaseEntity<Long> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "birth_certificate_number")
    private String birthCertificateNumber;

    @Column(name = "national_code", unique = true)
    private String nationalCode;

    @Column(name = "date_of_birth")
    private Long dateOfBirth;

    @Column(name = "student_number")
    private String studentNumber;

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "type_of_university")
    @Enumerated(EnumType.STRING)
    private UniversityType typeOfUniversity;

    @Column(name = "entering_year")
    private String enteringYear;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "is_married")
    private Boolean isMarried;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private SpouseInformation spouseInformation;

    @Column(name = "house_type")
    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private HousingInformation housingInformation;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Account account;

    @Column(name = "is_graduated")
    private Boolean isGraduated;

    public Student() {
    }

    public Student(String firstName, String lastName, String nationalCode, Boolean isGraduated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.isGraduated = isGraduated;
    }
}
