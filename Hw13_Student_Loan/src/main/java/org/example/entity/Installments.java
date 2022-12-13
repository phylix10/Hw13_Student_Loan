package org.example.entity;


import lombok.Getter;
import lombok.Setter;
import org.example.entity.enumeration.LoanType;

import javax.persistence.*;
@Entity
@Setter
@Getter
@Table(name = "installments")
public class Installments extends BaseEntity<Long> {

    @Column(name = "loan_type")
    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @Column(name = "due_date")
    private Long dueDate;

    @Column(name = "amount_of_installment")
    private Double amountOfInstallment;

    @Column(name = "date_of_payment")
    private Long dateOfPayment;

    @Column(name = "is_payment")
    private Boolean isPayment;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Installments() {
    }

    public Installments(LoanType loanType, Double amountOfInstallment, Boolean isPayment) {
        this.loanType = loanType;
        this.amountOfInstallment = amountOfInstallment;
        this.isPayment = isPayment;
    }
}
