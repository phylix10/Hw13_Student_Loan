package org.example.repository;

import org.example.configuration.SessionFactorySingleton;
import org.example.entity.Loan;
import org.example.entity.enumeration.LoanType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class LoanRepository extends BaseRepository<Loan> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public LoanRepository() {
        super("Loan", Loan.class);
    }

    public List<Loan> findLoanByAccountId(Long accountId, LoanType loanType) {
        Session session = sessionFactory.openSession();
        Query<Loan> query = session.createQuery("select l from Loan l where l.account.id=:accountId and l.loanType=:loanType", Loan.class)
                .setParameter("accountId", accountId)
                .setParameter("loanType", loanType);
        List<Loan> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list;
    }

    public Loan findHousingLoanInThisYear(Long accountId, LoanType loanType) {
        Session session = sessionFactory.openSession();

        long aban1 = LocalDate.parse(LocalDate.now().getYear() + "-10-22").toEpochDay();
        long aban8 = LocalDate.parse(LocalDate.now().getYear() + "-10-31").toEpochDay();

        long azar14 = LocalDate.parse(LocalDate.now().getYear() + "-12-04").toEpochDay();
        long azar23 = LocalDate.parse(LocalDate.now().getYear() + "-12-15").toEpochDay();

        Query<Loan> query = session.createQuery("select l from Loan l where l.account.id=:accountId" +
                        " and l.loanType=:loanType and ((l.loanRegistrationDate between :aban1 and :aban8) or (l.loanRegistrationDate between :azar14 and :azar23))", Loan.class)
                .setParameter("accountId", accountId)
                .setParameter("loanType", loanType)
                .setParameter("aban1", aban1).setParameter("aban8", aban8)
                .setParameter("azar14", azar14).setParameter("azar23", azar23);
        List<Loan> list = query.list();
        session.close();
        if (list.isEmpty())
            return null;

        return list.get(0);
    }
}
