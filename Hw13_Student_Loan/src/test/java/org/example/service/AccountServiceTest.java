package org.example.service;

import org.example.entity.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.example.configuration.SessionFactorySingleton;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {
    private static SessionFactory sessionFactory;
    private final Account account = new Account("5180182832", "6Qdkj#w3");
    private final AccountService accountService = new AccountService();
    private Session session;

    @BeforeAll
    public static void setup() {
        sessionFactory = SessionFactorySingleton.getInstance();
        System.out.println("SessionFactory created");
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @Test
    void add() {
        System.out.println("Running testCreate...");
        session.beginTransaction();

        accountService.add(account);

        Account newAccount = session.get(Account.class, account.getId());
        assertEquals(newAccount.getId(), account.getId());
        session.getTransaction().commit();
    }

    @Test
    void login() {
        System.out.println("Running testCreate...");
        assertTrue(accountService.login(account.getUsername(), account.getPassword()));
    }

    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }
}