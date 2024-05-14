package org.example.repository.LoanLimit;

import org.example.base.repository.BaseRepositoryImpl;
import org.example.model.LoanLimit;
import org.example.model.enums.CityType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LoanLimitRepositoryImpl extends BaseRepositoryImpl<LoanLimit, Long> implements LoanLimitRepository {
    public LoanLimitRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<LoanLimit> getEntityClass() {
        return LoanLimit.class;
    }

    @Override
    public LoanLimit findByLoanTypeAndCityType(long loanTypeId, CityType cityType) {
        LoanLimit loanLimit = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanLimit = session.createQuery("FROM LoanLimit lm WHERE lm.loanType.id = :loanTypeId and  lm.cityType = :cityType", LoanLimit.class)
                    .setParameter("loanTypeId", loanTypeId)
                    .setParameter("cityType", cityType)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return loanLimit;
    }

    @Override
    public LoanLimit findByLoanTypeAndEducationGrade(long loanTypeId, long educationGrade) {
        LoanLimit loanLimit = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            loanLimit = session.createQuery("FROM LoanLimit lm WHERE lm.loanType.id = :loanTypeId and  lm.educationGrade.id = :educationGrade", LoanLimit.class)
                    .setParameter("loanTypeId", loanTypeId)
                    .setParameter("educationGrade", educationGrade)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loanLimit;
    }
}
