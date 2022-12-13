package org.example.service;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Installments;
import org.example.entity.enumeration.LoanType;
import org.example.repository.InstallmentsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class InstallmentsService {

    private final InstallmentsRepository installmentsRepository = new InstallmentsRepository();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public void add(Installments installments) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                installmentsRepository.add(installments);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            } finally {
                session.close();
            }
        }
    }

    public List<Installments> findInstallments(Long accountId, Boolean isPayment) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                return installmentsRepository.findInstallments(accountId, isPayment);
            } catch (Exception e) {
                return null;
            } finally {
                session.close();
            }
        }
    }

    public List<Installments> findInstallmentsByLoanType(Long accountId, Boolean isPayment, LoanType loanType) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                return installmentsRepository.findInstallmentsByLoanType(accountId, isPayment, loanType);
            } catch (Exception e) {
                return null;
            } finally {
                session.close();
            }
        }
    }

    public void updatePaymentStatus(Long id, Long dateOfPayment, Boolean isPayment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                Installments installments = installmentsRepository.findById(id);
                installments.setDateOfPayment(dateOfPayment);
                installments.setIsPayment(isPayment);
                session.update(installments);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            } finally {
                session.close();
            }
        }
    }
}
