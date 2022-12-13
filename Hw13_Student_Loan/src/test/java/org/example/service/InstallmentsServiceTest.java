package org.example.service;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Account;
import org.example.entity.Installments;
import org.example.entity.enumeration.LoanType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InstallmentsServiceTest {
    private static SessionFactory sessionFactory;
    private final Installments installment = new Installments(LoanType.TUITION, 12_000_000.0, false);
    private final InstallmentsService installmentsService = new InstallmentsService();
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
    void add() {
        System.out.println("Running testCreate...");
        session.beginTransaction();

        Account account = session.get(Account.class, 1L);

        installment.setAccount(account);

        installmentsService.add(installment);

        Installments newInstallment = session.get(Installments.class, installment.getId());
        assertEquals(installment.getId(), newInstallment.getId());
    }

    @Test
    @Order(2)
    void findInstallments() {
        System.out.println("Running testCreate...");
        Long accountId = 1L;
        Boolean isPayment = false;
        List<Installments> installments = installmentsService.findInstallments(accountId, isPayment);
        assertEquals(installments.get(0).getAmountOfInstallment(), installment.getAmountOfInstallment());
    }

    @Test
    @Order(3)
    void findInstallmentsByLoanType() {
        System.out.println("Running testCreate...");
        Long accountId = 1L;
        Boolean isPayment = false;
        LoanType loanType = LoanType.TUITION;
        List<Installments> installments = installmentsService.findInstallmentsByLoanType(accountId, isPayment, loanType);
        assertEquals(installments.get(0).getAmountOfInstallment(), installment.getAmountOfInstallment());
    }

    @Test
    @Order(4)
    void updatePaymentStatus() {
        System.out.println("Running testCreate...");
        session.beginTransaction();

        installmentsService.updatePaymentStatus(1L, LocalDate.now().toEpochDay(), true);

        Installments newInstallment = session.get(Installments.class, 1L);
        assertEquals(newInstallment.getIsPayment(), true);
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