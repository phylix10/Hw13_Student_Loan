package org.example.repository;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Installments;
import org.example.entity.enumeration.LoanType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class InstallmentsRepository extends BaseRepository<Installments> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();
    public InstallmentsRepository() {
        super("Installments", Installments.class);
    }

    public List<Installments> findInstallments(Long accountId, Boolean isPayment) {
        Session session = sessionFactory.openSession();
        Query<Installments> query = session.createQuery("select i from Installments i where i.account.id=:accountId and i.isPayment=:isPayment", Installments.class)
                .setParameter("accountId", accountId)
                .setParameter("isPayment", isPayment);
        List<Installments> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list;
    }

    public List<Installments> findInstallmentsByLoanType(Long accountId, Boolean isPayment, LoanType loanType) {
        Session session = sessionFactory.openSession();
        Query<Installments> query = session.createQuery("select i from Installments i where i.account.id=:accountId and i.isPayment=:isPayment and i.loanType=:loanType", Installments.class)
                .setParameter("accountId", accountId)
                .setParameter("isPayment", isPayment)
                .setParameter("loanType", loanType);
        List<Installments> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list;
    }
}
