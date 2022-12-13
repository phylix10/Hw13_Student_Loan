package org.example.service;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Account;
import org.example.entity.Loan;
import org.example.entity.enumeration.Grade;
import org.example.entity.enumeration.LoanType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LoanServiceTest {
    private static SessionFactory sessionFactory;
    private final Loan loan = new Loan(LoanType.TUITION, Grade.UNDERGRADUATE, 1_300_000L);
    private final LoanService loanService = new LoanService();
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
    @Order(2)
    void findLoanByAccountId() {
        System.out.println("Running testCreate...");
        List<Loan> loans = loanService.findLoanByAccountId(1L, LoanType.TUITION);
        assertNotNull(loans);
    }

    @Test
    @Order(1)
    void add() {
        System.out.println("Running testCreate...");
        session.beginTransaction();

        Account account = session.get(Account.class, 1L);

        loan.setAccount(account);

        loanService.add(loan);

        Loan newLoan = session.get(Loan.class, 1L);

        assertEquals(loan.getAmount(), newLoan.getAmount());
    }

    @Test
    @Order(3)
    void findHousingLoanInThisYear() {
        System.out.println("Running testCreate...");
        Loan newLoan = loanService.findHousingLoanInThisYear(1L, LoanType.HOUSING_LOAN);
        assertNull(newLoan);
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