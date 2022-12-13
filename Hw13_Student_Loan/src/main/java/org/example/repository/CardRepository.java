package org.example.repository;


import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CardRepository extends BaseRepository<Card> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    public CardRepository() {
        super("Card", Card.class);
    }

    public Card findCardByAccountId(Long accountId) {
        Session session = sessionFactory.openSession();
        Query<Card> query = session.createQuery("select c from Card c where c.account.id=:accountId", Card.class)
                .setParameter("accountId", accountId);
        List<Card> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }
}
