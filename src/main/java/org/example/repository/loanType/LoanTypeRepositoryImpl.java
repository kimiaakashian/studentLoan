package org.example.repository.loanType;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.LoanType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class LoanTypeRepositoryImpl extends BaseRepositoryImpl<LoanType, Long> implements LoanTypeRepository {
    public LoanTypeRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<LoanType> getEntityClass() {
        return LoanType.class;
    }

    @Override
    public List<LoanType> findAllLoanTypes() {
        ArrayList<LoanType> loanTypes = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanTypes = (ArrayList<LoanType>) session.createQuery(
                            "FROM LoanType l ", LoanType.class)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanTypes;
    }
}
