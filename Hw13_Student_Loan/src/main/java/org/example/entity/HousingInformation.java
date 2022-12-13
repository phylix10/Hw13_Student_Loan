package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.enumeration.City;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "HousingInformation")
public class HousingInformation extends BaseEntity<Long> {

    @Column(name = "city")
    @Enumerated(EnumType.STRING)
    private City city;

    @Column(name = "address")
    private String address;

    @Column(name = "housing_rental_contract_number")
    private String housingRentalContractNumber;

    @OneToOne()
    @JoinColumn(name = "student_id")
    private Student student;
}
