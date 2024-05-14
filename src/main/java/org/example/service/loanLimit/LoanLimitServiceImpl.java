package org.example.service.loanLimit;

import org.example.base.service.BaseServiceImpl;
import org.example.model.LoanLimit;
import org.example.repository.LoanLimit.LoanLimitRepository;
import org.hibernate.SessionFactory;

public class LoanLimitServiceImpl extends BaseServiceImpl<LoanLimit, Long, LoanLimitRepository> implements LoanLimitService {
    public LoanLimitServiceImpl(LoanLimitRepository repository, SessionFactory sessionFactory) {
        super(repository, sessionFactory);
    }
}
