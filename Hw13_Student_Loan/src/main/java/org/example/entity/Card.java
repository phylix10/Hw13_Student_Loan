package org.example.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "Card")
public class Card extends BaseEntity<Long> {

    @Column(name = "card_number", unique = true)
    private String cardNumber;

    @Column(name = "cvv2")
    private String cvv2;

    @Column(name = "expiration_date")
    private Long expirationDate;

    @Column(name = "second_password")
    private String secondPassword;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Card() {
    }

    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
