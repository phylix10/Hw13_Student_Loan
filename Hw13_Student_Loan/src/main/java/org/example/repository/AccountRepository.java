package org.example.repository;


import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AccountRepository extends BaseRepository<Account> {
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    public AccountRepository() {
        super("Account", Account.class);
    }

    public Account findByUsername(String username) {
        Session session = sessionFactory.openSession();
        Query<Account> query = session.createQuery("select a from Account a where a.username=:username", Account.class)
                .setParameter("username", username);
        List<Account> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }
}
