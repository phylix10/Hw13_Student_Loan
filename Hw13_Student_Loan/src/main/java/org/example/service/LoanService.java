package org.example.service;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Loan;
import org.example.entity.enumeration.LoanType;
import org.example.repository.LoanRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class LoanService {

    private final LoanRepository loanRepository = new LoanRepository();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public List<Loan> findLoanByAccountId(Long accountId, LoanType loanType) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                return loanRepository.findLoanByAccountId(accountId, loanType);
            } catch (Exception e) {
                return null;
            }
            finally {
                session.close();
            }
        }
    }

    public void add(Loan loan) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                loanRepository.add(loan);
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

    public Loan findHousingLoanInThisYear(Long accountId, LoanType loanType) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            try {
                return loanRepository.findHousingLoanInThisYear(accountId, loanType);
            } catch (Exception e) {
                return null;
            }
            finally {
                session.close();
            }
        }
    }
}
