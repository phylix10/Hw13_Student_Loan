package org.example.service;


import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Account;
import org.example.exception.UserNotFoundException;
import org.example.repository.AccountRepository;
import org.example.securiy.SecurityUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class AccountService {

    private final AccountRepository accountRepository = new AccountRepository();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public void add(Account account) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                accountRepository.add(account);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
            finally {
                session.close();
            }
        }
    }

    public boolean login(String username, String password) {
        Account account = accountRepository.findByUsername(username);
        if (account != null) {
            if (account.getPassword().equals(password)) {
                SecurityUtils.setAccount(account);
                return true;
            } else {
                System.out.println("Password is wrong :(");
                return false;
            }
        } else {
            throw new UserNotFoundException("User with given username: " + username + " not found, please register");
        }
    }
}
