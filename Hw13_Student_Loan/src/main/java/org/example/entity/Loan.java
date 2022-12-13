package org.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.entity.enumeration.Grade;
import org.example.entity.enumeration.LoanType;

import javax.persistence.*;


@Entity
@Setter
@Getter
@Table(name = "Loan")
public class Loan extends BaseEntity<Long> {

    @Column(name = "loan_type")
    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "loan_registration_date")
    private Long loanRegistrationDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Loan() {
    }

    public Loan(LoanType loanType, Grade grade, Long amount) {
        this.loanType = loanType;
        this.grade = grade;
        this.amount = amount;
    }
}
