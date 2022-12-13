package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "Account")
public class Account extends BaseEntity<Long> {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Card> cards;

    @OneToMany(mappedBy = "account")
    private Set<Installments> installments;

    @OneToMany(mappedBy = "account")
    private Set<Loan> loans;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
