package org.example.service;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Card;
import org.example.repository.CardRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CardService {

    private final CardRepository cardRepository = new CardRepository();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public Card findCardById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                return cardRepository.findById(id);
            } catch (Exception e) {
                return null;
            }
            finally {
                session.close();
            }
        }
    }

    public Card findCardByAccountId(Long accountId) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                return cardRepository.findCardByAccountId(accountId);
            } catch (Exception e) {
                return null;
            }
            finally {
                session.close();
            }
        }
    }
}
