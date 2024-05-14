package org.example.repository.loanRequest;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.LoanRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class LoanRequestRepositoryImpl extends BaseRepositoryImpl<LoanRequest, Long> implements LoanRequestRepository {
    public LoanRequestRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<LoanRequest> getEntityClass() {
        return LoanRequest.class;
    }

    @Override
    public List<LoanRequest> findAllLoanRequestForStudent(Long studentId) {
        ArrayList<LoanRequest> loanRequests = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanRequests = (ArrayList<LoanRequest>) session.createQuery(
                            "FROM LoanRequest l  where l.student.id= :studentId", LoanRequest.class)
                    .setParameter("studentId", studentId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanRequests;
    }

    @Override
    public LoanRequest findHousingLoanRequestForStudent(Long studentId) {
        LoanRequest loanRequests = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanRequests = session.createQuery(
                            "FROM LoanRequest l  where l.student.id= :studentId and l.loanType.id = 3", LoanRequest.class)
                    .setParameter("studentId", studentId)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanRequests;
    }

    @Override
    public LoanRequest findTuitionLoanRequestForStudent(Long studentId) {
        LoanRequest loanRequests = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanRequests = session.createQuery(
                            "FROM LoanRequest l  where l.student.id= :studentId and l.loanType.id = 1", LoanRequest.class)
                    .setParameter("studentId", studentId)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanRequests;
    }

    @Override
    public LoanRequest findEducationLoanRequestForStudent(Long studentId) {
        LoanRequest loanRequests = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanRequests = session.createQuery(
                            "FROM LoanRequest l  where l.student.id= :studentId and l.loanType.id = 2", LoanRequest.class)
                    .setParameter("studentId", studentId)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanRequests;
    }
}

