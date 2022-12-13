package org.example.service;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Account;
import org.example.entity.Card;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CardServiceTest {

    private static SessionFactory sessionFactory;
    private final Card card = new Card("6037998112345678");
    private final Account account = new Account("5189371107", "123456");
    private final AccountService accountService = new AccountService();
    private final CardService cardService = new CardService();
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
    @Order(1)
    void findCardById() {
        System.out.println("Running testCreate...");
        session.beginTransaction();

        List<Card> cards = new ArrayList<>();
        cards.add(card);

        account.setCards(cards);
        card.setAccount(account);

        accountService.add(account);

        Card newCard = cardService.findCardById(1L);
        assertEquals(card.getCardNumber(), newCard.getCardNumber());
        session.getTransaction().commit();
    }

    @Test
    @Order(2)
    void findCardByAccountId() {
        System.out.println("Running testCreate...");
        Card newCard = cardService.findCardByAccountId(2L);
        assertEquals(newCard.getCardNumber(), card.getCardNumber());
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