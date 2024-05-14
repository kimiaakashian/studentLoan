package org.example.repository.loanInstallment;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.LoanInstallment;
import org.example.model.enums.InstallmentStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class LoanInstallmentRepositoryImpl extends BaseRepositoryImpl<LoanInstallment, Long> implements LoanInstallmentRepository {
    public LoanInstallmentRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<LoanInstallment> getEntityClass() {
        return LoanInstallment.class;
    }

    @Override
    public LoanInstallment findFirstLoanInstallment(Long loanRequestId) {

        LoanInstallment loanInstallment = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanInstallment = ((ArrayList<LoanInstallment>) session.createQuery("FROM LoanInstallment li WHERE li.loanRequest.id = :loanRequestId " +
                            "and li.installmentStatus='" + InstallmentStatus.پرداخت_نشده + "' order by li.installmentDate ASC ", LoanInstallment.class)
                    .setParameter("loanRequestId", loanRequestId)
                    .list()).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanInstallment;
    }

    @Override
    public List<LoanInstallment> findPaidInstallment(Long studentId) {

        ArrayList<LoanInstallment> loanInstallment = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanInstallment = ((ArrayList<LoanInstallment>) session.createQuery("FROM LoanInstallment li WHERE li.loanRequest.student.id = :studentId " +
                            "and li.installmentStatus='" + InstallmentStatus.پرداخت_شده + "' order by li.installmentDate ASC ", LoanInstallment.class)
                    .setParameter("studentId", studentId)
                    .list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanInstallment;
    }

    @Override
    public List<LoanInstallment> findUnPaidInstallment(Long studentId) {

        ArrayList<LoanInstallment> loanInstallment = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanInstallment = ((ArrayList<LoanInstallment>) session.createQuery("FROM LoanInstallment li WHERE li.loanRequest.student.id = :studentId " +
                            "and li.installmentStatus='" + InstallmentStatus.پرداخت_نشده + "' order by li.installmentDate asc ", LoanInstallment.class)
                    .setParameter("studentId", studentId)
                    .list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanInstallment;
    }
}
