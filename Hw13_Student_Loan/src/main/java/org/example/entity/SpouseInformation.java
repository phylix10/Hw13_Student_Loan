package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Setter
@Getter
@Table(name = "SpouseInformation")
public class SpouseInformation extends BaseEntity<Long> {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "national_code")
    private String nationalCode;

    @OneToOne()
    @JoinColumn(name = "student_id")
    private Student student;
}
