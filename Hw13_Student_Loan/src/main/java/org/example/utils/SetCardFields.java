package org.example.utils;

import org.example.entity.Account;
import org.example.entity.Card;
import org.example.securiy.SecurityUtils;

public class SetCardFields {
    private static final SetInputs setInput = new SetInputs();

    public Card setCard() {
        String cardNumber = setInput.setCardNumber();

        String cvv2 = setInput.setCvv2();

        Long expirationDate = setInput.setExpirationDate();

        String secondPassword = setInput.setSecondPassword();

        Account loggedInAccount = SecurityUtils.getAccount();

        Card card = new Card();
        card.setCardNumber(cardNumber);
        card.setCvv2(cvv2);
        card.setExpirationDate(expirationDate);
        card.setSecondPassword(secondPassword);
        card.setAccount(loggedInAccount);

        return card;
    }
}
